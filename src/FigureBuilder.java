import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Object for building figure step by step
 */
public class FigureBuilder {

    /**
     * Mode of creating figure:
     * @see Mode
     */
    public Mode mode = Mode.NONE;

    /**
     * Color of outline for new figure
     */
    private Color lineColor;

    /**
     * Color of fill for new figure
     */
    private Color fillColor;

    /**
     * points for new figure
     */
    private final List<Point> points = new ArrayList<>();

    /**
     * Creates new figure builder for next figure
     * @param lineColor color of figure's outline
     * @param fillColor color of figure's fill
     */
    public FigureBuilder(Color lineColor, Color fillColor) {
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }

    /**
     * Change outline color of next figure
     * @param lineColor selected color
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * Change fill color of next figure
     * @param fillColor selected color
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * Change mode for creation of new figure
     * @param mode mode of creation @see{@link Mode}
     */
    public void setMode(Mode mode) {
        this.mode = mode;
        points.clear();
    }

    /**
     * Reacts for adding new point
     * @param x coordinate x
     * @param y coordinate y
     * @return figure, if figure was created or null if still lack of points
     */
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

    /**
     * Creates Triangle by list of points
     * @param points list of point (must contain 3 points)
     * @return created Triangle
     */
    private Triangle createTriangle(List<Point> points) {
        Point p1 = points.get(0);
        Point p2 = points.get(1);
        Point p3 = points.get(2);
        return new Triangle(lineColor, fillColor, p1, p2, p3);
    }

    /**
     * Creates Rectangle by list of points
     * @param points list of point (must contain 2 points)
     * @return created Rectangle
     */
    private Rectangle createRectangle(List<Point> points) {
        return new Rectangle(lineColor, fillColor, points.get(0), points.get(1));
    }

    /**
     * Creates Circle by list of points.
     * First point - will be center, second point - is point on Circle's outline, so radius may be calculated
     * @param points list of point (must contain 2 points)
     * @return created Circle
     */
    private Circle createCircle(List<Point> points) {
        int radius = (int) Math.hypot(points.get(0).getX() - points.get(1).getX(), points.get(0).getY() - points.get(1).getY());
        return new Circle(lineColor, fillColor, radius, points.get(0));
    }

    /**
     * Modes for figure creation:
     * <ul>
     * <li>NONE - editing</li>
     * <li>CIRCLE - creation of circle</li>
     * <li>RECTANGLE - creation of rectangle</li>
     * <li>TRIANGLE - creation of triangle</li>
     * </ul>
     */
    public enum Mode {NONE, CIRCLE, RECTANGLE, TRIANGLE}

}
