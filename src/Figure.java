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

    public static Figure parseFigure(String line) {
        String[] s = line.split(" ");
        switch (s[0]) {
            case "C": {
                double x = Double.parseDouble(s[1]);
                double y = Double.parseDouble(s[2]);
                int radius = Integer.parseInt(s[3]);
                int lineColor = Integer.valueOf(s[4], 16);
                int fillColor = Integer.valueOf(s[5], 16);

                return new Circle(new Color(lineColor), new Color(fillColor), radius, new Point(x, y));
            }
            case "R": {
                double x1 = Double.parseDouble(s[1]);
                double y1 = Double.parseDouble(s[2]);
                double x2 = Double.parseDouble(s[3]);
                double y2 = Double.parseDouble(s[4]);
                int lineColor = Integer.valueOf(s[5], 16);
                int fillColor = Integer.valueOf(s[6], 16);

                return new Rectangle(new Color(lineColor), new Color(fillColor), new Point(x1, y1), new Point(x2, y2));
            }
            case "T": {
                double x1 = Double.parseDouble(s[1]);
                double y1 = Double.parseDouble(s[2]);
                double x2 = Double.parseDouble(s[3]);
                double y2 = Double.parseDouble(s[4]);
                double x3 = Double.parseDouble(s[5]);
                double y3 = Double.parseDouble(s[6]);
                int lineColor = Integer.valueOf(s[7], 16);
                int fillColor = Integer.valueOf(s[8], 16);

                return new Triangle(new Color(lineColor), new Color(fillColor),
                        new Point(x1,y1), new Point(x2,y2), new Point(x3,y3));
            }
        }
        return null;
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

    protected abstract void move(double dx, double dy);

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
