import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Performs IO operations
 */
public class FileManager {
    /**
     * Stores list of figure to text file
     * @param figures list of Figures
     * @param filename file name for save data
     */
    public static void writeToFile(List<Figure> figures, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Figure figure : figures) {
                writer.println(figure.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads list of figure from text file
     * @param filename file name for read data
     * @return list of Figures or empty list and show message that file is wrong
     */
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
