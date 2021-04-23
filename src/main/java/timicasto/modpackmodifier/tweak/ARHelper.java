package timicasto.modpackmodifier.tweak;

import timicasto.modpackmodifier.editors.EditorHandler;
import timicasto.modpackmodifier.tweak.arobj.Astronomical;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ARHelper {
    // Integer: Star index, Star: Star Object
    public static Map<Integer, Astronomical.Star> stars = new HashMap<>();

    public static void refresh(JFrame panel) throws InterruptedException {
        System.out.println("Stars: " + stars.toString());
        Thread.sleep(1000);
        EditorHandler.refreshARDisplay(stars, panel);
    }
}
