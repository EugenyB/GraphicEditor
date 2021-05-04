import java.awt.*;

public class Circle extends Figure {
    private Point center;
    private int radius;

    public Circle(Color lineColor, Color fillColor, int radius, Point center) {
        super(lineColor, fillColor);
        this.center = center;
        this.radius = radius;
    }

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

    @Override
    public void move(double dx, double dy) {
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

    @Override
    public String toString() {
        return "C " + center.getX() + " " + center.getY() + " " + radius + " "
                + Integer.toHexString(getLineColor().getRGB()).substring(2).toUpperCase() + " "
                + Integer.toHexString(getFillColor().getRGB()).substring(2).toUpperCase();
    }
}
