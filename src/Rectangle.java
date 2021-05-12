import java.awt.*;
import java.util.Arrays;

/**
 * Rectangle - subclass of Figure
 * contains two Points - opposite vertexes
 */
public class Rectangle extends Figure{
    /**
     * Points representing rectangle's diagonal
     */
    private Point[] points;

    /**
     * Constructor for Rectangle
     * @param lineColor initial color of Circle's outline
     * @param fillColor initial color of Circle's fill
     * @param pts points for diagonal vertexes
     */
    public Rectangle(Color lineColor, Color fillColor, Point... pts) {
        super(lineColor, fillColor);
        points = Arrays.copyOf( pts, 2);
    }

    /**
     * Draws simple (not selected) Rectangle
     * @param g Graphic context
     */
    @Override
    protected void drawSimple(Graphics2D g) {
        g.setColor(getFillColor());
        int x1 = (int)Double.min(points[0].getX(), points[1].getX());
        int y1 = (int)Double.min(points[0].getY(), points[1].getY());
        int x2 = (int)Double.max(points[0].getX(), points[1].getX());
        int y2 = (int)Double.max(points[0].getY(), points[1].getY());
        g.fillRect(x1 , y1, x2-x1, y2-y1);
        g.setColor(getLineColor());
        g.drawRect(x1 , y1, x2-x1, y2-y1);
    }

    /**
     * Moves Rectangle
     * @param dx moving distance by X axis
     * @param dy moving distance by Y axis
     */
    @Override
    protected void move(double dx, double dy) {
        for (Point point : points) {
            point.setLocation(point.getX()+dx, point.getY()+dy);
        }
    }

    /**
     * Scales Rectangle
     * @param factor scaling factor
     */
    @Override
    protected void scale(double factor) {
        double sumX = 0;
        double sumY = 0;
        for (Point point : points) {
            sumX += point.getX();
            sumY += point.getY();
        }
        sumX /= 2;
        sumY /= 2;

        move(-sumX, -sumY);
        for (Point point : points) {
            point.setLocation(point.getX()*factor, point.getY()*factor);
        }
        move(sumX, sumY);
    }

    /**
     * Checks if point inside of Rectangle
     * @param point checked point
     * @return true if point is inside or false otherwise
     */
    @Override
    protected boolean containsPoint(Point point) {
        int x1 = (int) Double.min(points[0].getX(), points[1].getX());
        int y1 = (int) Double.min(points[0].getY(), points[1].getY());
        int x2 = (int) Double.max(points[0].getX(), points[1].getX());
        int y2 = (int) Double.max(points[0].getY(), points[1].getY());
        double x = point.getX();
        double y = point.getY();

        return x>=x1 && x <=x2 && y>=y1 && y<=y2;
    }

    /**
     * Text representation of Rectangle for writing to text file
     * @return text representation of Circle
     */
    public String toString() {
        return "R " + points[0].getX() + " " + points[0].getY() + " "
                + points[1].getX() + " " + points[1].getY() + " "
                + Integer.toHexString(getLineColor().getRGB()).substring(2).toUpperCase() + " "
                + Integer.toHexString(getFillColor().getRGB()).substring(2).toUpperCase();
    }
}
