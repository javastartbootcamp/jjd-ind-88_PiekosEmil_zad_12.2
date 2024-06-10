import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Company company = new Company();
        File file = new File("employees.csv");

        if (file.exists()) {
            File stats = new File("stats.txt");
            stats.createNewFile();
            company.readEmployees();

            FileWriter fileWriter = new FileWriter(stats);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("Średnia wypłata: " +  company.averageSalary(company.employees));
            writer.newLine();
            writer.write("Minimalna wypłata: " + company.lowestSalary(company.employees));
            writer.newLine();
            writer.write("Maksymalna wypłata: " + company.highestSalary(company.employees));
            writer.newLine();
            writer.write(company.employeeOfDepartment(company.employees));

            writer.flush();
        }
    }
}
