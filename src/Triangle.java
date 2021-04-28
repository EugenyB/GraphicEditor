import java.awt.*;
import java.util.Arrays;

public class Triangle extends Figure {
    private Point[] points;

    public Triangle(Color lineColor, Color fillColor, Point... pts) {
        super(lineColor, fillColor);
        points = Arrays.copyOf(pts,3);
    }

    @Override
    protected void drawSimple(Graphics2D g) {
        g.setColor(getFillColor());
        int[] x = {points[0].getX(), points[1].getX(), points[2].getX()};
        int[] y = {points[0].getY(), points[1].getY(), points[2].getY()};
        g.fillPolygon(x, y, 3);
        g.setColor(getLineColor());
        g.drawPolygon(x, y, 3);
    }

    @Override
    protected void move(int dx, int dy) {
        for (Point point : points) {
            point.setLocation(point.getX()+dx, point.getY()+dy);
        }
    }

    @Override
    protected void scale(double factor) {
        for (Point point : points) {
            point.setLocation((int)(point.getX()*factor), (int)(point.getY()*factor));
        }
    }

    @Override
    protected boolean containsPoint(Point point) {
        int x1 = points[0].getX();
        int y1 = points[0].getY();
        int x2 = points[1].getX();
        int y2 = points[1].getY();
        int x3 = points[2].getX();
        int y3 = points[2].getY();
        int x = point.getX();
        int y = point.getY();

        int s = area(x1, y1, x2, y2, x3, y3);
        int s1 = area(x, y, x2, y2, x3, y3);
        int s2 = area(x1, y1, x, y, x3, y3);
        int s3 = area(x1, y1, x2, y2, x, y);
        return s == s1 + s2 + s3;
    }

    private int area(int x1, int y1, int x2, int y2, int x3, int y3) {
        int ax = x2-x1;
        int bx = x3-x1;
        int ay = y2-y1;
        int by = y3-y1;
        return Math.abs(ax*by-bx*ay);
    }

    public String toString() {
        return "T " + points[0].getX() + " " + points[0].getY() + " "
                + points[1].getX() + " " + points[1].getY() + " "
                + points[2].getX() + " " + points[2].getY() + " "
                + Integer.toHexString(getLineColor().getRGB()).substring(2).toUpperCase() + " "
                + Integer.toHexString(getFillColor().getRGB()).substring(2).toUpperCase();
    }

}
