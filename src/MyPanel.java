import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel {

    private List<Figure> figures;

    public MyPanel() {
        figures = new ArrayList<>();
        // todo убрать в финальной версии
        figures.add(new Circle(new Point(100,100), 50, Color.BLUE, Color.WHITE));
        figures.add(new Circle(new Point(200,200), 70, Color.RED, Color.YELLOW));
        Point[] pts = { new Point(100,100), new Point(200, 200), new Point(350, 200) };
        figures.add(new Triangle(pts, Color.RED, Color.BLUE));
        // ------------------------------
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFigures(g, figures);
    }

    private void drawFigures(Graphics g, List<Figure> figures) {
        if (figures!=null) {
            for (Figure figure : figures) {
                figure.draw((Graphics2D) g);
            }
        }
    }
}
