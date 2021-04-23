import java.awt.*;

public class Circle extends Figure {
    private Point center;
    private int radius;

    public Circle(Point center, int radius, Color lineColor, Color fillColor) {
        super(lineColor, fillColor);
        this.center = center;
        this.radius = radius;
    }

    @Override
    protected void drawSimple(Graphics2D g) {
        int x = center.getX() - radius;
        int y = center.getY() - radius;
        int diameter = 2 * radius;

        g.setColor(getFillColor());
        g.fillOval(x, y, diameter, diameter);
        g.setColor(getLineColor());
        g.drawOval(x, y, diameter, diameter);
    }

    @Override
    public void move(int dx, int dy) {
        center = new Point(center.getX() + dx, center.getY() + dy);
    }

    @Override
    public void scale(double factor) {
        radius *= factor;
    }

    @Override
    public boolean containsPoint(Point point) {
        double distance = Math.hypot(center.getX() - point.getX(), center.getY() - point.getY());
        return distance <= radius;
    }
}
