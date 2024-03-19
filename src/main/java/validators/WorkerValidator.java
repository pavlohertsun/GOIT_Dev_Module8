package validators;

import entities.Worker;

import java.time.LocalDate;

public class WorkerValidator {
    public static boolean isValid(Worker worker) {
        if (!isValidName(worker.getName())
                | !isValidBirthday(worker.getBirthday())
                | !isValidLevel(worker.getLevel())
                | !isValidSalary(worker.getSalary())) return false;
        return true;
    }
    private static boolean isValidName(String name){
        if(name.length() >= 2) return true;
        return false;
    }
    private static boolean isValidBirthday(LocalDate date){
        if(date.isAfter(LocalDate.of(1990, 1, 1))) return true;
        return false;
    }
    private static boolean isValidLevel(String checkingLevel){
        String[] validLevels = {"Trainee", "Junior", "Middle", "Senior"};
        for (String level : validLevels) {
            if (level.equals(checkingLevel)){
                return true;
            }
        }
        return false;
    }
    private static boolean isValidSalary(Double salary){
        if(salary > 100 && salary < 100000) return true;
        return false;
    }

}
