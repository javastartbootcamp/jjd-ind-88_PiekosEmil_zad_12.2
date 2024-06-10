import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Company {
    private static final int MAX_EMPLOYEES = 100;
    Employee[] employees = new Employee[MAX_EMPLOYEES];

    public void readEmployees() {
        try {
            File sourceFile = new File("employees.csv");
            FileReader fileReader = new FileReader(sourceFile);
            Scanner scan = new Scanner(fileReader).useDelimiter(";");
            String firstName = scan.next();
            String lastName = scan.next();
            String id = scan.next();
            String department = scan.next();
            int salary = scan.nextInt();
            scan.nextLine();

            for (int i = 0; i < employees.length; i++) {
                employees[i] = new Employee(firstName, lastName, id, department, salary);
            }

        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void printEmployees() {
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }
    }
}


