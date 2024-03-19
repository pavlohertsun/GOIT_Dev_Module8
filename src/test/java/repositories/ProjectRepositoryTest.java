package repositories;

import entities.Client;
import entities.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Database;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRepositoryTest {

    @Test
    void listAllTest() {
        List<Project> expectedList = new ArrayList<>();
        expectedList.add(new Project(1, 1, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 2, 28)));
        expectedList.add(new Project(2, 2, LocalDate.of(2022, 5, 15), LocalDate.of(2023, 3, 15)));
        expectedList.add(new Project(3, 3, LocalDate.of(2022, 9, 10), LocalDate.of(2023, 6, 20)));
        expectedList.add(new Project(4, 4, LocalDate.of(2022, 12, 5), LocalDate.of(2023, 7, 15)));
        expectedList.add(new Project(5, 5, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 12, 31)));
        expectedList.add(new Project(6, 6, LocalDate.of(2023, 7, 20), LocalDate.of(2024, 4, 10)));
        expectedList.add(new Project(7, 7, LocalDate.of(2023, 11, 12), LocalDate.of(2024, 8, 5)));
        expectedList.add(new Project(8, 1, LocalDate.of(2024, 2, 28), LocalDate.of(2024, 11, 30)));
        expectedList.add(new Project(9, 2, LocalDate.of(2024, 6, 15), LocalDate.of(2025, 3, 20)));
        expectedList.add(new Project(10, 3, LocalDate.of(2024, 10, 1), LocalDate.of(2025, 7, 10)));
        expectedList.add(new Project(11, 4, LocalDate.of(2025, 1, 15), LocalDate.of(2025, 10, 15)));
        expectedList.add(new Project(12, 1, LocalDate.of(2025, 5, 10), LocalDate.of(2026, 2, 28)));
        expectedList.add(new Project(13, 2, LocalDate.of(2025, 9, 25), LocalDate.of(2026, 6, 30)));
        expectedList.add(new Project(14, 7, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 10, 20)));
        expectedList.add(new Project(15, 6, LocalDate.of(2026, 4, 20), LocalDate.of(2026, 12, 31)));

        Connection connection = Database.getConnection();

        ProjectRepository projectRepository = new ProjectRepository(connection);
        List<Project> actualList = projectRepository.listAll();

        Assertions.assertIterableEquals(expectedList, actualList);
    }

    @Test
    void getByIdTest() {
        Project expectedProject = new Project(7, 7, LocalDate.of(2023, 11, 12), LocalDate.of(2024, 8, 5));

        Connection connection = Database.getConnection();

        ProjectRepository projectRepository = new ProjectRepository(connection);
        Project actualProject = projectRepository.getById(7);

        Assertions.assertEquals(expectedProject, actualProject);
    }

    @Test
    void createTest() {
        long expectedNewId = 16;

        Project projectToInsert = new Project(7, LocalDate.of(2023, 11, 12), LocalDate.of(2024, 8, 5));
        Connection connection = Database.getConnection();

        ProjectRepository projectRepository = new ProjectRepository(connection);
        long actualNewId = projectRepository.create(projectToInsert);

        Assertions.assertEquals(expectedNewId, actualNewId);
    }

    @Test
    void updateTest() {
        Project projectToUpdate = new Project(16, 7, LocalDate.of(2023, 11, 12), LocalDate.of(2024, 8, 5));
        Connection connection = Database.getConnection();

        ProjectRepository projectRepository = new ProjectRepository(connection);

        assertDoesNotThrow(() -> projectRepository.update(projectToUpdate));
    }

    @Test
    void deleteTest() {
        Project projectToDelete = new Project(16, 7, LocalDate.of(2023, 11, 12), LocalDate.of(2024, 8, 5));
        Connection connection = Database.getConnection();

        ProjectRepository projectRepository = new ProjectRepository(connection);

        assertDoesNotThrow(() -> projectRepository.delete(projectToDelete));
    }
}