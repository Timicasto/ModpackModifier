import com.jhlabs.image.GaussianFilter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Utils {
    public static Image blur(BufferedImage input, int radius) {
        long startTime = System.currentTimeMillis();
        GaussianFilter filter = new GaussianFilter();
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
        filter.setRadius(radius);
        filter.filter(input, output);
        System.out.println("执行时间: " + (System.currentTimeMillis() - startTime) + "ms");
        return output;
    }

}
