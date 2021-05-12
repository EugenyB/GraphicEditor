import java.awt.*;

/**
 * Circle - subclass of Figure
 * contains Point - center
 * and int radius
 */
public class Circle extends Figure {
    /**
     * Center of Circle
     */
    private Point center;

    /**
     * Radius of Circle
     */
    private int radius;

    /**
     * Constructor of Circle
     * @param lineColor initial color of Circle's outline
     * @param fillColor initial color of Circle's fill
     * @param radius initial radius
     * @param center initial center point
     */
    public Circle(Color lineColor, Color fillColor, int radius, Point center) {
        super(lineColor, fillColor);
        this.center = center;
        this.radius = radius;
    }

    /**
     * Draws simple (not selected) Circle
     * @param g Graphic context
     */
    @Override
    protected void drawSimple(Graphics2D g) {
        double x = center.getX() - radius;
        double y = center.getY() - radius;
        int diameter = 2 * radius;

        g.setColor(getFillColor());
        g.fillOval((int)x, (int)y, diameter, diameter);
        g.setColor(getLineColor());
        g.drawOval((int)x, (int)y, diameter, diameter);
    }

    /**
     * Moves Circle
     * @param dx moving distance by X axis
     * @param dy moving distance by Y axis
     */
    @Override
    public void move(double dx, double dy) {
        center = new Point(center.getX() + dx, center.getY() + dy);
    }

    /**
     * Scales Circle
     * @param factor scaling factor
     */
    @Override
    public void scale(double factor) {
        radius *= factor;
    }

    /**
     * Checks if point inside of Circle
     * @param point checked point
     * @return true if point is inside or false otherwise
     */
    @Override
    public boolean containsPoint(Point point) {
        double distance = Math.hypot(center.getX() - point.getX(), center.getY() - point.getY());
        return distance <= radius;
    }

    /**
     * Text representation of Circle for writing to text file
     * @return text representation of Circle
     */
    @Override
    public String toString() {
        return "C " + center.getX() + " " + center.getY() + " " + radius + " "
                + Integer.toHexString(getLineColor().getRGB()).substring(2).toUpperCase() + " "
                + Integer.toHexString(getFillColor().getRGB()).substring(2).toUpperCase();
    }
}
