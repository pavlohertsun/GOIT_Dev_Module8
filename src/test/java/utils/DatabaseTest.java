package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void getConnectionTest() {
        Assertions.assertNotNull(Database.getConnection());
    }
}