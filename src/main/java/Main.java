import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File stats = new File("stats.txt");

        try (
                FileWriter fileWriter = new FileWriter(stats);
                BufferedWriter writer = new BufferedWriter(fileWriter);
        ) {
            Employee[] employees = Company.readEmployees();

            writer.write("Średnia wypłata: " + Company.averageSalary(employees));
            writer.newLine();
            writer.write("Minimalna wypłata: " + Company.lowestSalary(employees));
            writer.newLine();
            writer.write("Maksymalna wypłata: " + Company.highestSalary(employees));
            writer.newLine();
            writer.write(Company.employeeOfDepartment(employees));
        } catch (IOException e) {
            System.err.println("Nie odnaleziono pliku");
        }
    }
}
