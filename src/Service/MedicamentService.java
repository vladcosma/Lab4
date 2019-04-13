package Service;

import Domain.Entity;
import Domain.Medicament;
import Domain.Transaction;
import Repository.IRepository;
import Repository.InMemoryRepository;

import java.util.*;

public class MedicamentService {

    private IRepository<Medicament> medrepository;
    private IRepository<Transaction> transactionIRepository;

    private Stack<UndoRedoOperation<Medicament>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<Medicament>> redoableOperations = new Stack<>();

    public MedicamentService(IRepository<Medicament> repository, IRepository<Transaction> transactionIRepository) {

        this.medrepository = repository;
        this.transactionIRepository = transactionIRepository;

    }



    /**
     *
     * @param id
     * @param name
     * @param manufacturer
     * @param price
     * @param needRecipe
     */

    public void addOrUpdate(String id, String name, String manufacturer, double price, boolean needRecipe) {
        Medicament existing = medrepository.findById(id);

        if (existing != null) {
            // keep unchanged fields as they were
            if (name.isEmpty()) {
                name = existing.getName();
            }
            if (name.isEmpty()) {
                name = existing.getName();
            }
            if (manufacturer.isEmpty()) {
                manufacturer = existing.getManufacturer();
            }
            if (price == 0) {
                price = existing.getPrice();
            }
        }
        Medicament medicament = new Medicament(id, name, manufacturer, price,needRecipe);
        medrepository.insert(medicament);
        undoableOperations.push(new AddOperation<>(medrepository, medicament));
    }

    /**
     *
     * @param name
     * @return
     */
    public double getPriceByID(String name){
        Medicament medicament = medrepository.findById(name);
        return medicament.getPrice();
    }

    public void remove(String id) {
        Medicament medicament = medrepository.findById(id);
        medrepository.remove(id);
        undoableOperations.push(new AddOperation<>(medrepository, medicament));
    }

    public List<Medicament> getAll(){
        return medrepository.getAll();
    }

    //Scumpirea cu un procentaj dat a tuturor medicamentelor cu prețul mai mic decât o valoare dată.
    public List<Medicament> increaseMedPrice (double procent, double valueOfMed){
        List<Medicament> result = new ArrayList<>();
        for (Medicament m: medrepository.getAll()){
            if(m.getPrice() < valueOfMed) {
                m.setPrice(m.getPrice()+ m.getPrice()*procent);
            }
            result.add(m);
        }
        return result;
    }

    //cautare full text
    public List<Medicament> fullTextMedicamentSearch (String text) {
        List<Medicament> result = new ArrayList<>();
        for (Medicament m : medrepository.getAll()) {
            if (m.toString().contains(text)) {
                result.add(m);
            }
        }
        return result;
    }

    //Afișarea medicamentelor ordonate descrescător după numărul de vânzări.
    public List<MedicamentBySoldNrVM> sortDesc(){

        /* for(Transaction t: transactionRepository.getAll()){
            Medicament medicamentSold = medicamentRepository.findById(t.getIdMedicament());*/

        Map<String,Integer> freq = new HashMap<>();
        for(Transaction t: transactionIRepository.getAll()){

            String medID = t.getIdMedicament();
            if(freq.containsKey(medID)){
                freq.put(medID,freq.get(medID)+1);
            } else {
                freq.put(medID,1);
            }
        }

        List<MedicamentBySoldNrVM> orderedMed = new ArrayList<>();
        for(String medID: freq.keySet()){
            MedicamentBySoldNrVM medicament = new MedicamentBySoldNrVM(medrepository.findById(medID),freq.get(medID));
            orderedMed.add(medicament);
        }

        orderedMed.sort((m1,m2) -> Integer.compare(m2.getNrOfSldItems(),m1.getNrOfSldItems()));
        return orderedMed;
    }

    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOperation<Medicament> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableOperations.add(lastOperation);

        }
    }

    public void redo() {
        if (!redoableOperations.empty()) {
            UndoRedoOperation<Medicament> lastOperation = redoableOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }

}