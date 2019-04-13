package Domain;

public class MedicamentValidator implements IValidator<Medicament> {

    //pretul sa fie strict pozitiv

    /**
     * Validates a medicament
     * @param medicament
     * @throws MedicamentValExc if there are validation errors.
     */

    public void validate(Medicament medicament){
        if(medicament.getPrice() <= 0) {
            throw new MedicamentValExc("The price must be a positive value!");
        }
    }
}