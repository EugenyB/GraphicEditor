import java.awt.*;
import java.util.Arrays;

public class Rectangle extends Figure{
    private Point[] points;
    public Rectangle(Color lineColor, Color fillColor, Point... pts) {
        super(lineColor, fillColor);
        points = Arrays.copyOf( pts, 2);
    }

    @Override
    protected void drawSimple(Graphics2D g) {
        g.setColor(getFillColor());
        int x1 = Integer.min(points[0].getX(), points[1].getX());
        int y1 = Integer.min(points[0].getY(), points[1].getY());
        int x2 = Integer.max(points[0].getX(), points[1].getX());
        int y2 = Integer.max(points[0].getY(), points[1].getY());
        g.fillRect(x1 , y1, x2-x1, y2-y1);
        g.setColor(getLineColor());
        g.drawRect(x1 , y1, x2-x1, y2-y1);
    }

    @Override
    protected void move(int dx, int dy) {
        for (Point point : points) {
            point.setLocation(point.getX()+dx, point.getY()+dy);
        }
    }

    @Override
    protected void scale(double factor) {
        int sumX = 0;
        int sumY = 0;
        for (Point point : points) {
            sumX += point.getX();
            sumY += point.getY();
        }
        sumX /= 2;
        sumY /= 2;

        move(-sumX, -sumY);
        for (Point point : points) {
            point.setLocation((int)(point.getX()*factor), (int)(point.getY()*factor));
        }
        move(sumX, sumY);
    }

    @Override
    protected boolean containsPoint(Point point) {
        int x1 = Integer.min(points[0].getX(), points[1].getX());
        int y1 = Integer.min(points[0].getY(), points[1].getY());
        int x2 = Integer.max(points[0].getX(), points[1].getX());
        int y2 = Integer.max(points[0].getY(), points[1].getY());
        int x = point.getX();
        int y = point.getY();

        return x>=x1 && x <=x2 && y>=y1 && y<=y2;
    }

    public String toString() {
        return "R " + points[0].getX() + " " + points[0].getY() + " "
                + points[1].getX() + " " + points[1].getY() + " "
                + Integer.toHexString(getLineColor().getRGB()).substring(2).toUpperCase() + " "
                + Integer.toHexString(getFillColor().getRGB()).substring(2).toUpperCase();
    }
}
