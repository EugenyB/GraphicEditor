
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu.Separator;
import java.awt.event.ActionEvent;

/**
 *
 * @author
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
    }

    private void initComponents() {

        buttonGroup1 = new ButtonGroup();
        jPopupMenu1 = new JPopupMenu();
        jMenuItem5 = new JMenuItem();
        jPanel1 = new JPanel();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenuItem2 = new JMenuItem();
        jSeparator1 = new Separator();
        jMenuItem3 = new JMenuItem();
        jMenu2 = new JMenu();
        jRadioButtonMenuItem1 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new JRadioButtonMenuItem();
        jSeparator2 = new Separator();
        jRadioButtonMenuItem4 = new JRadioButtonMenuItem();
        jMenu3 = new JMenu();
        jMenuItem4 = new JMenuItem();

        jMenuItem5.setText("Color");
        jMenuItem5.addActionListener(this::jMenuItem5ActionPerformed);
        jPopupMenu1.add(jMenuItem5);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setComponentPopupMenu(jPopupMenu1);

        GroupLayout jPanel1Layout;
        jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
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

        buttonGroup1.add(jRadioButtonMenuItem1);
        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Circle");
        jMenu2.add(jRadioButtonMenuItem1);

        buttonGroup1.add(jRadioButtonMenuItem2);
        jRadioButtonMenuItem2.setText("Rectangle");
        jMenu2.add(jRadioButtonMenuItem2);

        buttonGroup1.add(jRadioButtonMenuItem3);
        jRadioButtonMenuItem3.setText("Triangle");
        jMenu2.add(jRadioButtonMenuItem3);
        jMenu2.add(jSeparator2);

        buttonGroup1.add(jRadioButtonMenuItem4);
        jRadioButtonMenuItem4.setText("Move / Resize");
        jMenu2.add(jRadioButtonMenuItem4);

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
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

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

    /**
     * @param args the command line arguments
     */
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
    private JPanel jPanel1;
    private JPopupMenu jPopupMenu1;
    private JRadioButtonMenuItem jRadioButtonMenuItem1;
    private JRadioButtonMenuItem jRadioButtonMenuItem2;
    private JRadioButtonMenuItem jRadioButtonMenuItem3;
    private JRadioButtonMenuItem jRadioButtonMenuItem4;
    private Separator jSeparator1;
    private Separator jSeparator2;

}
