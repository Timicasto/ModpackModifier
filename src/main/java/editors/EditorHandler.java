package editors;

import gui.PopMenu;
import io.ZSGenerator;
import tweak.ZSHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class EditorHandler {

    public static int operationCount = 0;
    private static JFrame main;

    public static void initCrT(JFrame frame) {
        main = frame;
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
        ZSEditorPopMenu menu = new ZSEditorPopMenu();
        JScrollPane pane = new JScrollPane();
        frame.add(pane);
        frame.setContentPane(menu);
        frame.setVisible(true);
        newFile.addActionListener(e -> {

        });

        save.addActionListener(e -> {
            for (int i = 0 ; i < ZSHelper.operations.size() ; i++) {
                ZSGenerator.commands.add(ZSGenerator.ZSLineCommand.generateCommandByItemIO(ZSHelper.operations.get(i + 1).type, ZSHelper.operations.get(i + 1).inputs, ZSHelper.operations.get(i + 1).output));
            }
            File fileSave = new File("./generatedRecipe.zs");
            if (!fileSave.isFile()) {
                try {
                    fileSave.createNewFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter("./generatedRecipe.zs"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            for (String s : ZSGenerator.commands) {
                try {
                    assert writer != null;
                    writer.write(s + "\r\n");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            try {
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    public static void initARXML(JFrame frame) {
        main = frame;
        frame.setLayout(null);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setSize(1024, 768);
        frame.getLayeredPane().add(new DrawLine());
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
        // ZSEditorPopMenu menu = new ZSEditorPopMenu();
        JScrollPane pane = new JScrollPane();
        frame.add(pane);
        frame.setContentPane(menu);
        frame.setVisible(true);
        newFile.addActionListener(e -> {

        });

        save.addActionListener(e -> {
            for (int i = 0 ; i < ZSHelper.operations.size() ; i++) {
                ZSGenerator.commands.add(ZSGenerator.ZSLineCommand.generateCommandByItemIO(ZSHelper.operations.get(i + 1).type, ZSHelper.operations.get(i + 1).inputs, ZSHelper.operations.get(i + 1).output));
            }
            File fileSave = new File("./generatedRecipe.zs");
            if (!fileSave.isFile()) {
                try {
                    fileSave.createNewFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter("./generatedRecipe.zs"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            for (String s : ZSGenerator.commands) {
                try {
                    assert writer != null;
                    writer.write(s + "\r\n");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            try {
                assert writer != null;
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    public static JFrame getMainFrame() {
        return main;
    }

    static class DrawLine extends JPanel {
        @Override
        public void paint(Graphics graphics) {
            graphics.drawLine(400, 0, 400, 900);
            graphics.drawLine(399, 0, 399, 900);
            graphics.drawLine(401, 0, 401, 900);
        }
    }

    static class ZSEditorPopMenu extends JPanel {
        JMenuItem ADD_VANILLA_SHAPELESS_CRAFTING, REMOVE_VANILLA_CRAFTING, MOD_AA_ATOMIC_RECONSTRUCTOR_ADD, MOD_AA_ATOMIC_RECONSTRUCTOR_REMOVE, MOD_AA_BALL_OF_FUR_ADD, MOD_AA_BALL_OF_FUR_REMOVE;

        public ZSEditorPopMenu() {
            JPopupMenu menu1 = new JPopupMenu();
            menu1.add(ADD_VANILLA_SHAPELESS_CRAFTING = new JMenuItem(ZSHelper.OperationType.ADD_VANILLA_SHAPELESS_CRAFTING.name));
            menu1.add(REMOVE_VANILLA_CRAFTING = new JMenuItem(ZSHelper.OperationType.REMOVE_VANILLA_CRAFTING.name));
            menu1.add(MOD_AA_ATOMIC_RECONSTRUCTOR_ADD = new JMenuItem(ZSHelper.OperationType.MOD_AA_ATOMIC_RECONSTRUCTOR_ADD.name));
            menu1.add(MOD_AA_ATOMIC_RECONSTRUCTOR_REMOVE = new JMenuItem(ZSHelper.OperationType.MOD_AA_ATOMIC_RECONSTRUCTOR_REMOVE.name));
            menu1.add(MOD_AA_BALL_OF_FUR_ADD = new JMenuItem(ZSHelper.OperationType.MOD_AA_BALL_OF_FUR_ADD.name));
            menu1.add(MOD_AA_BALL_OF_FUR_REMOVE = new JMenuItem(ZSHelper.OperationType.MOD_AA_BALL_OF_FUR_REMOVE.name));
            ADD_VANILLA_SHAPELESS_CRAFTING.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.ALT_MASK));
            REMOVE_VANILLA_CRAFTING.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.ALT_MASK));
            MOD_AA_ATOMIC_RECONSTRUCTOR_ADD.setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.ALT_MASK));
            MOD_AA_ATOMIC_RECONSTRUCTOR_REMOVE.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.ALT_MASK));
            MOD_AA_BALL_OF_FUR_ADD.setAccelerator(KeyStroke.getKeyStroke('F', InputEvent.ALT_MASK));
            MOD_AA_BALL_OF_FUR_REMOVE.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.ALT_MASK));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Pressed mouse button " + e.getButton());
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        menu1.show(ZSEditorPopMenu.this, e.getX(), e.getY());
                    }
                }
            });
            initEvent();
        }

        private void initEvent() {
            ADD_VANILLA_SHAPELESS_CRAFTING.addActionListener(e -> createNewShaplessCraftingFrame());
            REMOVE_VANILLA_CRAFTING.addActionListener(e -> createNewRemoveCraftingFrame());
            MOD_AA_ATOMIC_RECONSTRUCTOR_ADD.addActionListener(e -> createNewReconstructorAddFrame());
            MOD_AA_ATOMIC_RECONSTRUCTOR_REMOVE.addActionListener(e -> createNewReconstructorRemoveFrame());
            MOD_AA_BALL_OF_FUR_ADD.addActionListener(e -> createNewBallOfFurAddFrame());
            MOD_AA_BALL_OF_FUR_REMOVE.addActionListener(e -> createNewBallOfFurRemoveFrame());
        }

        public static void createNewShaplessCraftingFrame() {
            JFrame frame = createEmptyFrame(ZSHelper.OperationType.ADD_VANILLA_SHAPELESS_CRAFTING.name);
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Input types: ");
            panel.setBorder(new EmptyBorder(5, 5, 5, 5));
            panel.add(label);
            Integer[] choice = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
            JComboBox<? extends Integer> box = new JComboBox<>(choice);
            panel.add(box);
            JButton buttonCompleteInputType = new JButton("Decided on the number of types");
            panel.add(buttonCompleteInputType);
            buttonCompleteInputType.addActionListener(e -> {
                int typeCount = (int) box.getSelectedItem();
                JTextField[] variables = new JTextField[typeCount];
                for (int i = 0 ; i < typeCount ; i++) {
                    System.out.println("Running once");
                    JLabel label1 = new JLabel("input: ");
                    panel.add(label1);
                    label.setBounds(20, 80 + 30 * i, 80, 20);
                    variables[i] = new JTextField(i);
                    panel.add(variables[i]);
                    variables[i].setBounds(120, 80 + 30 * i, 140, 20);
                    System.out.println("label and textfield are " + label1 + "  " + variables[i]);
                }
                JLabel label2 = new JLabel("output: ");
                panel.add(label2);
                label2.setBounds(20, 80 + 30 * ((int)box.getSelectedItem() + 1), 80,20);
                System.out.println(label2);
                JTextField field1 = new JTextField();
                panel.add(field1);
                field1.setBounds(120, 80 + 30 * ((int)box.getSelectedItem() + 1), 140, 20);
                System.out.println(field1);
                JButton buttonFinish = new JButton("Create");
                buttonFinish.setBounds(80, 80 + 30 * ((int)box.getSelectedItem() + 2), 120, 40);
                panel.add(buttonFinish);
                buttonFinish.addActionListener(e1 -> {
                    operationCount++;
                    String[] inputs = new String[typeCount];
                    for (int i = 0 ; i < typeCount ; i++) {
                        inputs[i] = variables[i].getText();
                    }
                    System.out.println(Arrays.toString(inputs));
                    ZSHelper.operations.put(operationCount, new ZSHelper.ZSOperation(ZSHelper.OperationType.ADD_VANILLA_SHAPELESS_CRAFTING, inputs, field1.getText(), 0, 0));
                    try {
                        ZSHelper.refresh(getMainFrame());
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                });
            });
            frame.add(panel);
        }



        public static void createNewRemoveCraftingFrame() {
            JFrame frame = createEmptyFrame(ZSHelper.OperationType.REMOVE_VANILLA_CRAFTING.name);
            JPanel panel = new JPanel();
            panel.setLayout(null);
            JLabel label = new JLabel("Target Item: ");
            label.setBounds(20, 40, 120, 20);
            panel.add(label);
            JTextField field = new JTextField();
            field.setBounds(100, 40, 100, 20);
            panel.add(field);
            JButton button = new JButton("Create");
            button.setBounds(40, 80, 120, 40);
            panel.add(button);
            button.addActionListener(e -> {
                operationCount++;
                String target = field.getText();
                ZSHelper.operations.put(operationCount, new ZSHelper.ZSOperation(ZSHelper.OperationType.REMOVE_VANILLA_CRAFTING, null, target, 0, 0));
                try {
                    ZSHelper.refresh(getMainFrame());
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
            frame.add(panel);
        }

        public static void createNewReconstructorAddFrame() {
            JFrame frame = createEmptyFrame(ZSHelper.OperationType.MOD_AA_ATOMIC_RECONSTRUCTOR_ADD.name);
            JPanel panel = new JPanel();
            frame.add(panel);
        }

        public static void createNewReconstructorRemoveFrame() {
            JFrame frame = createEmptyFrame(ZSHelper.OperationType.MOD_AA_ATOMIC_RECONSTRUCTOR_REMOVE.name);
            JPanel panel = new JPanel();
            frame.add(panel);
        }

        public static void createNewBallOfFurAddFrame() {
            JFrame frame = createEmptyFrame(ZSHelper.OperationType.MOD_AA_BALL_OF_FUR_ADD.name);
            JPanel panel = new JPanel();
            frame.add(panel);
        }

        public static void createNewBallOfFurRemoveFrame() {
            JFrame frame = createEmptyFrame(ZSHelper.OperationType.MOD_AA_BALL_OF_FUR_REMOVE.name);
            JPanel panel = new JPanel();
            frame.add(panel);
        }

        public static JFrame createEmptyFrame(String title) {
            JFrame frame = new JFrame(title);
            frame.setSize(400, 400);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setIconImage(PopMenu.icon);
            return frame;
        }
    }

    public static void refreshDisplay(Map<Integer, ZSHelper.ZSOperation> map, JFrame panel) {
        for (int i = 0 ; i < map.size() ; i++) {
            if (map.get(i + 1).type.equals(ZSHelper.OperationType.ADD_VANILLA_SHAPELESS_CRAFTING)) {
                panel.setLayout(null);
                String[] inputs = map.get(i + 1).inputs;
                JLabel label = new JLabel("AddShaplessRecipe");
                panel.add(label);
                label.setBounds(25, 20 + 120 * i, 160, 20);
                JLabel label1 = new JLabel("Index: " + i);
                panel.add(label1);
                label1.setBounds(25, 40 + 120 * i, 100, 20);
                JLabel label3 = new JLabel("Inputs: ");
                panel.add(label3);
                label3.setBounds(25, 60 + 120 * i, 100, 20);
                for (int j = 0; j < inputs.length; j++) {
                    JLabel label2 = new JLabel(inputs[j]);
                    panel.add(label2);
                    label2.setBounds(80 + (120 * j), 60 + i * 100, 120, 20);
                }
                JLabel label4 = new JLabel("Output: ");
                panel.add(label4);
                label4.setBounds(25, 80 + 120 * i, 100, 20);
                JLabel label5 = new JLabel(map.get(i + 1).output);
                panel.add(label5);
                label5.setBounds(75, 80 + 120 * i, 100, 20);
            }
            if (map.get(i + 1).type.equals(ZSHelper.OperationType.REMOVE_VANILLA_CRAFTING)) {
                panel.setLayout(null);
                String[] inputs = map.get(i + 1).inputs;
                JLabel label = new JLabel("RemoveEveryRecipe");
                panel.add(label);
                label.setBounds(25, 20 + 120 * i, 160, 20);
                JLabel label1 = new JLabel("Index: " + i);
                panel.add(label1);
                label1.setBounds(25, 40 + 120 * i, 100, 20);
                JLabel label3 = new JLabel("Target: ");
                panel.add(label3);
                label3.setBounds(25, 60 + 120 * i, 100, 20);
                JLabel label5 = new JLabel(map.get(i + 1).output);
                panel.add(label5);
                label5.setBounds(75, 60 + 120 * i, 100, 20);
            }
        }
        System.out.println(panel);
    }
}
