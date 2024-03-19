package properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsReader {
    private static final String FILE_NAME = "db.properties";
    public static String readDBLogin() {
        try {
            Properties prop = new Properties();
            prop.load(createInputStream());
            return prop.getProperty("db.login");
        }
        catch (IOException ex){
            System.out.println("error while reading");
            return null;
        }
    }
    public static String readDBPassword(){
        try{
            Properties prop = new Properties();
            prop.load(createInputStream());
            return prop.getProperty("db.password");
        } catch (IOException ex){
            System.out.println("Error while reading");
            return null;
        }
    }
    public static String createConnectionUrl(){
        try{
            Properties prop = new Properties();
            prop.load(createInputStream());
            return new StringBuilder("jdbc:postgresql://")
                    .append(prop.getProperty("db.host"))
                    .append(":")
                    .append(prop.getProperty("db.port"))
                    .append("/")
                    .append(prop.getProperty("db.database"))
                    .toString();
        } catch (IOException ex){
            System.out.println("Error while reading");
            return null;
        }
    }

    public static InputStream createInputStream(){
        InputStream is = PropsReader.class
                .getClassLoader().getResourceAsStream(FILE_NAME);
        if(is == null) {
            System.out.println("empty stream");
            return null;
        }
        return is;
    }
}
