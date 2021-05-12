import java.awt.*;

/**
 * Abstract Closs for inheritance
 * contains fields:  {@link #lineColor}, {@link #fillColor}
 */
public abstract class Figure {
    /**
     * Color of outline
     */
    private Color lineColor;

    /**
     * Color of fill
     */
    private Color fillColor;

    /**
     * Selection state of figure
     */
    private boolean selected;

    /**
     * Constructs figure with specified line color and fill color
     * @param lineColor color of outline
     * @param fillColor color of fill
     */
    public Figure(Color lineColor, Color fillColor) {
        this.lineColor = lineColor;
        this.fillColor = fillColor;
        selected = false;
    }

    /**
     * Parsing figure from text line and creates object of appropriate subclass
     * @param line text for parsing
     * @return parsed Figure (subclass) or null if line does not contains description of figure
     */
    public static Figure parseFigure(String line) {
        String[] s = line.split(" ");
        switch (s[0]) {
            case "C": {
                double x = Double.parseDouble(s[1]);
                double y = Double.parseDouble(s[2]);
                int radius = Integer.parseInt(s[3]);
                int lineColor = Integer.valueOf(s[4], 16);
                int fillColor = Integer.valueOf(s[5], 16);

                return new Circle(new Color(lineColor), new Color(fillColor), radius, new Point(x, y));
            }
            case "R": {
                double x1 = Double.parseDouble(s[1]);
                double y1 = Double.parseDouble(s[2]);
                double x2 = Double.parseDouble(s[3]);
                double y2 = Double.parseDouble(s[4]);
                int lineColor = Integer.valueOf(s[5], 16);
                int fillColor = Integer.valueOf(s[6], 16);

                return new Rectangle(new Color(lineColor), new Color(fillColor), new Point(x1, y1), new Point(x2, y2));
            }
            case "T": {
                double x1 = Double.parseDouble(s[1]);
                double y1 = Double.parseDouble(s[2]);
                double x2 = Double.parseDouble(s[3]);
                double y2 = Double.parseDouble(s[4]);
                double x3 = Double.parseDouble(s[5]);
                double y3 = Double.parseDouble(s[6]);
                int lineColor = Integer.valueOf(s[7], 16);
                int fillColor = Integer.valueOf(s[8], 16);

                return new Triangle(new Color(lineColor), new Color(fillColor),
                        new Point(x1,y1), new Point(x2,y2), new Point(x3,y3));
            }
        }
        return null;
    }

    /**
     * Draws figure both: selected or not
     * @param g Graphic context
     */
    public void draw(Graphics2D g) {
        if (selected) {
            drawSelected(g);
        } else {
            drawSimple(g);
        }
    }

    /**
     * Draws selected figure
     * @param g Graphic context
     */
    public void drawSelected(Graphics2D g) {
        g.setStroke(new BasicStroke(3));
        drawSimple(g);
        g.setStroke(new BasicStroke(1));
    }

    /**
     * Draws figure
     * Abstract method for overriding in subclasses
     * @param g Graphic context
     */
    protected abstract void drawSimple(Graphics2D g);

    /**
     * Move figure on vector (dx, dy)
     * @param dx moving distance by X axis
     * @param dy moving distance by Y axis
     */
    protected abstract void move(double dx, double dy);

    /**
     * Scale figure relative to it's center
     * Center coordinates should be calculated as average of all point coordinates
     * @param factor scaling factor
     */
    protected abstract void scale(double factor);

    /**
     * Checks if point is inside of figure
     * @param point checked point
     * @return true - if figure contains point, false - otherwise
     */
    protected abstract boolean containsPoint(Point point);

    /**
     * Retrieves color of figure's outline
     * @return color of figure's outline
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * Sets color of figure's outline
     * @param lineColor new color of figure's outline
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * Retrieves color of figure's fill
     * @return color of figure's fill
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Sets color of figure's fill
     * @param fillColor new color of figure's fill
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * Checks if figure selected
     * @return true if figure selected, false - otherwise
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Changes selected state of figure
     * @param selected new state - selected (true) or not (false)
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
