package Tests;

import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.MedicamentService;
import Service.TransactionService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentServiceTest {

    @Test
    void addOrUpdateServiceShouldAddMedicament() {

        IRepository<Transaction> transactionIRepository = null;
        MedicamentValidator validator =new MedicamentValidator();
        IRepository<Medicament> medRepository = new InMemoryRepository<>(validator);
        MedicamentService medService = new MedicamentService(medRepository,transactionIRepository);

        Medicament med = new Medicament("1", "Fasconal", "THG", 86.6, true);

        medService.addOrUpdate("1", "Fasconal", "THG", 86.6, true);


        assertEquals(med,medService.getAll().get(0));
        assertEquals(1, medService.getAll().size());
    }

    @Test
    void shouldRemoveMedicament() {

        IRepository<Transaction> transactionIRepository = null;
        MedicamentValidator validator =new MedicamentValidator();
        IRepository<Medicament> medRepository = new InMemoryRepository<>(validator);
        MedicamentService medService = new MedicamentService(medRepository,transactionIRepository);

        Medicament med = new Medicament("1", "Fasconal", "THG", 86.6, true);

        medService.addOrUpdate("1", "Fasconal", "THG", 86.6, true);

        medService.remove("1");

        assertEquals(0, medService.getAll().size());
        assertFalse(medService.getAll().size() != 0);

    }


    @Test
    void getAllServieShouldGetAllMedicament() {

        IRepository<Transaction> transactionIRepository = null;
        MedicamentValidator validator =new MedicamentValidator();
        IRepository<Medicament> medRepository = new InMemoryRepository<>(validator);
        MedicamentService medService = new MedicamentService(medRepository,transactionIRepository);


        Medicament med = new Medicament("1", "Fasconal", "THG", 86.6, true);
        Medicament med1 = new Medicament("2", "Paracetamol", "THG", 6.6, false);

        medService.addOrUpdate("1", "Fasconal", "THG", 86.6, true);
        medService.addOrUpdate("2", "Paracetamol", "THG", 6.6, false);

        assertEquals(med, medService.getAll().get(0));
        assertEquals(med1, medService.getAll().get(1));
        assertFalse(medService.getAll().size() != 2);
    }
}