package validators;

import entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientValidatorTest {

    @Test
    void isValidTest() {
        Client client = new Client("a");

        boolean expectedResult = false;
        boolean actualResult = ClientValidator.isValid(client);

        Assertions.assertEquals(expectedResult, actualResult);
    }
}