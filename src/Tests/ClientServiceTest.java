package Tests;

import Domain.Client;
import Domain.ClientValidator;
import Domain.Medicament;
import Domain.Transaction;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.ClientService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    @Test
    void addOrUpdateServiceShouldAddClient() {

        ClientValidator validator =new ClientValidator();
        IRepository<Transaction> transactionIRepository = null;
        IRepository<Medicament> medIRepository = null;
        IRepository<Client> clientRepository = new InMemoryRepository<>(validator);
        ClientService clientService = new ClientService(clientRepository,transactionIRepository,medIRepository);

        Client client = new Client("1","Ioana","Pop","1234567890123","12.03.2019","05.02.2001");

        clientService.add("1","Ioana","Pop","1234567890123","12.03.2019","05.02.2001");

        assertEquals(client,clientService.getAll().get(0));
        assertEquals(1, clientService.getAll().size());
    }


    @Test
    void shouldRemoveClient() {

        ClientValidator validator =new ClientValidator();
        IRepository<Transaction> transactionIRepository = null;
        IRepository<Medicament> medIRepository = null;
        IRepository<Client> clientRepository = new InMemoryRepository<>(validator);
        ClientService clientService = new ClientService(clientRepository,transactionIRepository,medIRepository);

        Client client = new Client("1","Ioana","Pop","1234567890123","12.03.2019","05.02.2001");

        clientService.add("1","Ioana","Pop","1234567890123","12.03.2019","05.02.2001");
        clientService.remove("1");

        assertEquals(0, clientService.getAll().size());
        assertFalse(clientService.getAll().size() != 0);

    }

    @Test
    void getAllServieShouldGetAllClient() {
        ClientValidator validator =new ClientValidator();
        IRepository<Transaction> transactionIRepository = null;
        IRepository<Medicament> medIRepository = null;
        IRepository<Client> clientRepository = new InMemoryRepository<>(validator);
        ClientService clientService = new ClientService(clientRepository,transactionIRepository,medIRepository);

        Client client = new Client("1","Ioana","Pop","1234567890123","12.03.2019","05.02.2001");
        Client client1 = new Client("2","Ion","Pop","1234587890123","12.03.2019","05.02.2001");

        clientService.add("1","Ioana","Pop","1234567890123","12.03.2019","05.02.2001");
        clientService.add("2","Ion","Pop","1234587890123","12.03.2019","05.02.2001");

        assertEquals(client, clientService.getAll().get(0));
        assertEquals(client1, clientService.getAll().get(1));
        assertFalse(clientService.getAll().size() != 2);
    }
}