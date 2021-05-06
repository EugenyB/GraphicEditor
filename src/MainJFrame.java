
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu.Separator;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Main class of program - Main Window
 * @author <a href="http://google.com">Me</a>
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Конструктор по умолчанию
     * вызывает метод {@link #initComponents()} method
     */
    public MainJFrame() {
        initComponents();
    }

    /**
     * Creates window components and connects them with event handlers
     */
    private void initComponents() {

        buttonGroup1 = new ButtonGroup();
        jPopupMenu1 = new JPopupMenu();
        lineColorMenuItem = new JMenuItem();
        fillColorMenuItem = new JMenuItem();
        drawPanel = new MyPanel();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        openMenuItem = new JMenuItem();
        saveMenuItem = new JMenuItem();
        jSeparator1 = new Separator();
        exitMenuItem = new JMenuItem();
        jMenu2 = new JMenu();
        circleRadioButtonMenuItem = new JRadioButtonMenuItem();
        rectangleRadioButtonMenuItem = new JRadioButtonMenuItem();
        triangleRadioButtonMenuItem = new JRadioButtonMenuItem();
        jSeparator2 = new Separator();
        moveResizeRadioButtonMenuItem = new JRadioButtonMenuItem();
        jMenu3 = new JMenu();
        aboutMenuItem = new JMenuItem();

        lineColorMenuItem.setText("Line Color");
        lineColorMenuItem.addActionListener(this::lineColorMenuItemActionPerformed);
        jPopupMenu1.add(lineColorMenuItem);
        fillColorMenuItem.setText("Fill Color");
        fillColorMenuItem.addActionListener(this::fillColorMenuItemActionPerformed);
        jPopupMenu1.add(fillColorMenuItem);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        drawPanel.setComponentPopupMenu(jPopupMenu1);

        jMenu1.setText("File");

        openMenuItem.setText("Open...");
        openMenuItem.addActionListener(this::openMenuItemActionPerformed);
        jMenu1.add(openMenuItem);

        saveMenuItem.setText("Save...");
        saveMenuItem.addActionListener(this::saveMenuItemActionPerformed);
        jMenu1.add(saveMenuItem);
        jMenu1.add(jSeparator1);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(this::exitMenuItemActionPerformed);
        jMenu1.add(exitMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        buttonGroup1.add(circleRadioButtonMenuItem);
        circleRadioButtonMenuItem.setText("Circle");
        jMenu2.add(circleRadioButtonMenuItem);
        circleRadioButtonMenuItem.addActionListener(e -> startCircle());

        buttonGroup1.add(rectangleRadioButtonMenuItem);
        rectangleRadioButtonMenuItem.setText("Rectangle");
        jMenu2.add(rectangleRadioButtonMenuItem);
        rectangleRadioButtonMenuItem.addActionListener(e -> startRectangle());

        buttonGroup1.add(triangleRadioButtonMenuItem);
        triangleRadioButtonMenuItem.setText("Triangle");
        jMenu2.add(triangleRadioButtonMenuItem);
        triangleRadioButtonMenuItem.addActionListener(e -> startTriangle());

        jMenu2.add(jSeparator2);

        buttonGroup1.add(moveResizeRadioButtonMenuItem);
        moveResizeRadioButtonMenuItem.setText("Move / Resize");
        moveResizeRadioButtonMenuItem.setSelected(true);
        jMenu2.add(moveResizeRadioButtonMenuItem);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(this::aboutActionPerformed);
        jMenu3.add(aboutMenuItem);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        add(drawPanel);
        JPanel topPanel = new JPanel();
        lineColorPanel = new ColorPanel(Color.BLACK);
        topPanel.add(lineColorPanel);
        fillColorPanel = new ColorPanel(Color.WHITE);
        topPanel.add(fillColorPanel);
        topPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(topPanel, BorderLayout.NORTH);
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON1) return;
                if (moveResizeRadioButtonMenuItem.isSelected()) {
                    int x = e.getX();
                    int y = e.getY();

                    Figure f = drawPanel.findFigure(x,y);

                    if (f!=null) {
                        if (f.isSelected()) {
                            startMove(x,y);
                        }
                        drawPanel.selectFigure(f);
                    }
                }
                if (circleRadioButtonMenuItem.isSelected()) {
                    builder.processPoint(e.getX(), e.getY());
                }
                if (rectangleRadioButtonMenuItem.isSelected()) {
                    builder.processPoint(e.getX(), e.getY());
                }
                if (triangleRadioButtonMenuItem.isSelected()) {
                    Figure figure = builder.processPoint(e.getX(), e.getY());
                    if (figure != null) {
                        drawPanel.addFigure(figure);
                        repaint();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton()!=MouseEvent.BUTTON1) return;
                if (moveResizeRadioButtonMenuItem.isSelected()) {
                    int x = e.getX();
                    int y = e.getY();
                    finishMove(x,y);
                }
                if (circleRadioButtonMenuItem.isSelected()) {
                    Figure figure = builder.processPoint(e.getX(), e.getY());
                    drawPanel.addFigure(figure);
                }
                if (rectangleRadioButtonMenuItem.isSelected()) {
                    Figure figure = builder.processPoint(e.getX(), e.getY());
                    drawPanel.addFigure(figure);
                }
                if (triangleRadioButtonMenuItem.isSelected()) {
                    builder.processPoint(e.getX(), e.getY());
                }
                repaint();
            }
        });

        drawPanel.addMouseWheelListener(this::processWheel);

        builder = new FigureBuilder(drawPanel.getLineColor(), drawPanel.getFillColor());
        pack();
    }

    /**
     * Used for moving figure
     * When moving figure, first click - start point, second click - finish point
     * and move by dx = x2-x1, dy = y2-y1
     * @param x finish point x2
     * @param y finish point y2
     */
    private void finishMove(int x, int y) {
        drawPanel.finishMove(x,y);
    }

    /**
     * Used for moving figure
     * When moving figure, first click - start point, second click - finish point
     * and move by dx = x2-x1, dy = y2-y1
     * @param x start point x1
     * @param y start point y1
     */
    private void startMove(int x, int y) {
        drawPanel.setStartMovePoint(x,y);
    }

    /**
     * Reaction for mouse wheel
     * When mouse scrolls up - figure gets bigger, when scrolls down - smaller
     * @param e MouseWheelEvent - autogenerated less than 0 when scrolls up, greater than 0 when scrolls down
     */
    private void processWheel(MouseWheelEvent e) {
        Figure f;
        if ((f = drawPanel.getSelected())!=null) {
            if (e.getUnitsToScroll() > 0) {
                f.scale(0.9);
            } else {
                f.scale(1/0.9);
            }
        }
        repaint();
    }

    /**
     * Starts for triangle draw. Selects line and fill color from panel.
     * setups builder
     */
    private void startTriangle() {
        drawPanel.selectFigure(null);
        builder.setMode(FigureBuilder.Mode.TRIANGLE);
        builder.setLineColor(drawPanel.getLineColor());
        builder.setFillColor(drawPanel.getFillColor());
    }

    /**
     * Starts for circle draw. Selects line and fill color from panel.
     * setups builder
     */
    private void startCircle() {
        drawPanel.selectFigure(null);
        builder.setMode(FigureBuilder.Mode.CIRCLE);
        builder.setLineColor(drawPanel.getLineColor());
        builder.setFillColor(drawPanel.getFillColor());
    }

    /**
     * Starts for rectangle draw. Selects line and fill color from panel.
     * setups builder
     */
    private void startRectangle() {
        drawPanel.selectFigure(null);
        builder.setMode(FigureBuilder.Mode.RECTANGLE);
        builder.setLineColor(drawPanel.getLineColor());
        builder.setFillColor(drawPanel.getFillColor());
    }

    /**
     * Reaction for menu item "Save" - opens FileChooser Dialog and if it closes by Approve (OK) saving figures info to file
     * @param evt autogenerated
     */
    private void saveMenuItemActionPerformed(ActionEvent evt) {
        List<Figure> figures = drawPanel.getFigures();
        JFileChooser jFileChooser = new JFileChooser(".");
        if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = jFileChooser.getSelectedFile().getName();
            FileManager.writeToFile(figures, filename);
        }
    }

    /**
     * Reaction for menu item "Open" - opens FileChooser Dialog and if it closes by Approve (OK) reads figures info from file
     * @param evt autogenerated
     */
    private void openMenuItemActionPerformed(ActionEvent evt) {
        JFileChooser jFileChooser = new JFileChooser(".");
        if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = jFileChooser.getSelectedFile().getName();
            List<Figure> figures = FileManager.readFromFile(filename);
            drawPanel.setFigures(figures);
            drawPanel.repaint();
        }
    }

    /**
     * Shows about program dialog
     * @param evt autogenerated
     */
    private void aboutActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(rootPane, "This is my program\nGraphic Editor");
    }

    /**
     * Exits program
     * @param evt autogenerated
     */
    private void exitMenuItemActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    /**
     * Choosing color of figure's outline
     * if exits selected figure then color sets for it, otherwise - for next created figure
     * @param evt autogenerated
     */
    private void lineColorMenuItemActionPerformed(ActionEvent evt) {
        Figure f;
        if ((f = drawPanel.getSelected()) == null) {
            Color color = JColorChooser.showDialog(this, "Choose line color for new figures", drawPanel.getLineColor());
            drawPanel.setLineColor(color);
            builder.setLineColor(color);
            lineColorPanel.setPanelColor(color);
        } else {
            Color color = JColorChooser.showDialog(this, "Choose line color for the figure", f.getLineColor());
            f.setLineColor(color);
        }
        repaint();
    }

    /**
     * Choosing color of figure's fill
     * if exits selected figure then color sets for it, otherwise - for next created figure
     * @param evt autogenerated
     */
    private void fillColorMenuItemActionPerformed(ActionEvent evt) {
        Figure f;
        if ((f = drawPanel.getSelected()) == null) {
            Color color = JColorChooser.showDialog(this, "Choose fill color for new figures", drawPanel.getFillColor());
            drawPanel.setFillColor(color);
            builder.setFillColor(color);
            fillColorPanel.setPanelColor(color);
        } else {
            Color color = JColorChooser.showDialog(this, "Choose fill color for the figure", f.getFillColor());
            f.setFillColor(color);
        }
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainJFrame().setVisible(true));
    }

    /**
     * Object for build a new figure
     */
    private FigureBuilder builder;

    private ButtonGroup buttonGroup1;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenuBar jMenuBar1;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem aboutMenuItem;
    private JMenuItem lineColorMenuItem;
    private JMenuItem fillColorMenuItem;
    private MyPanel drawPanel;
    private JPopupMenu jPopupMenu1;
    private JRadioButtonMenuItem circleRadioButtonMenuItem;
    private JRadioButtonMenuItem rectangleRadioButtonMenuItem;
    private JRadioButtonMenuItem triangleRadioButtonMenuItem;
    private JRadioButtonMenuItem moveResizeRadioButtonMenuItem;
    private Separator jSeparator1;
    private Separator jSeparator2;
    private ColorPanel lineColorPanel;
    private ColorPanel fillColorPanel;

}
