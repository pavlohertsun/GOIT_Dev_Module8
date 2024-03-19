package services;

import entities.Client;
import utils.Database;
import validators.ClientValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private  Connection connection;
    private PreparedStatement ps;

    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM client;";
    private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM client WHERE id = ?;";
    private static final String INSERT_CLIENT = "INSERT INTO client (name) VALUES (?);";
    private static final String UPDATE_CLIENT = "UPDATE client SET name = ? WHERE id = ?";
    private static final String DELETE_CLIENT = "DELETE FROM client WHERE id = ?;";
    public static void main(String[] args) {
        Connection connection = Database.getConnection();


    }

    public long create(String name) throws Exception{
        if(!ClientValidator.isValid(new Client(name)))
            throw new Exception("Client is not valid");

        try{
            ps = connection.prepareStatement(INSERT_CLIENT, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);

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
    public String getById(long id){
        try{
            ps = connection.prepareStatement(SELECT_CLIENT_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getString("name");
        } catch (SQLException ex){
            System.out.println("Error while selecting by id: " + ex.getMessage());
        }
        return null;
    }
    public void setName(long id, String name) throws Exception{
        if(!ClientValidator.isValid(new Client(id, name)))
            throw new Exception("Client is not valid");

        try{
            ps = connection.prepareStatement(UPDATE_CLIENT);

            ps.setString(1, name);
            ps.setLong(2, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1) {
                System.out.println("Successful update");
            }
        } catch (SQLException ex){
            System.out.println("Error while updating: " + ex.getMessage());
        }
    }
    public void deleteById(long id){
        try{
            ps = connection.prepareStatement(DELETE_CLIENT);

            ps.setLong(1, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1) {
                System.out.println("Successful delete");
            }
        } catch (SQLException ex){
            System.out.println("Error while deleting: " + ex.getMessage());
        }
    }
    public List<Client> listAll(){
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
}
