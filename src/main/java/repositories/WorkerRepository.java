package repositories;

import entities.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerRepository implements CrudRepository<Worker> {
    private Connection connection;
    private PreparedStatement ps;

    private static final String SELECT_ALL_WORKERS = "SELECT * FROM worker;";
    private static final String SELECT_WORKER_BY_ID = "SELECT * FROM worker WHERE id = ?;";
    private static final String INSERT_WORKER = "INSERT INTO worker (name, birthday, level, salary) VALUES (?,?,?,?);";
    private static final String UPDATE_WORKER = "UPDATE worker SET name = ?, birthday = ?, level = ?, salary = ? WHERE id = ?";
    private static final String DELETE_WORKER = "DELETE FROM worker WHERE id = ?;";

    public WorkerRepository(Connection connection){
        this.connection = connection;
    }
    @Override
    public List<Worker> listAll() {
        List<Worker> workers = new ArrayList<>();
        try{
            ps = connection.prepareStatement(SELECT_ALL_WORKERS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                workers.add(new Worker(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getString("level"),
                        rs.getDouble("salary")));
            }
            return workers;
        } catch (SQLException ex){
            System.out.println("Error while selecting all: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Worker getById(long id) {
        try{
            ps = connection.prepareStatement(SELECT_WORKER_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return new Worker(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getDate("birthday").toLocalDate(),
                    rs.getString("level"),
                    rs.getDouble("salary")
            );
        } catch (SQLException ex){
            System.out.println("Error while selecting by id: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public long create(Worker entity) {
        try{
            ps = connection.prepareStatement(INSERT_WORKER, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, entity.getName());
            ps.setDate(2, Date.valueOf(entity.getBirthday()));
            ps.setString(3, entity.getLevel());
            ps.setDouble(4, entity.getSalary());

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
    public void update(Worker entity) {
        try{
            ps = connection.prepareStatement(UPDATE_WORKER);

            ps.setString(1, entity.getName());
            ps.setDate(2, Date.valueOf(entity.getBirthday()));
            ps.setString(3, entity.getLevel());
            ps.setDouble(4, entity.getSalary());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating client failed.");
            }
        } catch (SQLException ex){
            System.out.println("Error while updating: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Worker entity) {
        try{
            ps = connection.prepareStatement(DELETE_WORKER);

            ps.setLong(1, entity.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating client failed.");
            }
        } catch (SQLException ex){
            System.out.println("Error while deleting: " + ex.getMessage());
        }
    }
}
