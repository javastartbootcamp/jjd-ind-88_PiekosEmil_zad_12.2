import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {


    @Test
    void shouldCalculateStats() throws IOException {
        File file = new File("employees.csv");

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.append("Jan;Kowalski;123456789;Management;6000\n");
        fileWriter.append("Aneta;Zawadzka;432156789;it;5200\n");
        fileWriter.append("Karol;Kowalewski;5412328948;it;5200\n");
        fileWriter.append("Jan;Zarzecki;678628373674;Support;4800\n");
        fileWriter.close();

        // when
        Main.main(new String[]{});

        // then
        File statsFile = new File("stats.txt");
        assertThat(statsFile).exists();

        String fileContent = Files.readString(statsFile.toPath());

        assertThat(fileContent).contains("Średnia wypłata: 5000");
        assertThat(fileContent).contains("Najmniejsza wypłata: 4800");
        assertThat(fileContent).contains("Największa wypłata: 6000");
        assertThat(fileContent).contains("Łączna liczba pracowników: 4");
        assertThat(fileContent).contains("Liczba pracowników w dziale IT: 2");
        assertThat(fileContent).contains("Liczba pracowników w dziale Management: 1");
        assertThat(fileContent).contains("Liczba pracowników w dziale Support: 1");
    }

    @Test
    void shouldCalculateStatsOnlyOneEmployee() throws IOException {
        File file = new File("employees.csv");

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.append("Jan;Kowalski;123456789;it;6000\n");
        fileWriter.close();

        // when
        Main.main(new String[]{});

        // then
        File statsFile = new File("stats.txt");
        assertThat(statsFile).exists();

        String fileContent = Files.readString(statsFile.toPath());

        assertThat(fileContent).contains("Średnia wypłata: 6000");
        assertThat(fileContent).contains("Najmniejsza wypłata: 6000");
        assertThat(fileContent).contains("Największa wypłata: 6000");
        assertThat(fileContent).contains("Łączna liczba pracowników: 1");
        assertThat(fileContent).contains("Liczba pracowników w dziale IT: 1");
        assertThat(fileContent).contains("Liczba pracowników w dziale Management: 0");
        assertThat(fileContent).contains("Liczba pracowników w dziale Support: 0");
    }


    @BeforeEach
    public void init() throws IOException {
        createBackup();
    }

    @AfterEach
    public void cleanup() throws IOException {
        restoreBackup();
    }


    private void createBackup() throws IOException {
        File orgFile = new File("employees.csv");
        if (orgFile.exists()) {
            File backup = new File("backup.csv");
            backup.delete();
            Files.copy(orgFile.toPath(), backup.toPath());
        }
    }

    private void restoreBackup() throws IOException {
        File orgFile = new File("employees.csv");
        File backup = new File("backup.csv");
        orgFile.delete();
        if (backup.exists()) {
            Files.copy(backup.toPath(), orgFile.toPath());
            backup.delete();
        }
    }


}
