package repositories;

import entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {

    @Test
    void listAllTest() {
        List<Client> expectedList = List.of(
                new Client(1, "Taras"),
                new Client(2, "George"),
                new Client(3, "Vlad"),
                new Client(4, "Svitlana"),
                new Client(5, "Dmytro"),
                new Client(6, "Daria"),
                new Client(7, "Marta"));

        Connection connection = Database.getConnection();

        ClientRepository clientRepository = new ClientRepository(connection);
        List<Client> actualList = clientRepository.listAll();

        Assertions.assertIterableEquals(expectedList, actualList);
    }

    @Test
    void getByIdTest() {
        Client expectedClient = new Client(4, "Svitlana");

        Connection connection = Database.getConnection();

        ClientRepository clientRepository = new ClientRepository(connection);
        Client actualClient = clientRepository.getById(expectedClient.getId());

        Assertions.assertEquals(expectedClient, actualClient);
    }

    @Test
    void createTest() {
        long expectedNewId = 9;

        Client clientToInsert = new Client("Pavlo");
        Connection connection = Database.getConnection();

        ClientRepository clientRepository = new ClientRepository(connection);
        long actualNewId = clientRepository.create(clientToInsert);

        Assertions.assertEquals(expectedNewId, actualNewId);
    }

    @Test
    void updateTest() {
        Client clientToUpdate= new Client(8,"PavloUpdate");
        Connection connection = Database.getConnection();

        ClientRepository clientRepository = new ClientRepository(connection);

        assertDoesNotThrow(() -> clientRepository.update(clientToUpdate));
    }

    @Test
    void deleteTest() {
        Client clientToDelete= new Client(9,"Pavlo");
        Connection connection = Database.getConnection();

        ClientRepository clientRepository = new ClientRepository(connection);

        assertDoesNotThrow(() -> clientRepository.delete(clientToDelete));
    }
}