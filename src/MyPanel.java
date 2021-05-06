import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel {

    private List<Figure> figures = new ArrayList<>();
    private Color lineColor = Color.BLACK;
    private Color fillColor = Color.WHITE;

    private Point startMovePoint = null;

    public MyPanel() {
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
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

    /**
     * Search for figure by coordinates
     * @param x x coordinate
     * @param y y coordinate
     * @return found figure or null if no figure contains this point
     */
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

    public void setStartMovePoint(int x, int y) {
        startMovePoint = new Point(x,y);
    }

    public void finishMove(int x, int y) {
        if (startMovePoint == null) return;
        Figure figure = getSelected();
        if (figure == null) return;
        double dx = x - startMovePoint.getX();
        double dy = y - startMovePoint.getY();
        figure.move(dx, dy);
        startMovePoint = null;
    }
}
