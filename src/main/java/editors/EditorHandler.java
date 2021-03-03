package editors;

import gui.PopMenu;
import tweak.ZSHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Map;

public class EditorHandler {

    public static int operationCount = 0;

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
        ZSEditorPopMenu menu = new ZSEditorPopMenu();
        frame.setContentPane(menu);
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
                    ZSHelper.refresh(panel);
                });
            });
            frame.add(panel);
        }



        public static void createNewRemoveCraftingFrame() {
            JFrame frame = createEmptyFrame(ZSHelper.OperationType.REMOVE_VANILLA_CRAFTING.name);
            JPanel panel = new JPanel();
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

    public static void refreshDisplay(Map<Integer, ZSHelper.ZSOperation> map, JPanel panel) {
        for (int i = 0 ; i < map.size() ; i++) {
            String[] inputs = map.get(i + 1).inputs;
            JLabel label = new JLabel("AddShaplessRecipe");
            label.setBounds(25, 20 + (20 + 20 * inputs.length), 100, 20);
            JLabel label1 = new JLabel("Index: " + i);
            panel.add(label);
            panel.add(label1);
            for (int j = 0 ; j < inputs.length ; j++) {
                JLabel label2 = new JLabel(inputs[j]);
                label2.setBounds(40, 20 + (20 * j), 120, 20);
                panel.add(label2);
            }
        }
    }
}
