package Tests;

import Domain.Client;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @org.junit.jupiter.api.Test
    void constructorShouldSetAllFieldsCorrectly() {
        Client client = new Client("1", "test", "test2", "123456778954", "23.06.1995", "13.07.1998");

        assertEquals("1", client.getId());
        assertEquals("test", client.getFirstName());
        assertEquals("test2", client.getLastName());
        assertEquals("123456778954", client.getCNP());
        assertEquals("23.06.1995", client.getDateOfRegistration());
        assertEquals("13.07.1998", client.getDateOfBirth());

    }

    @org.junit.jupiter.api.Test
    void settersShouldSetFieldsCorrectly(){
        Client client1 = new Client("1","aaa","bb","1234567890123","12.03.2001","11.02.2001");

        client1.setId("2");
        client1.setLastName("test");
        client1.setFirstName("test2");
        client1.setCNP("123445678954");
        client1.setDateOfRegistration("23.96.1756");
        client1.setDateOfBirth("12.95.1567");

        assertEquals("2", client1.getId());
        assertEquals("test", client1.getLastName());
        assertEquals("test2", client1.getFirstName());
        assertEquals("123445678954", client1.getCNP());
        assertEquals("23.96.1756", client1.getDateOfRegistration());
        assertEquals("12.95.1567", client1.getDateOfBirth());

    }


}