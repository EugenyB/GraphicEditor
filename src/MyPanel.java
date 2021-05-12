import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main panel of graphic editor.
 * All drawing operations performs on it
 */
public class MyPanel extends JPanel {

    /**
     * List of figures, that draws on panel
     */
    private List<Figure> figures = new ArrayList<>();

    /**
     * Current color of new figure outline
     */
    private Color lineColor = Color.BLACK;

    /**
     * Current color of new figure fill
     */
    private Color fillColor = Color.WHITE;

    /**
     * Point for performing move operations
     */
    private Point startMovePoint = null;

    /**
     * Overridden method for preferred size - this panel prefers size(800x600)
     * @return preferred size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    /**
     * List of all figures
     * @return list of figures, currently drawing in panel
     */
    public List<Figure> getFigures() {
        return figures;
    }

    /**
     * Sets list of figures
     * @param figures new list of figures
     */
    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    /**
     * Adds figure to panel. Stores it in figures list
     * @param figure new adding figure
     */
    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    /**
     * Gets current outline color for new figure
     * @return current outline color for new figure
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * Sets current outline color for new figure
     * @param lineColor new outline color for new figure
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * Gets current fill color for new figure
     * @return current fill color for new figure
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Sets current fill color for new figure
     * @param fillColor new fill color for new figure
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * Overridden method for draw panel.
     * Draws standard panel and all figures on it
     * @param g Graphic context
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFigures(g, figures);
    }

    /**
     * Draws all figures on selected context
     * @param g Graphic context
     * @param figures list of Figures
     */
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

    /**
     * Sets figure selected.
     * Other figures sets unselected
     * @param f figure for selection
     */
    public void selectFigure(Figure f) {
        for (Figure figure : figures) {
            figure.setSelected(f == figure);
        }
        repaint();
    }

    /**
     * Search for selected figure
     * @return selected Figure or null if no selected figure
     */
    public Figure getSelected() {
        for (Figure figure : figures) {
            if (figure.isSelected()) return figure;
        }
        return null;
    }

    /**
     * Sets point for start move
     * @param x x coordinate for start point of move
     * @param y y coordinate for start point of move
     */
    public void setStartMovePoint(int x, int y) {
        startMovePoint = new Point(x,y);
    }

    /**
     * Finishes point move.
     * If no start point - do nothing.
     * If no selected figure - do nothing.
     * Calculate vector(dx,dy) for moving and performs move on selected figure
     * @param x x coordinate for finish point of move
     * @param y y coordinate for finish point of move
     */
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
