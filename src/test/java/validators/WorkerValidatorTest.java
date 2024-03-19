package validators;

import entities.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WorkerValidatorTest {

    @Test
    void isValid() {
        Worker worker = new Worker(6, "Emma", LocalDate.of(1994, 6, 1), "Junior", 1500);

        boolean expectedResult = true;
        boolean actualResult = WorkerValidator.isValid(worker);

        Assertions.assertEquals(expectedResult, actualResult);
    }
}