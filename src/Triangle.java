import java.awt.*;
import java.util.Arrays;

/**
 * Triangle - subclass of Figure
 * contains Three Points: vertexes of triangle
 */
public class Triangle extends Figure {
    /**
     * Array of vertexes of triangle
     */
    private final Point[] points;

    /**
     * Constructor of Triangle
     * @param lineColor initial color of Circle's outline
     * @param fillColor initial color of Circle's fill
     * @param pts vertexes of triangle
     */
    public Triangle(Color lineColor, Color fillColor, Point... pts) {
        super(lineColor, fillColor);
        points = Arrays.copyOf(pts,3);
    }

    /**
     * Draws simple (not selected) Triangle
     * @param g Graphic context
     */
    @Override
    protected void drawSimple(Graphics2D g) {
        g.setColor(getFillColor());
        int[] x = {(int) points[0].getX(), (int) points[1].getX(), (int) points[2].getX()};
        int[] y = {(int) points[0].getY(), (int) points[1].getY(), (int) points[2].getY()};
        g.fillPolygon(x, y, 3);
        g.setColor(getLineColor());
        g.drawPolygon(x, y, 3);
    }

    /**
     * Moves Triangle
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
     * Scales Triangle
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
        sumX /= 3;
        sumY /= 3;

        move(-sumX, -sumY);
        for (Point point : points) {
            point.setLocation(point.getX()*factor, point.getY()*factor);
        }
        move(sumX, sumY);
    }

    /**
     * Checks if point inside of Triangle.
     * Area based check is performing
     * @param point checked point
     * @return true if point is inside or false otherwise
     */
    @Override
    protected boolean containsPoint(Point point) {
        double x1 = points[0].getX();
        double y1 = points[0].getY();
        double x2 = points[1].getX();
        double y2 = points[1].getY();
        double x3 = points[2].getX();
        double y3 = points[2].getY();
        double x = point.getX();
        double y = point.getY();

        double s = area(x1, y1, x2, y2, x3, y3);
        double s1 = area(x, y, x2, y2, x3, y3);
        double s2 = area(x1, y1, x, y, x3, y3);
        double s3 = area(x1, y1, x2, y2, x, y);
        return Math.abs(s-s1-s2-s3) < 1e-9;
    }

    /**
     * Calculates doubled area of triangle for check of point inside
     * @param x1 x coordinate for 1 vertex
     * @param y1 y coordinate for 1 vertex
     * @param x2 x coordinate for 2 vertex
     * @param y2 y coordinate for 2 vertex
     * @param x3 x coordinate for 3 vertex
     * @param y3 y coordinate for 3 vertex
     * @return doubled area of triangle
     */
    private double area(double x1, double y1, double x2, double y2, double x3, double y3) {
        double ax = x2-x1;
        double bx = x3-x1;
        double ay = y2-y1;
        double by = y3-y1;
        return Math.abs(ax*by-bx*ay);
    }

    /**
     * Text representation of Triangle for writing to text file
     * @return text representation of Triangle
     */
    public String toString() {
        return "T " + points[0].getX() + " " + points[0].getY() + " "
                + points[1].getX() + " " + points[1].getY() + " "
                + points[2].getX() + " " + points[2].getY() + " "
                + Integer.toHexString(getLineColor().getRGB()).substring(2).toUpperCase() + " "
                + Integer.toHexString(getFillColor().getRGB()).substring(2).toUpperCase();
    }

}
