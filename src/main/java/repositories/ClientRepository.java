package repositories;

import entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements CrudRepository<Client>{
    private Connection connection;
    private PreparedStatement ps;

    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM client;";
    private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM client WHERE id = ?;";
    private static final String INSERT_CLIENT = "INSERT INTO client (name) VALUES (?);";
    private static final String UPDATE_CLIENT = "UPDATE client SET name = ? WHERE id = ?";
    private static final String DELETE_CLIENT = "DELETE FROM client WHERE id = ?;";

    public ClientRepository(Connection connection){
        this.connection = connection;
    }
    @Override
    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try{
            ps = connection.prepareStatement(SELECT_ALL_CLIENTS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clients.add(new Client(
                        rs.getLong("id"),
                        rs.getString("name")));
            }
            return clients;
        } catch (SQLException ex){
            System.out.println("Error while selecting all: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Client getById(long id) {
        try{
            ps = connection.prepareStatement(SELECT_CLIENT_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return new Client(rs.getLong("id"), rs.getString("name"));
        } catch (SQLException ex){
            System.out.println("Error while selecting by id: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public long create(Client entity) {
        try{
            ps = connection.prepareStatement(INSERT_CLIENT, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, entity.getName());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating client failed, no rows affected.");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("Creating client failed, no ID obtained.");
            }

        } catch(SQLException ex) {
            System.out.println("Error while creating: " + ex.getMessage());
            return -1;
        }
    }

    @Override
    public void update(Client entity) {
        try{
            ps = connection.prepareStatement(UPDATE_CLIENT);

            ps.setString(1, entity.getName());
            ps.setLong(2, entity.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating client failed.");
            }
        } catch (SQLException ex){
            System.out.println("Error while updating: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Client entity) {
        try{
            ps = connection.prepareStatement(DELETE_CLIENT);

            ps.setLong(1, entity.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting client failed");
            }
        } catch (SQLException ex){
            System.out.println("Error while deleting: " + ex.getMessage());
        }
    }
}
