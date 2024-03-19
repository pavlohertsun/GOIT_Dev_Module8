package validators;

import entities.Client;

public class ClientValidator {
    public static boolean isValid(Client cLient){
        if(!isValidName(cLient.getName())) return false;
        return true;
    }
    private static boolean isValidName(String name){
        if(name.length() >= 2) return true;
        return false;
    }
}
