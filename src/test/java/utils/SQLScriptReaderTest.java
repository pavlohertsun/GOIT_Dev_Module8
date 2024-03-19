package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SQLScriptReaderTest {

    @Test
    void readScriptFromFileTest() {
        String expectedScript = "CREATE TABLE test_table(id BIGINT PRIMARY KEY,name VARCHAR(50) ); ";
        String actualScript = SQLScriptReader.readScriptFromFile("sql/test_sql_file.sql");

        Assertions.assertEquals(expectedScript, actualScript);
    }
}