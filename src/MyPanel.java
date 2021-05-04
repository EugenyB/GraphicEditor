import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel {

    private List<Figure> figures = new ArrayList<>();
    private Color lineColor = Color.BLACK;
    private Color fillColor = Color.WHITE;

    public MyPanel() {
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

    public void selectFigure(Figure f) {
        for (Figure figure : figures) {
            figure.setSelected(f == figure);
        }
        repaint();
    }

    public Figure getSelected() {
        for (Figure figure : figures) {
            if (figure.isSelected()) return figure;
        }
        return null;
    }
}
