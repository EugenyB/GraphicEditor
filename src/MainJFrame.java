
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu.Separator;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        jMenuItem5 = new JMenuItem();
        drawPanel = new MyPanel();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenuItem2 = new JMenuItem();
        jSeparator1 = new Separator();
        jMenuItem3 = new JMenuItem();
        jMenu2 = new JMenu();
        circleRadioButtonMenuItem = new JRadioButtonMenuItem();
        rectangleRadioButtonMenuItem = new JRadioButtonMenuItem();
        triangleRadioButtonMenuItem = new JRadioButtonMenuItem();
        jSeparator2 = new Separator();
        moveResizeRadioButtonMenuItem = new JRadioButtonMenuItem();
        jMenu3 = new JMenu();
        jMenuItem4 = new JMenuItem();

        jMenuItem5.setText("Color");
        jMenuItem5.addActionListener(this::jMenuItem5ActionPerformed);
        jPopupMenu1.add(jMenuItem5);

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

        jMenuItem1.setText("Open...");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Save...");
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(this::jMenuItem3ActionPerformed);
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        buttonGroup1.add(circleRadioButtonMenuItem);
        circleRadioButtonMenuItem.setSelected(true);
        circleRadioButtonMenuItem.setText("Circle");
        jMenu2.add(circleRadioButtonMenuItem);

        buttonGroup1.add(rectangleRadioButtonMenuItem);
        rectangleRadioButtonMenuItem.setText("Rectangle");
        jMenu2.add(rectangleRadioButtonMenuItem);

        buttonGroup1.add(triangleRadioButtonMenuItem);
        triangleRadioButtonMenuItem.setText("Triangle");
        jMenu2.add(triangleRadioButtonMenuItem);
        jMenu2.add(jSeparator2);

        buttonGroup1.add(moveResizeRadioButtonMenuItem);
        moveResizeRadioButtonMenuItem.setText("Move / Resize");
        jMenu2.add(moveResizeRadioButtonMenuItem);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");

        jMenuItem4.setText("About");
        jMenuItem4.addActionListener(this::jMenuItem4ActionPerformed);
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(drawPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(drawPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveResizeRadioButtonMenuItem.isSelected()) {
                    int x = e.getX();
                    int y = e.getY();
                    System.out.println(">>>" + x + " " + y);
                }
            }
        });

        pack();
    }

    private void jMenuItem1ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jMenuItem4ActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(rootPane, "This is my program");
    }

    private void jMenuItem3ActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    private void jMenuItem5ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainJFrame().setVisible(true));
    }

    private ButtonGroup buttonGroup1;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
    private JMenuItem jMenuItem5;
    private MyPanel drawPanel;
    private JPopupMenu jPopupMenu1;
    private JRadioButtonMenuItem circleRadioButtonMenuItem;
    private JRadioButtonMenuItem rectangleRadioButtonMenuItem;
    private JRadioButtonMenuItem triangleRadioButtonMenuItem;
    private JRadioButtonMenuItem moveResizeRadioButtonMenuItem;
    private Separator jSeparator1;
    private Separator jSeparator2;

}
