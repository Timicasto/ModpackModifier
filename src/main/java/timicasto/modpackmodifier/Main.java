package timicasto.modpackmodifier;

import timicasto.modpackmodifier.editors.EditorHandler;
import timicasto.modpackmodifier.gui.BorderRadius;
import timicasto.modpackmodifier.gui.PopMenu;
import timicasto.modpackmodifier.tweak.ARHelper;
import timicasto.modpackmodifier.tweak.arobj.Astronomical;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Main {

    /**
     * This project meets the following laws:
     *   - Comments that belongs to the line below it
     */

    public static int instanceCount;
    public static boolean showWarningMessage = true;
    public static boolean terminateWhenWindowClose = false;

    public static void main(String[] args) {
        // When the available memory in Java Virtual Machine is less than 512MB, let the user stop running the entire
        // program as long as any one of the windows is closed.
        if (getFreeMemory() / 1024 / 1024 < 512) {
            JOptionPane.showMessageDialog(null, "The current JVM memory margin is too low, no matter you close any window, the entire program will stop running, please pay attention to save your work");
            terminateWhenWindowClose = true;
        }
        SwingUtilities.invokeLater(() -> {
            try {
                createWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ARHelper.stars.put(0, new Astronomical.Star("", 0, 0, 0, 0, 0));
    }

    /**
     * Used to get available memory in Java Virtual Machine
     *
     * @return Available memory in Java Virtual Machine
     */
    public static double getFreeMemory() {
        Runtime time = Runtime.getRuntime();
        System.out.println("Free Memory: " + time.freeMemory() + " bytes");
        return time.freeMemory();
    }

    /**
     * Program main window initialization method
     *
     */
    public static void createWindow() throws IOException {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setTitle("Modpack Modifier SNAPSHOT 1.0 -by QuantumHardwareStudio");
        // TODO: Change the icon from the network to load from local loading
        frame.setIconImage(PopMenu.icon);
        // If the available memory is less than 512M, it will output "Set to true" on the console, and execute the
        // setting logic to stop running the program when any window is closed.
        if (terminateWhenWindowClose) {
            System.out.println("Set to true");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        frame.pack();
        panel.setOpaque(false);
        JPanel panel2 = (JPanel)frame.getContentPane();
        panel2.setOpaque(false);
        /*Image bg = Utils.blur(ImageIO.read(new URL("http://timicasto.sukazyo.cc:12000/randompicture.php")), 86);
        JPanel panel1 = new CustomJFrame.BackgroundPanel(bg);
        panel1.setSize(1280, 720);
        frame.add(panel1);*/
        //System.out.println("panel bg is " + panel1);
        panel.setOpaque(false);
        frame.getContentPane().add(panel);
        frame.setSize(1280, 720);
        placeComponents(panel);
        frame.setBackground(new Color(16777215));
        frame.setResizable(false);
        frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        frame.setVisible(true);
    }

    public static void placeComponents(JPanel panel) throws IOException {
        panel.setLayout(null);
        panel.setBackground(new Color(16777215));
        JLabel title = new JLabel("Modpack Modifier");
        title.setFont(new Font("微软雅黑", Font.BOLD, 38));
        title.setForeground(new Color(0));
        panel.add(title);
        title.setBounds(93, 47, 506, 52);
        JLabel welcome = new JLabel("欢迎");
        welcome.setFont(new Font("微软雅黑", Font.BOLD, 21));
        welcome.setForeground(new Color(0));
        panel.add(welcome);
        welcome.setBounds(93, 102, 43, 29);
        JButton propertyCrT = new JButton("    Craft Tweaker     ", new ImageIcon(new URL("http://timicasto.sukazyo.cc:12000/zs.png"))) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(0xEEEEEE));
                    this.setBorder(new BorderRadius());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,10,10);
                super.paintComponent(g);
            }
        };
        propertyCrT.setFont(new Font("微软雅黑", Font.BOLD, 16));
        propertyCrT.setForeground(new Color(7368816));
        propertyCrT.setBackground(new Color(0xEEEEEE));
        propertyCrT.setBorder(new BorderRadius());
        propertyCrT.setBorderPainted(false);
        propertyCrT.setOpaque(false);
        //propertyCrT.setContentAreaFilled(false);
        propertyCrT.setFocusPainted(false);
        propertyCrT.setBounds(93, 318, 265, 53);
        panel.add(propertyCrT);
        JButton propertyModularMachinery = new JButton("Modular Machinery", new ImageIcon(new URL("http://timicasto.sukazyo.cc:12000/json.png"))) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(0xEEEEEE));
                    this.setBorder(new BorderRadius());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,10,10);
                super.paintComponent(g);
            }
        };
        propertyModularMachinery.setFont(new Font("微软雅黑", Font.BOLD, 16));
        propertyModularMachinery.setForeground(new Color(7368816));
        propertyModularMachinery.setBackground(new Color(0xEEEEEE));
        propertyModularMachinery.setBorder(new BorderRadius());
        propertyModularMachinery.setBorderPainted(false);
        propertyModularMachinery.setOpaque(false);
        //propertyModularMachinery.setContentAreaFilled(false);
        propertyModularMachinery.setFocusPainted(false);
        propertyModularMachinery.setBounds(93, 398, 265, 53);
        panel.add(propertyModularMachinery);
        propertyModularMachinery.addActionListener(e -> createNewWindowByButton("Json(Modular Machinery) Editor", 800, 600));

        panel.add(propertyModularMachinery);
        JButton propertyARXML = new JButton("Advanced Rocketry", new ImageIcon(new URL("http://timicasto.sukazyo.cc:12000/xml.png"))) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(0xEEEEEE));
                    this.setBorder(new BorderRadius());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,10,10);
                super.paintComponent(g);
            }
        };
        propertyARXML.setFont(new Font("微软雅黑", Font.BOLD, 16));
        propertyARXML.setForeground(new Color(7368816));
        propertyARXML.setBackground(new Color(0xEEEEEE));
        propertyARXML.setBorder(new BorderRadius());
        propertyARXML.setBorderPainted(false);
        propertyARXML.setOpaque(false);
        //propertyARXML.setContentAreaFilled(false);
        propertyARXML.setFocusPainted(false);
        propertyARXML.setBounds(93, 473, 265, 53);
        propertyARXML.addActionListener(e -> EditorHandler.initARXML(Objects.requireNonNull(createNewWindowByButton("XML(Advanced Rocketry) Editor", 1024, 768))));
        panel.add(propertyARXML);
        JButton propertyCM3 = new JButton("Compact Machines", new ImageIcon(new URL("http://timicasto.sukazyo.cc:12000/json.png"))) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(0xEEEEEE));
                    this.setBorder(new BorderRadius());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,10,10);
                super.paintComponent(g);
            }
        };
        BufferedImage image = ImageIO.read(new URL("http://timicasto.sukazyo.cc:12000/randompicture.php"));
        image.getScaledInstance(492, 211, Image.SCALE_DEFAULT);
        JLabel bg = new JLabel(new ImageIcon(image));
        bg.setBounds(492, 211, 695, 391);
        bg.setBorder(new BorderRadius(new Color(0xFFFFFF)));
        panel.add(bg);
        propertyCM3.setBounds(93, 549, 265, 53);
        propertyCM3.setFont(new Font("微软雅黑", Font.BOLD, 16));
        propertyCM3.setForeground(new Color(7368816));
        propertyCM3.setBackground(new Color(0xEEEEEE));
        propertyCM3.setBorder(new BorderRadius());
        propertyCM3.setBorderPainted(false);
        propertyCM3.setOpaque(false);
        //propertyCM3.setContentAreaFilled(false);
        propertyCM3.setFocusPainted(false);
        propertyCM3.addActionListener(e -> createNewWindowByButton("Json(Compact Machines 3) Editor", 800, 600));
        panel.add(propertyCM3);

        URL url = new URL("http://timicasto.sukazyo.cc:12000/CreateInstance.png");
        BufferedImage instanceImage = ImageIO.read(url);
        BufferedImage scaled1 = new BufferedImage((int) (instanceImage.getWidth() * 0.6), (int) (instanceImage.getHeight() * 0.6), BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = scaled1.getGraphics();
        graphics.drawImage(instanceImage, 0, 0, scaled1.getWidth(), scaled1.getHeight(), null);
        Icon instance = new ImageIcon(scaled1);

        JButton newInstance = new JButton("    新建实例", instance) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(1622491));
                    this.setBorder(new BorderRadius());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,10,10);
                super.paintComponent(g);
            }
        };
        newInstance.setFont(new Font("微软雅黑", Font.BOLD, 21));
        newInstance.setForeground(new Color(16777215));
        newInstance.setBackground(new Color(56575));
        newInstance.setBounds(93, 211, 265, 53);
        newInstance.setBorder(new BorderRadius());
        newInstance.setBorderPainted(false);
        newInstance.setOpaque(false);
        //newInstance.setContentAreaFilled(false);
        newInstance.setFocusPainted(false);
        newInstance.addActionListener(e -> {
            try {
                if (instanceCount < 8) {
                    if (instanceCount == 0 && showWarningMessage && terminateWhenWindowClose) {
                        JOptionPane.showConfirmDialog(null, "When you close any window, the entire program will be closed accordingly, please pay attention to save your work, \n Do you want to continue to create a new window or instance? \n If you don't want to see this message again, please click Cancel");
                    }
                    createWindow();
                    instanceCount++;
                    System.out.println("Current instances : " + instanceCount);
                } else {
                    System.out.println("Too many instances!");
                    JOptionPane.showMessageDialog(null, "Too many instances, you can't create another one");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        panel.add(newInstance);
        /*
        JLabel label = new JLabel("Select A Property");
        label.setOpaque(true);
        label.setBounds(10, 20, 120, 25);
        panel.add(label);*/
        propertyCrT.addActionListener(e -> EditorHandler.initCrT(Objects.requireNonNull(createNewWindowByButton("ZenScript(Craft Tweaker) editor", 1600, 900))));
    }

    @Deprecated
    public static void setBak(JPanel panel) {
        panel.setLayout(null);
        BufferedImage bgSrc = null;
        try {
            bgSrc = ImageIO.read(new URL("http://timicasto.sukazyo.cc:12000/randompicture.php"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage scaled = new BufferedImage(960, 540, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = scaled.getGraphics();
        graphics.drawImage(bgSrc, 0, 0, 960, 540, null);
        JLabel background = new JLabel(new ImageIcon(scaled));
        background.setBounds(0, 0, 960, 540);
        panel.add(background);
    }

    public static JFrame createInstance(String title, int width, int height) throws IOException {
        JFrame crt = new JFrame(title);
        Image icon = ImageIO.read(new URL("http://timicasto.sukazyo.cc:12000/logo.png"));
        crt.setIconImage(icon);
        crt.setSize(width, height);
        crt.setResizable(false);
        crt.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        crt.setVisible(true);
        return crt;
    }


    public static JFrame createNewWindowByButton(String title, int width, int height) {
        if (instanceCount < 8) {
            if (showWarningMessage) {
                int response = JOptionPane.showConfirmDialog(null, "When you close any window, the entire program will be closed accordingly, please pay attention to save your work, \n Do you want to continue to create a new window or instance? \n If you don't want to see this message again, please click Cancel");
                switch (response) {
                    case 0:
                        try {
                            JFrame frame = createInstance(title, width, height);
                            JOptionPane.showMessageDialog(null, "Press Ctrl-Q in editor window to view hot keys");
                            instanceCount++;
                            return frame;
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        break;
                    case 1:
                        return null;
                    case 2:
                        showWarningMessage = false;
                        try {
                            JFrame frame = createInstance(title, width, height);
                            instanceCount++;
                            return frame;
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    default:
                        break;
                }
            } else {
                try {
                    JFrame frame = createInstance(title, width, height);
                    instanceCount++;
                    return frame;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,"Too many instances, you can't create a new one!");
        }
        return null;
    }
}
