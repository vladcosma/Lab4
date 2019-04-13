package Domain;

import java.util.Date;
import java.util.Objects;

public class Client extends Entity{
    //CRUD card client: id, nume, prenume, CNP, data nașterii (dd.mm.yyyy), data înregistrării (dd.mm.yyyy). CNP-ul trebuie să fie unic.


    private String  firstName, lastName, CNP, dateOfRegistration;
    private String dateOfBirth;

    public Client(String id, String firstName, String lastName, String CNP, String dateOfRegistration, String dateOfBirth) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.dateOfRegistration = dateOfRegistration;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", CNP='" + CNP + '\'' +
                ", dateOfRegistration='" + dateOfRegistration + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    /*public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}