package Domain;

public class ClientValidator implements IValidator<Client>  {

    //cnp validare

    /**
     * Validates a client
     * @param client
     * @throws ClientValExc if there are validation errors.
     */

    public void validate(Client client) throws ClientValExc {
        if (!(client.getCNP().length() == 13)) {
            throw new ClientValExc ("The CNP is invalid, it must be 13 character!");
        }
    }

}