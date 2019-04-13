package Service;

import Domain.Medicament;

public class MedicamentBySoldNrVM {

    private Medicament medicament;
    private int nrOfSldItems;

    public MedicamentBySoldNrVM(Medicament medicament, int nrOfSldItems) {
        this.medicament = medicament;
        this.nrOfSldItems = nrOfSldItems;
    }

    @Override
    public String toString() {
        return "MedicamentBySoldNrVM{" +
                "medicament=" + medicament +
                ", nrOfSldItems=" + nrOfSldItems +
                '}';
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public int getNrOfSldItems() {
        return nrOfSldItems;
    }
}