package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SQLScriptReader {
    public static String readScriptFromFile(String fileName){
        StringBuffer script = new StringBuffer();
        try(Scanner scanner = new Scanner(new File(fileName))){
            while(scanner.hasNext()){
                script.append(scanner.nextLine() + " ");
            }
        } catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        return script.toString().replaceAll("\\s{2,}", "");
    }
}