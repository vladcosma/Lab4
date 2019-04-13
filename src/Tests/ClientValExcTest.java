package Tests;

import Domain.Client;
import Domain.ClientValExc;
import Domain.ClientValidator;

import static org.junit.jupiter.api.Assertions.*;

class ClientValExcTest {

    @org.junit.jupiter.api.Test
    void shouldThrowAnExc(){
        Client client =new Client("2","Ioana", "Pop","123457890123","12.03.2001","01.05.1990");
        ClientValidator clientValidator = new ClientValidator();

        assertThrows(ClientValExc.class, () ->
                clientValidator.validate(client));
    }

}