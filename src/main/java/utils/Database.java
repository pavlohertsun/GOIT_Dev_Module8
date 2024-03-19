package utils;

import properties.PropsReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final static Database INSTANCE = new Database();

    private static Connection connection;

    private Database(){
        String url = PropsReader.createConnectionUrl();
        String login = PropsReader.readDBLogin();
        String password = PropsReader.readDBPassword();

        try{
            connection = DriverManager.getConnection(url, login, password);
        }
        catch (SQLException ex){
            System.out.println("SQL ex. Cannot connect");
        }
    }
    public static Database getInstance() { return INSTANCE; }
    public static Connection getConnection() { return connection; }
    public void closeConnection(){
        try{
            connection.close();
        } catch (SQLException ex){
            System.out.println("Failed while closing connection");
        }
    }
}
