import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Company {
    private static final int MAX_EMPLOYEES = 100;
    Employee[] employees = new Employee[MAX_EMPLOYEES];

    public void readEmployees() {
        int index = 0;
        String firstName = null;
        String lastName = null;
        String id = null;
        String department = null;
        int salary = 0;
        try {
            File sourceFile = new File("employees.csv");
            FileReader fileReader = new FileReader(sourceFile);
            Scanner scan = new Scanner(fileReader).useDelimiter(";");
            while (scan.hasNext() && scan != null) {
                firstName = scan.next();
                lastName = scan.next();
                id = scan.next();
                department = scan.next();
                scan.skip(";");
                salary = Integer.parseInt(scan.nextLine());
                employees[index] = new Employee(firstName, lastName, id, department, salary);
                index++;
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void printEmployees() {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                System.out.println(employees[i]);
            }
        }
    }

    public double averageSalary(Employee[] employees) {
        double sumSalary = 0;
        int employeesNumber = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                sumSalary += employees[i].getSalary();
                employeesNumber++;
            }
        }
        return sumSalary / employeesNumber;
    }

    public double lowestSalary(Employee[] employees) {
        int lowestSalary = employees[0].getSalary();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getSalary() < lowestSalary) {
                    lowestSalary = employees[i].getSalary();
                }
            }
        }
        return lowestSalary;
    }

    public double highestSalary(Employee[] employees) {
        int highestSalary = employees[0].getSalary();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getSalary() > highestSalary) {
                    highestSalary = employees[i].getSalary();
                }
            }
        }
        return highestSalary;
    }

    public String employeeOfDepartment(Employee[] employees) {
        int itEmployee = 0;
        int supportEmployee = 0;
        int managementEmployee = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getDepartment().equals("IT")) {
                    itEmployee++;
                } else if (employees[i].getDepartment().equals("Support")) {
                    supportEmployee++;
                } else if (employees[i].getDepartment().equals("Management")) {
                    managementEmployee++;
                }
            }
        }
        return "Liczba pracowników IT: " + itEmployee + "\n" +
                "Liczba pracowników Support: " + supportEmployee + "\n" +
                "Liczba pracowników Management: " + managementEmployee;
    }
}


