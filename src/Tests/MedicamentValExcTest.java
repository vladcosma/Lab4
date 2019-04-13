package Tests;

import Domain.Medicament;
import Domain.MedicamentValExc;
import Domain.MedicamentValidator;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentValExcTest {

    @org.junit.jupiter.api.Test
    void shouldThrowAnExc(){

        Medicament medicament = new Medicament("1", "fasconal", "ZHT", -58.2, false);
        MedicamentValidator medVal = new MedicamentValidator();

        assertThrows(MedicamentValExc.class, () ->
                medVal.validate(medicament));
    }

}