package editors;

import javax.swing.*;
import java.awt.*;

public class EditorHandler {
    public static void initCrT(JFrame frame) {
        frame.setLayout(null);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setSize(1600, 900);
        frame.getLayeredPane().add(new DrawLine());
        JOptionPane.showMessageDialog(null, "Please note: ZenScript is a scripting language, so the order of its execution will depend on the order in which you create objects. \nSince this tool is only an early version, and there is no high-quality performance optimization solution for moving objects in the short term,\n the order of objects cannot be moved.");
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu about = new JMenu("About");
        bar.add(file);
        bar.add(edit);
        bar.add(about);
        JMenuItem newFile = new JMenuItem("New File");
        JMenuItem save = new JMenuItem("Save / Save As");
        JMenuItem exit = new JMenuItem("Exit");
        file.add(newFile);
        file.add(save);
        file.addSeparator();
        file.add(exit);
        frame.setJMenuBar(bar);
        JLabel label = new JLabel("New object");
        label.setBounds(40, 200, 120, 20);
        panel.add(label);
        frame.getContentPane().add(panel);
        frame.setVisible(true);

        newFile.addActionListener(e -> {

        });

    }

    static class DrawLine extends JPanel {
        @Override
        public void paint(Graphics graphics) {
            graphics.drawLine(400, 0, 400, 900);
            graphics.drawLine(399, 0, 399, 900);
            graphics.drawLine(401, 0, 401, 900);
        }
    }
}
