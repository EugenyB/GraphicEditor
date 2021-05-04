import javax.swing.*;
import java.awt.*;

public class ColorPanel extends JPanel {

    private Color color;

    public ColorPanel(Color color) {
        this.color = color;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 25);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(0,0,getWidth(),getHeight());
    }

    public void setPanelColor(Color color) {
        this.color = color;
    }
}
