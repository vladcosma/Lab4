package Tests;

import Domain.Medicament;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentTest {

    @Test
    void toStringShouldReturnALongEnoughString() {
        Medicament medicament = new Medicament("1", "fasconal", "ZHT", 58.2, false);

        assertTrue(medicament.toString().length() > 10);
    }

    @Test
    void gettersShouldReturnCorrectly() {
        Medicament medicament = new Medicament("1", "fasconal", "ZHT", 58.2, false);

        assertEquals("1", medicament.getId());
        assertEquals("fasconal", medicament.getName());
        assertEquals("ZHT", medicament.getManufacturer());
        assertEquals(58.2, medicament.getPrice());
        assertEquals(false, medicament.isNeedRecipe());
    }


    @Test
    void settersShouldSetFieldsCorrectly() {
        Medicament medicament = new Medicament("1", "fasconal", "ZHT", 58.2, false);

        medicament.setId("2");
        medicament.setName("paracetamol");
        medicament.setManufacturer("PTA");
        medicament.setPrice(89.6);
        medicament.setNeedRecipe(true);

        assertEquals("2", medicament.getId());
        assertEquals("paracetamol", medicament.getName());
        assertEquals("PTA", medicament.getManufacturer());
        assertEquals(89.6, medicament.getPrice());
        assertEquals(true, medicament.isNeedRecipe());

    }

    @org.junit.jupiter.api.Test
    void constructorShouldSetAllFieldsCorrectly() {
        Medicament medicament = new Medicament("1", "fasconal", "ZHT", 58.2, false);

        assertEquals("1", medicament.getId());
        assertEquals("fasconal", medicament.getName());
        assertEquals("ZHT", medicament.getManufacturer());
        assertEquals(58.2, medicament.getPrice());
        assertEquals(false, medicament.isNeedRecipe());
    }

}