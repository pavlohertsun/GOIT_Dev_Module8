package services;

import org.flywaydb.core.Flyway;
import properties.PropsReader;

public class DatabaseInitPopulateService {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().dataSource(PropsReader.createConnectionUrl(), PropsReader.readDBLogin(), PropsReader.readDBPassword()).load();
        flyway.migrate();
    }
}
