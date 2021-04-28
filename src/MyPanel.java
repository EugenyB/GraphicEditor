import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel {

    private List<Figure> figures = new ArrayList<>();
    private Color lineColor = Color.BLACK;
    private Color fillColor = Color.WHITE;

    public MyPanel() {
        //figures = FileManager.readFromFile("figures.txt");
        // todo убрать в финальной версии
        //figures.add(new Circle(Color.BLUE, Color.WHITE, 50, new Point(100,100)));
        //figures.add(new Circle(Color.RED, Color.YELLOW, 70, new Point(200,200)));
        //figures.add(new Triangle(Color.RED, Color.BLUE, new Point(100,100), new Point(200, 200), new Point(350, 200)));
        //figures.add(new Rectangle(Color.GREEN, Color.MAGENTA, new Point(200,200), new Point(300, 300)));
        // ------------------------------
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
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

    public Figure findFigure(int x, int y) {
        Point p = new Point(x, y);
        for (int i = figures.size() - 1; i >= 0; i--) {
            if (figures.get(i).containsPoint(p)) return figures.get(i);
        }
        return null;
    }
}
