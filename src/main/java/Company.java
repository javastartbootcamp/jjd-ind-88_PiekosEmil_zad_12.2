import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Company {
    private static final int MAX_EMPLOYEES = 100;

    public static Employee[] readEmployees() {
        Employee[] employees = new Employee[MAX_EMPLOYEES];
        int index = 0;
        String firstName = null;
        String lastName = null;
        String id = null;
        String department = null;
        int salary = 0;

        File sourceFile = new File("employees.csv");
        try (FileReader fileReader = new FileReader(sourceFile);
             Scanner scan = new Scanner(fileReader).useDelimiter(";");) {
            while (scan.hasNext()) {
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
        return employees;
    }

    public static double averageSalary(Employee[] employees) {
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

    public static double lowestSalary(Employee[] employees) {
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

    public static double highestSalary(Employee[] employees) {
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

    public static String employeeOfDepartment(Employee[] employees) {
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


