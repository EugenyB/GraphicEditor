/**
 * Point for using with figures
 */
public class Point {
    /**
     * X coordinate of point
     * double for minimize of transformation errors
     */
    private double x;

    /**
     * Y coordinate of point
     * double for minimize of transformation errors
     */
    private double y;

    /**
     * Creates Point by coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves X coordinate of point
     * @return x coordinate of point
     */
    public double getX() {
        return x;
    }

    /**
     * Sets X coordinate of point
     * @param x coordinate of point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Retrieves Y coordinate of point
     * @return y coordinate of point
     */
    public double getY() {
        return y;
    }

    /**
     * Sets Y coordinate of point
     * @param y coordinate of point
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets Point location
     * @param x x coordinate of location
     * @param y y coordinate of location
     */
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
