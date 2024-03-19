package dtos;

import java.util.Objects;

public class MaxSalaryWorkerDto {
    private String name;
    private double salary;
    public MaxSalaryWorkerDto(String output){
        String[] parts = output.split(" ");
        name = parts[0];
        salary = Double.parseDouble(parts[1]);
    }

    @Override
    public String toString(){
        return "Name of the worker: " + name + "; His salary: " + salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaxSalaryWorkerDto that = (MaxSalaryWorkerDto) o;
        return Double.compare(that.salary, salary) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary);
    }
}
