package timicasto.modpackmodifier.editors;

import timicasto.modpackmodifier.gui.PopMenu;
import timicasto.modpackmodifier.io.XMLGenerator;
import timicasto.modpackmodifier.io.ZSGenerator;
import timicasto.modpackmodifier.tweak.ARHelper;
import timicasto.modpackmodifier.tweak.ZSHelper;
import timicasto.modpackmodifier.tweak.arobj.Astronomical;

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
import java.util.*;

public class EditorHandler {

    public static int operationCount = 0;
    public static int arObjs = 0;
    private static JFrame mainCrT, mainXML;

    public static void initCrT(JFrame frame) {
        mainCrT = frame;
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
        mainXML = frame;
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
        ARXMLEditorPopMenu menu = new ARXMLEditorPopMenu();
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

    public static JFrame getCrTMainFrame() {
        return mainCrT;
    }

    public static JFrame getXMLMainFrame() {
        return mainXML;
    }

    static class DrawLine extends JPanel {
        @Override
        public void paint(Graphics graphics) {
            graphics.drawLine(400, 0, 400, 900);
            graphics.drawLine(399, 0, 399, 900);
            graphics.drawLine(401, 0, 401, 900);
        }
    }

    static class ARXMLEditorPopMenu extends JPanel{
        JMenuItem ADD_STAR, ADD_PLANET;

        public ARXMLEditorPopMenu() {
            JPopupMenu menu = new JPopupMenu();
            menu.add(ADD_STAR = new JMenuItem("Create a Star"));
            menu.add(ADD_PLANET = new JMenuItem("Create a Planet"));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Pressed mouse button " + e.getButton());
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        menu.show(EditorHandler.ARXMLEditorPopMenu.this, e.getX(), e.getY());
                    }
                }
            });
            initARXMLEvent();
        }

        private void initARXMLEvent() {
            ADD_STAR.addActionListener(e -> createNewStarFrame());
            ADD_PLANET.addActionListener(e -> createNewPlanetFrame());
        }

        public static void createNewStarFrame() {
            JFrame frame = createNormalFrame("Create a new Star");
            JPanel panel = new JPanel();
            panel.setLayout(null);
            JLabel name = new JLabel("ID: ");
            name.setBounds(20, 20, 80, 20);
            panel.add(name);
            JTextField nameField = new JTextField();
            nameField.setBounds(240, 20, 250, 20);
            panel.add(nameField);

            JLabel name1 = new JLabel("Name: ");
            name1.setBounds(20, 60, 80, 20);
            panel.add(name1);
            JTextField nameField1 = new JTextField();
            nameField1.setBounds(240, 60, 250, 20);
            panel.add(nameField1);
            JLabel name2 = new JLabel("Temp: ");
            name2.setBounds(20, 100, 80, 20);
            panel.add(name2);
            JTextField nameField2 = new JTextField();
            nameField2.setBounds(240, 100, 250, 20);
            panel.add(nameField2);
            JLabel name3 = new JLabel("X Pos: ");
            name3.setBounds(20, 140, 120, 20);
            panel.add(name3);
            JTextField nameField3 = new JTextField();
            nameField3.setBounds(240, 140, 250, 20);
            panel.add(nameField3);
            JLabel name4 = new JLabel("Y Pos: ");
            name4.setBounds(20, 180, 80, 20);
            panel.add(name4);
            JTextField nameField4 = new JTextField();
            nameField4.setBounds(240, 180, 250, 20);
            panel.add(nameField4);
            JLabel name5 = new JLabel("Random solid planet count: ");
            name5.setBounds(20, 220, 200, 20);
            panel.add(name5);
            JTextField nameField5 = new JTextField();
            nameField5.setBounds(240, 220, 250, 20);
            panel.add(nameField5);
            JLabel name6 = new JLabel("Name: ");
            name6.setBounds(20, 260, 80, 20);
            panel.add(name6);
            JTextField nameField6 = new JTextField();
            nameField6.setBounds(240, 260, 250, 20);
            panel.add(nameField6);
            JButton button = new JButton("Finish");
            button.setBounds(160, 300, 160, 60);
            panel.add(button);
            frame.add(panel);
            button.addActionListener(e -> {
                arObjs++;
                ARHelper.stars.put(arObjs, new Astronomical.Star(nameField1.getText(), Integer.parseInt(nameField2.getText()), Integer.parseInt(nameField3.getText()), Integer.parseInt(nameField4.getText()), Integer.parseInt(nameField5.getText()), Integer.parseInt(nameField.getText())));
                try {
                    ARHelper.refresh(getXMLMainFrame());
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
            frame.repaint();
            System.out.println(frame);
        }

        public static void createNewPlanetFrame() {
            JFrame frame = createNormalFrame("Create a new Planet");
            JPanel panel = new JPanel();
            panel.setLayout(null);
            JLabel name = new JLabel("Name");
            name.setBounds(20, 20, 200, 20);
            panel.add(name);
            JTextField nameField = new JTextField();
            nameField.setBounds(240, 20, 250, 20);
            panel.add(nameField);

            JLabel gas = new JLabel("Gas(Nullable)");
            gas.setBounds(20, 40, 200, 20);
            panel.add(gas);
            JTextField gasField = new JTextField();
            gasField.setBounds(240, 40, 250, 20);
            panel.add(gasField);

            JLabel filter = new JLabel("Filter Block");
            filter.setBounds(20, 60, 200, 20);
            panel.add(filter);
            JTextField filterField = new JTextField();
            filterField.setBounds(240, 60, 250, 20);
            panel.add(filterField);

            JLabel atmosphere = new JLabel("Atmosphere Density");
            atmosphere.setBounds(20, 80, 200, 20);
            panel.add(atmosphere);
            JTextField atmosphereField = new JTextField();
            atmosphereField.setBounds(240, 80, 250, 20);
            panel.add(atmosphereField);

            JLabel dimID = new JLabel("Dimension ID");
            dimID.setBounds(20, 100, 200, 20);
            panel.add(dimID);
            JTextField idField = new JTextField();
            idField.setBounds(240, 100, 250, 20);
            panel.add(idField);

            JLabel oceanBlock = new JLabel("Ocean Block");
            oceanBlock.setBounds(20, 120, 200, 20);
            panel.add(oceanBlock);
            JTextField oceanBlockField = new JTextField();
            oceanBlockField.setBounds(240, 120, 250, 20);
            panel.add(oceanBlockField);

            JLabel hasRing = new JLabel("Whether there is a ring");
            hasRing.setBounds(20, 140, 200, 20);
            panel.add(hasRing);
            JCheckBox hasRingField = new JCheckBox();
            hasRingField.setBounds(240, 140, 20, 20);
            panel.add(hasRingField);

            JLabel gasGiant = new JLabel("Is it a gas giant");
            gasGiant.setBounds(20, 160, 200, 20);
            panel.add(gasGiant);
            JCheckBox gasGiantField = new JCheckBox();
            gasGiantField.setBounds(240, 160, 20, 20);
            panel.add(gasGiantField);

            JLabel gravitationalMultiplier = new JLabel("Gravity magnification");
            gravitationalMultiplier.setBounds(20, 180, 200, 20);
            panel.add(gravitationalMultiplier);
            JTextField gravitationalMultiplierField = new JTextField();
            gravitationalMultiplierField.setBounds(240, 180, 250, 20);
            panel.add(gravitationalMultiplierField);

            JLabel orbitalDistance = new JLabel("Orbital Distance");
            orbitalDistance.setBounds(20, 200, 200, 20);
            panel.add(orbitalDistance);
            JTextField orbitalDistanceField = new JTextField();
            orbitalDistanceField.setBounds(240, 200, 250, 20);
            panel.add(orbitalDistanceField);

            JLabel orbitalPhi = new JLabel("Orbital φ");
            orbitalPhi.setBounds(20, 220, 200, 20);
            panel.add(orbitalPhi);
            JTextField orbitalPhiField = new JTextField();
            orbitalPhiField.setBounds(240, 220, 250, 20);
            panel.add(orbitalPhiField);

            JLabel orbitalTheta = new JLabel("Orbital θ");
            orbitalTheta.setBounds(20, 240, 200, 20);
            panel.add(orbitalTheta);
            JTextField orbitalThetaField = new JTextField();
            orbitalThetaField.setBounds(240, 240, 250, 20);
            panel.add(orbitalThetaField);

            JLabel fogColor = new JLabel("Fog Color(HEX)");
            fogColor.setBounds(20, 260, 200, 20);
            panel.add(fogColor);
            JTextField fogColorField = new JTextField();
            fogColorField.setBounds(240, 260, 250, 20);
            panel.add(fogColorField);

            JLabel skyColor = new JLabel("Sky Color(HEX)");
            skyColor.setBounds(20, 280, 200, 20);
            panel.add(skyColor);
            JTextField skyColorField = new JTextField();
            skyColorField.setBounds(240, 280, 250, 20);
            panel.add(skyColorField);

            JLabel rotationalPeriod = new JLabel("Ticks per day");
            rotationalPeriod.setBounds(20, 300, 200, 20);
            panel.add(rotationalPeriod);
            JTextField rotationalPeriodField = new JTextField();
            rotationalPeriodField.setBounds(240, 300, 250, 20);
            panel.add(rotationalPeriodField);

            JLabel seaLevel = new JLabel("Sea Level");
            seaLevel.setBounds(20, 320, 200, 20);
            panel.add(seaLevel);
            JTextField seaLevelField = new JTextField();
            seaLevelField.setBounds(240, 320, 250, 20);
            panel.add(seaLevelField);

            JLabel parentStar = new JLabel("Parent star");
            parentStar.setBounds(20, 340, 340, 20);
            panel.add(parentStar);
            Astronomical.Star[] stars = new Astronomical.Star[ARHelper.stars.size()];
            String[] names = new String[ARHelper.stars.size()];
            Map<String, Astronomical.Star> transformation = new HashMap<>();
            for (int key : ARHelper.stars.keySet()) {
                stars[key] = ARHelper.stars.get(key);
            }
            for (int i = 0; i < stars.length; i++) {
                transformation.put(stars[i].name, stars[i]);
                names[i] = stars[i].name;
            }

            JComboBox<? extends String> parentStarInput = new JComboBox<>(names);
            parentStarInput.setBounds(240, 340, 250, 20);
            panel.add(parentStarInput);

            JButton button = new JButton("Finish");
            button.setBounds(200, 400, 160, 60);
            panel.add(button);
            frame.add(panel);

            button.addActionListener(e -> {
                transformation.get(Objects.requireNonNull(parentStarInput.getSelectedItem()).toString()).planets.put(Integer.parseInt(idField.getText()), new Astronomical.Planet(nameField.getText(), hasRingField.isSelected(), gasGiantField.isSelected(), gasField.getText(), filterField.getText(), oceanBlockField.getText(), Integer.parseInt(atmosphereField.getText()), Integer.parseInt(gravitationalMultiplierField.getText()), Integer.parseInt(orbitalDistanceField.getText()), Integer.parseInt(orbitalThetaField.getText()), Integer.parseInt(orbitalPhiField.getText()), Integer.parseInt(rotationalPeriodField.getText()), Integer.parseInt(seaLevelField.getText()), Integer.parseInt(idField.getText()), new Color(Integer.parseInt(skyColorField.getText())), new Color(Integer.parseInt(fogColorField.getText())), new String[]{}));
            });
        }


    }

    public static JFrame createNormalFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setIconImage(PopMenu.icon);
        return frame;
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
            initCrTEvent();
        }

        private void initCrTEvent() {
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
                        ZSHelper.refresh(getCrTMainFrame());
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
                    ZSHelper.refresh(getCrTMainFrame());
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
            frame.setSize(400, 800);
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

    public static void refreshARDisplay(Map<Integer, ?> map, JFrame frame) {
        int objects = 0;
        if (map.get(0).getClass().equals(Astronomical.Star.class)) {
            for (int i = 0 ; i < map.size() -1  ; i++) {
                objects++;
                Astronomical.Star star = (Astronomical.Star) map.get(i + 1);
                JLabel label = new JLabel("Star:   " + star.name);
                frame.add(label);
                label.setBounds(20, 20 + 160 * (objects - 1), 120, 20);
                JLabel label1 = new JLabel("Temp:   " + star.temp);
                frame.add(label1);
                label1.setBounds(20, 40 + 160 * (objects - 1), 120, 20);
                JLabel label2 = new JLabel("X Position: " + star.x);
                frame.add(label2);
                label2.setBounds(20, 60 + 160 * (objects - 1), 120, 20);
                JLabel label3 = new JLabel("Y Position: " + star.y);
                frame.add(label3);
                label3.setBounds(20, 80 + 160 * (objects - 1), 120, 20);
                JLabel label4 = new JLabel("ID: " + star.id);
                frame.add(label4);
                label4.setBounds(20, 100 + 160 * (objects - 1), 120, 20);
                JLabel label5 = new JLabel("Default number of planets: " + star.numPlanets);
                frame.add(label5);
                label5.setBounds(20, 120 + 160 * (objects - 1), 400, 20);
                JLabel label6 = new JLabel("// The number of random solid planets generated under this star");
                frame.add(label6);
                label6.setBounds(20, 140 + 160 * (objects - 1), 800, 20);
                System.out.println(frame);
            }
        }
        if (map.get(0).getClass().equals(Astronomical.Planet.class)) {
            for (int i = 0 ; i < map.size() - 1 ; i++) {
                objects++;
                Astronomical.Planet planet = (Astronomical.Planet) map.get(i + 1);
                JLabel label = new JLabel("Planet: " + planet.name);
                frame.add(label);
                label.setBounds(20, 160 * objects, 120, 20);
            }
        }
    }
}
