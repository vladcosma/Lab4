package Tests;

import Domain.Medicament;
import Domain.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void toStringShouldReturnALongEnoughString() {

        Transaction t;
        t = new Transaction("1","1","55",2,"12.03.2019","11.00");
        // Transaction transaction = new Transaction("1","1","55",2,"12.03.2019","11.00",58);

        Medicament medicament = new Medicament("1", "fasconal", "ZHT", 58.2, false);

        assertTrue(medicament.toString().length() > 10);
    }
}