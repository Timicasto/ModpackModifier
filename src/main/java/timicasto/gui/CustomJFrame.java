package timicasto.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CustomJFrame extends JFrame {
    Container container;
    BackgroundPanel bgp;

    public CustomJFrame() throws IOException {
        container = this.getContentPane();
        this.setLayout(null);
        bgp = new BackgroundPanel(ImageIO.read(new URL("http://timicasto.sukazyo.cc:12000/randompicture.php")));
        System.out.println("bgp is " + ImageIO.read(new URL("http://timicasto.sukazyo.cc:12000/randompicture.php")));
        bgp.setBounds(0, 0, this.getWidth(), this.getHeight());
        container.add(bgp);
        JButton jb;
        jb=new JButton("测试按钮");
        jb.setBounds(60,30,160,30);
        container.add(jb);
    }

    public static class BackgroundPanel extends JPanel {
        Image image;
        public BackgroundPanel(Image image1) {
            image = image1;
            this.setOpaque(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, 480, 280, this);
        }
    }
}
