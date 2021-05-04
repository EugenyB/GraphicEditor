
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu.Separator;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 *
 * @author
 */
public class MainJFrame extends javax.swing.JFrame {

    public MainJFrame() {
        initComponents();
    }

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

        GroupLayout jPanel1Layout;
        jPanel1Layout = new GroupLayout(drawPanel);
        drawPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(Alignment.LEADING)
                        .addGap(0, 474, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(Alignment.LEADING)
                        .addGap(0, 328, Short.MAX_VALUE)
        );

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

//        GroupLayout layout = new GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addContainerGap()
//                                .addComponent(drawPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addContainerGap())
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addContainerGap()
//                                .addComponent(drawPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addContainerGap())
//        );
        add(drawPanel);
        JPanel topPanel = new JPanel();
        lineColorPanel = new ColorPanel(Color.BLACK);
        topPanel.add(lineColorPanel);
        fillColorPanel = new ColorPanel(Color.WHITE);
        topPanel.add(fillColorPanel);
        topPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(topPanel, BorderLayout.NORTH);
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON1) return;
                if (moveResizeRadioButtonMenuItem.isSelected()) {
                    int x = e.getX();
                    int y = e.getY();
                    //System.out.println(">>>" + x + " " + y);
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
                    drawPanel.finishMove(x,y);
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

    private void startMove(int x, int y) {
        drawPanel.setStartMovePoint(x,y);
    }

    private void processWheel(MouseWheelEvent e) {
        Figure f;
        if ((f = drawPanel.getSelected())!=null) {
            //System.out.println("u>" + e.getUnitsToScroll());
            if (e.getUnitsToScroll()<0) {
                f.scale(0.9);
            } else {
                f.scale(1/0.9);
            }
        }
        repaint();
    }

    private void startTriangle() {
        drawPanel.selectFigure(null);
        builder.setMode(FigureBuilder.Mode.TRIANGLE);
        builder.setLineColor(drawPanel.getLineColor());
        builder.setFillColor(drawPanel.getFillColor());
    }

    private void startCircle() {
        drawPanel.selectFigure(null);
        builder.setMode(FigureBuilder.Mode.CIRCLE);
        builder.setLineColor(drawPanel.getLineColor());
        builder.setFillColor(drawPanel.getFillColor());
    }

    private void startRectangle() {
        drawPanel.selectFigure(null);
        builder.setMode(FigureBuilder.Mode.RECTANGLE);
        builder.setLineColor(drawPanel.getLineColor());
        builder.setFillColor(drawPanel.getFillColor());
    }

    private void saveMenuItemActionPerformed(ActionEvent actionEvent) {
        List<Figure> figures = drawPanel.getFigures();
        JFileChooser jFileChooser = new JFileChooser(".");
        if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = jFileChooser.getSelectedFile().getName();
            FileManager.writeToFile(figures, filename);
        }
    }

    private void openMenuItemActionPerformed(ActionEvent evt) {
        JFileChooser jFileChooser = new JFileChooser(".");
        if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = jFileChooser.getSelectedFile().getName();
            List<Figure> figures = FileManager.readFromFile(filename);
            drawPanel.setFigures(figures);
            drawPanel.repaint();
        }
    }

    private void aboutActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(rootPane, "This is my program");
    }

    private void exitMenuItemActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

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

    private void fillColorMenuItemActionPerformed(ActionEvent actionEvent) {
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
