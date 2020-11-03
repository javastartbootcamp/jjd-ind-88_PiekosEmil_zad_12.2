import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("employees.csv");

        if (file.exists()) {
            File stats = new File("stats.txt");
            stats.createNewFile();

//            FileWriter fileWriter = new FileWriter(stats);
//            fileWriter.write("Średnia wypłata: 5000");
//
//            fileWriter.close();
        }

    }
}
