import javax.swing.*;
import java.awt.*;

/**
 * Small panel for show colors of outline or fill
 */
public class ColorPanel extends JPanel {
    /**
     * color of panel
     */
    private Color color;

    /**
     * Creates panel with this color
     * @param color color of panel
     */
    public ColorPanel(Color color) {
        this.color = color;
    }

    /**
     * Overriding method for panel - setting it's size  to 100 x 25
     * @return dimension of panel
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 25);
    }

    /**
     * Redraw panel
     * @param g graphic context
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(0,0,getWidth(),getHeight());
    }

    /**
     * Sets chosen color for panel
     * @param color chosen color
     */
    public void setPanelColor(Color color) {
        this.color = color;
    }
}
