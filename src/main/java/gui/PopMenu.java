package gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PopMenu {

    public static BufferedImage icon;

    static {
        try {
            icon = ImageIO.read(new URL("http://timicasto.sukazyo.cc:12000/logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
