package Service;

import Domain.Client;
import Domain.Entity;
import Domain.Medicament;
import Domain.Transaction;
import Repository.IRepository;

import java.util.*;

public class ClientService <T extends Entity> {

    private IRepository<Client> repository;
    private IRepository<Transaction> transRepo;
    private IRepository<Medicament> medicamentRepository;

    private Stack<UndoRedoOperation<Client>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<Client>> redoableOperations = new Stack<>();

    public ClientService(IRepository<Client> repository, IRepository<Transaction> transRepo,IRepository<Medicament> medicamentRepository) {
        this.repository = repository;
        this.transRepo = transRepo;
        this.medicamentRepository = medicamentRepository;
    }



    /*public void addOrUpdate(String id, String firstName, String lastName, String CNP, String dateOfRegistration, String dayOfBirth) {
        Client existing = repository.findById(id);
        if (existing != null) {
            // keep unchanged fields as they were
            if (firstName.isEmpty()) {
                firstName = existing.getFirstName();
            }
            if (lastName.isEmpty()) {
                lastName = existing.getLastName();
            }
            if (CNP.isEmpty()) {
                CNP = existing.getCNP();
            }
            if (dateOfRegistration.isEmpty()) {
                dateOfRegistration = existing.getDateOfRegistration();
            }
            if (dayOfBirth.isEmpty()) {
                dayOfBirth = existing.getDateOfBirth();
            }
        }
        Client client = new Client(id, firstName, lastName, CNP, dateOfRegistration, dayOfBirth);
        repository.insert(client);
    }*/

    public void add(String id, String firstName, String lastName, String CNP, String dateOfRegistration, String dayOfBirth){
        Client client = new Client (id, firstName, lastName, CNP, dateOfRegistration, dayOfBirth);
        List<Client> CNPcheck= repository.getAll();
        for (Client c: CNPcheck) {
            if (c.getCNP().equals(CNP)) {
                throw new RuntimeException("error: existent CNP");
            }
        }
        repository.insert(client);
        undoableOperations.push(new AddOperation<>(repository, client));
    }

    public void update(String id, String firstName, String lastName, String CNP, String dateOfRegistration, String dayOfBirth) {
        Client clientActual = repository.findById(id);
        Client clientUpdate = new Client(id, firstName, lastName, CNP, dateOfRegistration, dayOfBirth);
        List<Client> clients = new ArrayList<>(repository.getAll());
        for (Client c : clients) {
            if (CNP.equals(c.getCNP()) && !CNP.equals(clientUpdate.getCNP())) {
                throw new RuntimeException("error: existent CNP");
            }
        }
        repository.update(clientUpdate);
        undoableOperations.push(new UpdateOperation<>(repository, clientUpdate, clientActual));
    }



    public void remove(String id) {
        Client client = repository.findById(id);
        repository.remove(id);
        undoableOperations.push(new DeleteOperation<>(repository, client));

    }


    public List<Client> getAll() {

        return repository.getAll();
    }

    /**
     * search for a text
     * @param text
     * @return the list of result
     */

    public List<Client> fullTextClientSearch (String text){
        List <Client> result = new ArrayList<>();
        for (Client c: repository.getAll()){
            if(c.toString().contains(text)){
                result.add(c);
            }
        }
        return result;
    }

    //Afișarea cardurilor client ordonate descrescător după valoarea reducerilor obținute.



    public List<ClientCardByPriceRedObt> sortDesc(){

        Map<String,Double> freq = new HashMap<>();
        for(Transaction t: transRepo.getAll()){

            double discount = 0;

            Medicament medicamentSold = medicamentRepository.findById(t.getIdMedicament());

            if (t.getIdCardClient() != null && (!medicamentSold.isNeedRecipe())) {
                discount = 0.1; // 10% discount
            } else {
                discount = 0.15;
            }

            String cardClient = t.getIdCardClient();

            if(freq.containsKey(cardClient)){
                freq.put(cardClient,freq.get(cardClient)+discount*t.getNrOfItems()*medicamentRepository.findById(t.getIdMedicament()).getPrice());
            } else {
                freq.put(cardClient,discount*t.getNrOfItems()*medicamentRepository.findById(t.getIdMedicament()).getPrice());
            }
        }

        List<ClientCardByPriceRedObt> obtained = new ArrayList<>();
        for(String cardClient: freq.keySet()){
            ClientCardByPriceRedObt ccbpro = new ClientCardByPriceRedObt(repository.findById(cardClient),freq.get(cardClient));
            obtained.add(ccbpro);
        }

        obtained.sort((m1,m2) -> Double.compare(m2.getPriveRedObtained(),m1.getPriveRedObtained()));
        return obtained;
    }

    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOperation<Client> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableOperations.add(lastOperation);

        }
    }

    /**
     * Redo the last operation
     */
    public void redo() {
        if (!redoableOperations.empty()) {
            UndoRedoOperation<Client> lastOperation = redoableOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }
}