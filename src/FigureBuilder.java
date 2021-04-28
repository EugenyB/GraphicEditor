import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FigureBuilder {

    public Mode mode = Mode.NONE;
    private Color lineColor;
    private Color fillColor;
    private List<Point> points = new ArrayList<>();

    public FigureBuilder(Color lineColor, Color fillColor) {
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        points.clear();
    }

    public Figure processPoint(int x, int y) {
        switch (mode) {
            case CIRCLE:
                if (points.isEmpty()) {
                    points.add(new Point(x, y));
                    return null;
                } else {
                    points.add(new Point(x, y));
                    Circle circle = createCircle(points);
                    points.clear();
                    return circle;
                }
            case RECTANGLE:
                if (points.isEmpty()) {
                    points.add(new Point(x, y));
                    return null;
                } else {
                    points.add(new Point(x, y));
                    Rectangle rectangle = createRectangle(points);
                    points.clear();
                    return rectangle;
                }
            case TRIANGLE:
                if (points.size() == 3) {
                    points.clear();
                    return null;
                } else {
                    if (points.size() < 2) {
                        points.add(new Point(x, y));
                        return null;
                    } else {
                        points.add(new Point(x, y));
                        return createTriangle(points);
                    }
                }
            default:
                return null;
        }
    }

    private Triangle createTriangle(List<Point> points) {
        Point p1 = new Point(points.get(0).getX(), points.get(0).getY());
        Point p2 = new Point(points.get(1).getX(), points.get(1).getY());
        Point p3 = new Point(points.get(2).getX(), points.get(2).getY());
        return new Triangle(lineColor, fillColor, p1, p2, p3);
    }

    private Rectangle createRectangle(List<Point> points) {
        Point p1 = new Point(points.get(0).getX(), points.get(0).getY());
        Point p2 = new Point(points.get(1).getX(), points.get(1).getY());
        return new Rectangle(lineColor, fillColor, p1, p2);
    }

    private Circle createCircle(List<Point> points) {
        int radius = (int) Math.hypot(points.get(0).getX() - points.get(1).getX(), points.get(0).getY() - points.get(1).getY());
        return new Circle(lineColor, fillColor, radius, points.get(0));
    }

    public enum Mode {NONE, CIRCLE, RECTANGLE, TRIANGLE}

}
