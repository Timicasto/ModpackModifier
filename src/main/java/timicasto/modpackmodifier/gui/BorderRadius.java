package timicasto.modpackmodifier.gui;

import javax.swing.border.Border;
import java.awt.*;

public class BorderRadius implements Border {
    private Color color;

    public BorderRadius(Color color) {
        this.color = color;
    }

    public BorderRadius() {
        this.color = new Color(56575);
    }

    @Override
    public Insets getBorderInsets(Component component) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        g.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 32, 32);
    }
}
