package repositories;

import entities.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements CrudRepository<Project> {
    private Connection connection;
    private PreparedStatement ps;

    private static final String SELECT_ALL_PROJECTS = "SELECT * FROM project;";
    private static final String SELECT_PROJECT_BY_ID = "SELECT * FROM project WHERE id = ?;";
    private static final String INSERT_PROJECT = "INSERT INTO project (client_id, start_date, finish_date) VALUES (?,?,?);";
    private static final String UPDATE_PROJECT = "UPDATE project SET client_id = ?, start_date = ?, finish_date = ? WHERE id = ?";
    private static final String DELETE_PROJECT = "DELETE FROM project WHERE id = ?;";

    public ProjectRepository(Connection connection){
        this.connection = connection;
    }
    @Override
    public List<Project> listAll() {
        List<Project> projects = new ArrayList<>();
        try{
            ps = connection.prepareStatement(SELECT_ALL_PROJECTS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projects.add(new Project(
                        rs.getLong("id"),
                        rs.getInt("client_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("finish_date").toLocalDate()));
            }
            return projects;
        } catch (SQLException ex){
            System.out.println("Error while selecting all: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Project getById(long id) {
        try{
            ps = connection.prepareStatement(SELECT_PROJECT_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return new Project(
                    rs.getLong("id"),
                    rs.getInt("client_id"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("finish_date").toLocalDate());
        } catch (SQLException ex){
            System.out.println("Error while selecting by id: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public long create(Project entity) {
        try{
            ps = connection.prepareStatement(INSERT_PROJECT, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, entity.getClientId());
            ps.setDate(2, Date.valueOf(entity.getStartDate()));
            ps.setDate(3, Date.valueOf(entity.getFinishDate()));

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
    public void update(Project entity) {
        try{
            ps = connection.prepareStatement(UPDATE_PROJECT);

            ps.setInt(1, entity.getClientId());
            ps.setDate(2, Date.valueOf(entity.getStartDate()));
            ps.setDate(3, Date.valueOf(entity.getFinishDate()));
            ps.setLong(4, entity.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating client failed.");
            }
        } catch (SQLException ex){
            System.out.println("Error while updating: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Project entity) {
        try{
            ps = connection.prepareStatement(DELETE_PROJECT);

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
