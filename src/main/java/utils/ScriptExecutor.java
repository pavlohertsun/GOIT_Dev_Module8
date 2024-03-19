package utils;

import java.sql.*;

public class ScriptExecutor {
    private Connection connection;

    public ScriptExecutor(Connection connection){
        this.connection = connection;
    }
    public int executeUpdate(String query){
        try(Statement statement = connection.createStatement()){
            return statement.executeUpdate(query);
        } catch (SQLException ex){
            System.out.println("SQL ex. Cannot create statement");
            return 0;
        }
    }
    public String executeQuery(String query){
        try(Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(query);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            StringBuilder output = new StringBuilder();
            while (rs.next()){
                for (int i = 1; i <= columnCount; i++) {
                    output.append(rs.getString(i) + " ");
                }
                output.append("\n");
            }
            return output.toString();
        } catch (SQLException ex){
            System.out.println("SQL ex. Cannot create statement");
            return null;
        }
    }
}
