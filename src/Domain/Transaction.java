package Domain;

import java.util.Date;

public class Transaction extends Entity {

   /* CRUD tranzacție: id, id_medicament, id_card_client (poate fi nul), nr_bucăți, data și ora.
    Dacă există un card client, atunci aplicați o reducere de 10% dacă medicamentul nu necesită rețetă și de 15%
    dacă necesită. Se tipărește prețul plătit și reducerile acordate.*/

    private String idMedicament,idCardClient;
    private int nrOfItems;
    Date date;
    private String date1,time;
    private double discount;

    public Transaction(String id, String idMedicament, String idCardClient, int nrOfItems, String date1, String time) {
        super(id);
        this.idMedicament = idMedicament;
        this.idCardClient = idCardClient;
        this.nrOfItems = nrOfItems;
        this.date1 = date1;
        this.time = time;
    }

    public Transaction(String id, String idMedicament, String idCardClient, int nrOfItems, Date date, String time, double discount) {
        super(id);
        this.idMedicament = idMedicament;
        this.idCardClient = idCardClient;
        this.nrOfItems = nrOfItems;
        this.date = date;
        this.time = time;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + getId() +
                ", idMedicament=" + idMedicament +
                ", idCardClient=" + idCardClient +
                ", nrOfItems=" + nrOfItems +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", discount=" + discount +
                '}';
    }


    /*public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }*/

    public String getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(String idMedicament) {
        this.idMedicament = idMedicament;
    }

    public String getIdCardClient() {
        return idCardClient;
    }

    public void setIdCardClient(String idCardClient) {
        this.idCardClient = idCardClient;
    }

    public int getNrOfItems() {
        return nrOfItems;
    }

    public void setNrOfItems(int nrOfItems) {
        this.nrOfItems = nrOfItems;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}