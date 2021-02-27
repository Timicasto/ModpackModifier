import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                createWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void createWindow() throws IOException {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("ModpackModifier");
        Image icon = ImageIO.read(new URL("http://timicasto.sukazyo.cc:12000/logo.png"));
        frame.setIconImage(icon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 400);
    }

    public static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        JLabel label = new JLabel("Select A Property");
        label.setBounds(10, 20, 120, 25);
        panel.add(label);
        JLabel bg = new JLabel(new Icon() {
        });
        JButton propertyCrT = new JButton("ZenScript(Craft Tweaker)");
        propertyCrT.setBounds(10, 80, 200, 25);
        panel.add(propertyCrT);
        JButton propertyModularMachinery = new JButton("Json(Modular Machinery)");
        propertyModularMachinery.setBounds(10, 120, 200, 25);
        panel.add(propertyModularMachinery);
        JButton propertyARXML = new JButton("XML(Advanced Rocketry)");
        propertyARXML.setBounds(10, 160, 200, 25);
        panel.add(propertyARXML);
        JButton propertyCM3 = new JButton("JSON(Compact Machines 3)");
        propertyCM3.setBounds(10, 200, 200, 25);
        panel.add(propertyCM3);
    }
}
