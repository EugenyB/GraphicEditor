import java.awt.*;

public abstract class Figure {
    private Color lineColor;
    private Color fillColor;
    private boolean selected;

    public Figure(Color lineColor, Color fillColor) {
        this.lineColor = lineColor;
        this.fillColor = fillColor;
        selected = false;
    }

    public void draw(Graphics2D g) {
        if (selected) {
            drawSelected(g);
        } else {
            drawSimple(g);
        }
    }

    public void drawSelected(Graphics2D g) {
        g.setStroke(new BasicStroke(3));
        drawSimple(g);
        g.setStroke(new BasicStroke(1));
    }

    protected abstract void drawSimple(Graphics2D g);

    protected abstract void move(int dx, int dy);

    protected abstract void scale(double factor);

    protected abstract boolean containsPoint(Point point);

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
