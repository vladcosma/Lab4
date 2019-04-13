package Tests;

import Domain.Medicament;
import Domain.MedicamentValidator;
import Repository.IRepository;
import Repository.InMemoryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepositoryTest {

    @Test
    void shouldReturnById() {

        MedicamentValidator validator =new MedicamentValidator();
        IRepository<Medicament> medicamentRepository = new InMemoryRepository<>(validator);

        Medicament med = new Medicament("2","Fasconal","ZTG",85.2,false);
        Medicament med1 = new Medicament("3","Paracetamol","ZTG",85.2,false);

        medicamentRepository.insert(med);
        medicamentRepository.insert(med1);

        Medicament existingMed = medicamentRepository.findById("3");

        assertEquals("3",existingMed.getId());

    }

    @Test
    void shouldAddTheMedicament() {
        MedicamentValidator validator =new MedicamentValidator();
        IRepository<Medicament> medicamentRepository = new InMemoryRepository<>(validator);

        Medicament med = new Medicament("2","Fasconal","ZTG",85.2,false);
        medicamentRepository.insert(med);
        //if medicament was added

        assertEquals("2", med.getId());
        assertEquals("Fasconal",med.getName());
        assertEquals("ZTG",med.getManufacturer());
        assertEquals(85.2, med.getPrice());
        assertEquals(false, med.isNeedRecipe());

    }

    @Test
    void shouldUpdateTheMedicament() {

        MedicamentValidator validator =new MedicamentValidator();
        IRepository<Medicament> medicamentRepository = new InMemoryRepository<>(validator);

        Medicament added = new Medicament("2","Fasconal","ZTG",85.2,false);
        medicamentRepository.update(added);

        Medicament existing = medicamentRepository.findById("2");
        added.setId("3");

        assertEquals("3",added.getId());   //??
    }

    @Test
    void shouldRemoveMedicament() {
        MedicamentValidator validator =new MedicamentValidator();
        IRepository<Medicament> medicamentRepository = new InMemoryRepository<>(validator);

        Medicament added = new Medicament("2","Fasconal","ZTG",85.2,false);
        medicamentRepository.insert(added);

        medicamentRepository.remove(added.getId());
        List<Medicament> allMedicaments =medicamentRepository.getAll();
        assertEquals(0, allMedicaments.size());
        assertFalse(medicamentRepository.getAll().size() != 0);
    }

    @Test
    void shouldReturnGetAll() {
        MedicamentValidator validator =new MedicamentValidator();
        IRepository<Medicament> medicamentRepository = new InMemoryRepository<>(validator);

        Medicament med = new Medicament("2","Fasconal","ZTG",85.2,false);
        Medicament med1 = new Medicament("3","Paracetamol","ZTG",5.2,true);

        medicamentRepository.insert(med);
        medicamentRepository.insert(med1);

        List<Medicament> allCars = medicamentRepository.getAll();
        assertEquals(med, allCars.get(0));
        assertEquals(med1, allCars.get(1));
        assertFalse(medicamentRepository.getAll().size() != 2);

    }
}