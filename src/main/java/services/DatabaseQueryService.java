package services;

import entities.Client;
import repositories.ClientRepository;
import utils.Database;

import java.sql.Connection;
import java.util.List;

public class DatabaseQueryService {
    public static void main(String[] args) {
        Connection connection = Database.getConnection();

        ClientRepository clientRepository = new ClientRepository(connection);

        Client client = new Client("Pavlo");
        client.setId(clientRepository.create(client));

        List<Client> clients = clientRepository.listAll();

        clients.forEach(entity -> System.out.println(entity));

        System.out.println(clientRepository.getById(client.getId()));

        clientRepository.update(client);

        clientRepository.delete(client);
    }
}
