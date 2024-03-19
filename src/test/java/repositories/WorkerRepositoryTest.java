package repositories;

import entities.Client;
import entities.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Database;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkerRepositoryTest {

    @Test
    void listAllTest() {
        List<Worker> expectedList = new ArrayList<>();

        expectedList.add(new Worker(1, "John", LocalDate.of(1997, 11, 21), "Senior", 6000));
        expectedList.add(new Worker(2, "Paul", LocalDate.of(1992, 7, 20), "Junior", 1200));
        expectedList.add(new Worker(3, "Bob", LocalDate.of(2001, 10, 12), "Middle", 3000));
        expectedList.add(new Worker(4, "Andrew", LocalDate.of(1993, 3, 25), "Senior", 6000));
        expectedList.add(new Worker(5, "Michael", LocalDate.of(1991, 8, 8), "Trainee", 1800));
        expectedList.add(new Worker(6, "Emma", LocalDate.of(1994, 6, 1), "Junior", 1500));
        expectedList.add(new Worker(7, "Veronika", LocalDate.of(1992, 11, 18), "Middle", 3500));
        expectedList.add(new Worker(8, "Helena", LocalDate.of(2004, 9, 3), "Senior", 7000));
        expectedList.add(new Worker(9, "Nataliya", LocalDate.of(1993, 4, 10), "Trainee", 1000));
        expectedList.add(new Worker(10, "Vadym", LocalDate.of(1995, 2, 28), "Junior", 1300));
        expectedList.add(new Worker(11, "James", LocalDate.of(1991, 12, 5), "Middle", 3200));
        expectedList.add(new Worker(12, "Elon", LocalDate.of(1994, 10, 22), "Senior", 6500));
        expectedList.add(new Worker(13, "Bill", LocalDate.of(1992, 6, 15), "Trainee", 1700));
        expectedList.add(new Worker(14, "Sergiy", LocalDate.of(1993, 9, 17), "Junior", 1400));
        expectedList.add(new Worker(15, "Jack", LocalDate.of(2002, 5, 30), "Middle", 3800));
        expectedList.add(new Worker(16, "Mia", LocalDate.of(1991, 3, 8), "Senior", 7500));
        expectedList.add(new Worker(17, "Oleg", LocalDate.of(1994, 1, 25), "Trainee", 1100));
        expectedList.add(new Worker(18, "Yurii", LocalDate.of(1992, 4, 3), "Junior", 1100));
        expectedList.add(new Worker(19, "Oleksandr", LocalDate.of(2000, 11, 19), "Middle", 3300));
        expectedList.add(new Worker(20, "Emily", LocalDate.of(1996, 5, 17), "Senior", 2650));


        Connection connection = Database.getConnection();

        WorkerRepository workerRepository = new WorkerRepository(connection);
        List<Worker> actualList = workerRepository.listAll();

        Assertions.assertIterableEquals(expectedList, actualList);
    }

    @Test
    void getByIdTest() {
        Worker expectedWorker = new Worker(9, "Nataliya", LocalDate.of(1993, 4, 10), "Trainee", 1000);

        Connection connection = Database.getConnection();

        WorkerRepository workerRepository = new WorkerRepository(connection);
        Worker actualWorker = workerRepository.getById(expectedWorker.getId());

        Assertions.assertEquals(expectedWorker, actualWorker);
    }

    @Test
    void createTest() {
        long expectedNewId = 21;

        Worker workerToInsert = new Worker("Nataliya", LocalDate.of(1993, 4, 10), "Trainee", 1000);
        Connection connection = Database.getConnection();

        WorkerRepository workerRepository = new WorkerRepository(connection);
        long actualNewId = workerRepository.create(workerToInsert);

        Assertions.assertEquals(expectedNewId, actualNewId);
    }

    @Test
    void updateTest() {
        Worker workerToUpdate =new Worker(21, "NataliyaUpdate", LocalDate.of(1993, 4, 10), "Trainee", 1000);
        Connection connection = Database.getConnection();

        WorkerRepository workerRepository = new WorkerRepository(connection);

        assertDoesNotThrow(() -> workerRepository.update(workerToUpdate));
    }

    @Test
    void deleteTest() {
        Worker workerToDelete =new Worker(21, "NataliyaUpdate", LocalDate.of(1993, 4, 10), "Trainee", 1000);
        Connection connection = Database.getConnection();

        WorkerRepository workerRepository = new WorkerRepository(connection);

        assertDoesNotThrow(() -> workerRepository.delete(workerToDelete));
    }
}