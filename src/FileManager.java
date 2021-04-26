import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileManager {
    public static void writeToFile(List<Figure> figures, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Figure figure : figures) {
                writer.println(figure.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Figure> readFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<Figure> figures = new ArrayList<>();
            String line;
            while ((line = reader.readLine())!= null) {
                Figure f = Figure.parseFigure(line);
                figures.add(f);
            }
            return figures;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File does not contain figures");
            return Collections.emptyList();
        }
    }
}
