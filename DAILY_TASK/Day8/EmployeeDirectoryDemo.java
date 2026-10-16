package Day8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class EmployeeDirectoryDemo {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(104, "Maya Chen", "Engineering", 128000),
                new Employee(101, "Noah Patel", "Finance", 112000),
                new Employee(108, "Ava Johnson", "Engineering", 136000),
                new Employee(103, "Liam Garcia", "Human Resources", 98000),
                new Employee(106, "Zoe Kim", "Finance", 121000),
                new Employee(102, "Ethan Brown", "Engineering", 119000),
                new Employee(107, "Isla Davis", "Marketing", 105000),
                new Employee(105, "Oliver Wilson", "Engineering", 130000));

        demonstrateHashMapLookup(employees);
        printDepartmentReport(employees);
        printEmployeesById(employees);
        printTopFiveSalaries(employees);
        demonstrateHashCodeContract();
    }

    private static void demonstrateHashMapLookup(List<Employee> employees) {
        Map<Integer, Employee> employeesById = new HashMap<>();
        for (Employee employee : employees) {
            employeesById.put(employee.getId(), employee);
        }

        System.out.println("Employee lookup for ID 106: " + employeesById.get(106));
    }

    private static void printDepartmentReport(List<Employee> employees) {
        Map<String, Integer> employeeCounts = new TreeMap<>();
        for (Employee employee : employees) {
            employeeCounts.merge(employee.getDepartment(), 1, Integer::sum);
        }

        System.out.println("\nEmployees by department:");
        employeeCounts.forEach((department, count) ->
                System.out.printf("- %s: %d%n", department, count));
    }

    private static void printEmployeesById(List<Employee> employees) {
        List<Employee> sortedEmployees = new ArrayList<>(employees);
        sortedEmployees.sort(Comparator.naturalOrder());

        System.out.println("\nEmployees by ID:");
        sortedEmployees.forEach(employee -> System.out.println("- " + employee));
    }

    private static void printTopFiveSalaries(List<Employee> employees) {
        Comparator<Employee> salaryOrder = Comparator
                .comparingDouble(Employee::getSalary).reversed()
                .thenComparing(Employee::getDepartment)
                .thenComparing(Employee::getName)
                .thenComparingInt(Employee::getId);

        System.out.println("\nTop 5 salaries:");
        employees.stream()
                .sorted(salaryOrder)
                .limit(5)
                .forEach(employee -> System.out.printf("- %s | $%,.0f%n",
                        employee.getName(), employee.getSalary()));
    }

    private static void demonstrateHashCodeContract() {
        BrokenEmployee brokenFirst = new BrokenEmployee(201, "Sam Lee");
        BrokenEmployee brokenSecond = new BrokenEmployee(201, "Sam Lee");
        Set<BrokenEmployee> brokenSet = new HashSet<>();
        brokenSet.add(brokenFirst);
        brokenSet.add(brokenSecond);

        System.out.println("\nMissing hashCode demo:");
        System.out.println("Equal by ID: " + brokenFirst.equals(brokenSecond));
        System.out.println("Hash codes match: "
                + (brokenFirst.hashCode() == brokenSecond.hashCode()));
        System.out.println("HashSet size without hashCode override: " + brokenSet.size());

        Set<Employee> fixedSet = new HashSet<>();
        fixedSet.add(new Employee(201, "Sam Lee", "Support", 90000));
        fixedSet.add(new Employee(201, "Sam Lee", "Support", 90000));
        System.out.println("HashSet size with equals and hashCode: " + fixedSet.size());
    }
}

class Employee implements Comparable<Employee> {
    private final int id;
    private final String name;
    private final String department;
    private final double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getDepartment() {
        return department;
    }

    double getSalary() {
        return salary;
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(id, other.id);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) other;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %s | $%,.0f", id, name, department, salary);
    }
}

class BrokenEmployee {
    private final int id;
    private final String name;

    BrokenEmployee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BrokenEmployee)) {
            return false;
        }
        BrokenEmployee employee = (BrokenEmployee) other;
        return id == employee.id;
    }
}
