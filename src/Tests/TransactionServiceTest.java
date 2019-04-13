package Tests;

import Domain.Medicament;
import Domain.MedicamentValidator;
import Domain.Transaction;
import Domain.TransactionValidator;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.TransactionService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {
    @Test
    void addOrUpdateServiceShouldAddTransaction() {

        TransactionValidator validator = new TransactionValidator();
        MedicamentValidator valid = new MedicamentValidator();

        IRepository<Transaction> transRepository = new InMemoryRepository<>(validator);
        IRepository<Medicament> medicamentRepository = new InMemoryRepository<>(valid);

        TransactionService transactionService = new TransactionService(transRepository,medicamentRepository);

        Medicament m1 = new Medicament("1","paracetamol","ZHT",85.2,false);
        Medicament m2 = new Medicament("2","paracetamol","ZHT",85.2,false);

        Transaction transaction1 = new Transaction("1","1","55",2,"12.02.2001","11.00");
        Transaction transaction2 = new Transaction("2","2","5",2,"10.02.2001","21.00");

        medicamentRepository.insert(m1);
        medicamentRepository.insert(m2);

        transactionService.addOrUpdate("1","1","55",2,"20.03.2001","11");
        transactionService.addOrUpdate("2","2","5",2,"10.02.2001","21.00");


        assertEquals(2, transactionService.getAll().size());
        assertEquals(transaction1, transactionService.getAll().get(0));
        assertEquals(transaction2, transactionService.getAll().get(1));
    }


    @Test
    void removeServiceShouldRemoveTransaction() {

        TransactionValidator validator = new TransactionValidator();
        MedicamentValidator valid = new MedicamentValidator();

        IRepository<Transaction> transRepository = new InMemoryRepository<>(validator);
        IRepository<Medicament> medicamentRepository = new InMemoryRepository<>(valid);

        TransactionService transactionService = new TransactionService(transRepository,medicamentRepository);

        Medicament m1 = new Medicament("1","paracetamol","ZHT",85.2,false);
        Medicament m2 = new Medicament("2","paracetamol","ZHT",85.2,false);

        medicamentRepository.insert(m1);
        medicamentRepository.insert(m2);

        Transaction transaction1 = new Transaction("1","1","55",2,"12.02.2001","11.00");

        transactionService.addOrUpdate("1","1","55",2,"20.03.2001","11");

        transactionService.remove("1");
        assertEquals(0, transactionService.getAll().size());
        assertTrue(transactionService.getAll().size() == 0);
    }

    @Test
    void getAllServiceShouldGetAllTransactions() {
        TransactionValidator validator = new TransactionValidator();
        MedicamentValidator valid = new MedicamentValidator();

        IRepository<Transaction> transRepository = new InMemoryRepository<>(validator);
        IRepository<Medicament> medicamentRepository = new InMemoryRepository<>(valid);

        TransactionService transactionService = new TransactionService(transRepository,medicamentRepository);

        Medicament m1 = new Medicament("1","paracetamol","ZHT",85.2,false);
        Medicament m2 = new Medicament("2","paracetamol","ZHT",85.2,false);

        medicamentRepository.insert(m1);
        medicamentRepository.insert(m2);

        Transaction transaction1 = new Transaction("1","1","55",2,"12.02.2001","11.00");
        Transaction transaction2 = new Transaction("2","1","55",2,"12.02.2001","11.00");

        transactionService.addOrUpdate("1","1","55",2,"12.02.2001","11.00");
        transactionService.addOrUpdate("2","1","55",2,"12.02.2001","11.00");

        assertEquals(transaction1, transactionService.getAll().get(0));
        assertEquals(transaction2, transactionService.getAll().get(1));
        assertTrue(transactionService.getAll().size() == 2);
    }
}