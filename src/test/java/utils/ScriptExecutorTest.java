package utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScriptExecutorTest {
    @BeforeAll
    static void init(){
        ScriptExecutor scriptExecutor = new ScriptExecutor(Database.getConnection());
        scriptExecutor.executeUpdate("CREATE TABLE test_table(id BIGINT PRIMARY KEY,name VARCHAR(50));");
    }
    @Test
    void executeUpdateTest() {
        ScriptExecutor scriptExecutor = new ScriptExecutor(Database.getConnection());
        int expectedResult = 1;
        int actualResult = scriptExecutor.executeUpdate("INSERT INTO test_table VALUES(1, 'Pavlo');");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void executeQueryTest() {
        ScriptExecutor scriptExecutor = new ScriptExecutor(Database.getConnection());

        scriptExecutor.executeUpdate("INSERT INTO test_table VALUES(1, 'Pavlo');");

        String expectedResult = "1 Pavlo \n";
        String actualResult = scriptExecutor.executeQuery("SELECT * FROM test_table;");

        Assertions.assertEquals(expectedResult, actualResult);
    }
    @AfterAll
    static void clear(){
        ScriptExecutor scriptExecutor = new ScriptExecutor(Database.getConnection());
        scriptExecutor.executeUpdate("DROP TABLE test_table;");
    }
}