package timicasto.modpackmodifier.io;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import timicasto.modpackmodifier.tweak.CMHelper;

import java.util.ArrayList;
import java.util.List;

public class CMJsonGenerator {
    public static String generateJson(String[][][] gradients, String input, String output, CMHelper.Attributes attributes, CMHelper.Item... variables) {
        JsonObject container = new JsonObject();
        container.addProperty("name", attributes.name);
        container.addProperty("disabled", attributes.disabled ? "true" : "false"); // disabled
        List<String> target = new ArrayList<>();
        String temp = output;
        while (true) {
            String splitBy = "%^";
            String splitStr;
            int index = temp.indexOf(splitBy);
            if (index < 0) {
                break;
            }
            splitStr = temp.substring(0, index);
            System.out.println(splitStr);
            target.add(splitStr);
            temp = temp.substring(index + 1);
        }
        container.addProperty("target-item", target.get(0)); // target
        container.addProperty("target-meta", target.get(1)); // target meta
        container.addProperty("target-nbt", target.get(2)); // target nbt
        container.addProperty("target-count", target.get(3));
        container.addProperty("duration", attributes.duration); // duration
        container.addProperty("symmetrical", "true"); // symmetrical
        List<String> catalyst = new ArrayList<>();
        String catalystTemp = input;
        while (true) {
            String splitBy = "%^";
            String splitStr;
            int index = catalystTemp.indexOf(splitBy);
            if (index < 0) {
                break;
            }
            splitStr = catalystTemp.substring(0, index);
            System.out.println(splitStr);
            catalyst.add(splitStr);
            catalystTemp = catalystTemp.substring(index + 1);
        }
        container.addProperty("catalyst", catalyst.get(0)); // catalyst
        container.addProperty("catalyst-meta", catalyst.get(1)); // catalyst meta
        container.addProperty("catalyst-nbt", catalyst.get(2)); // catalyst nbt
        Gson gson = new Gson();
        JsonObject input_types = new JsonObject();
        for (int i = 0; i < variables.length; i++) {
            JsonElement itemVariable = gson.toJsonTree(variables[i]);
            input_types.add(i == 1 ? "a" : i == 2 ? "b" : i == 3 ? "c" : i == 4 ? "d" : i == 5 ? "e" : "f", itemVariable);
        }
        container.add("input-types", input_types);
        JsonArray shape = new JsonArray();
        for (int i = 0; i < gradients.length; i++) {
            JsonArray y = new JsonArray();
            for (int j = 0; j < gradients.length; j++) {
                JsonArray x = new JsonArray();
                for (int k = 0; k < gradients.length; k++) {
                    x.add(gradients[j][i][k]);
                }
                y.add(x);
            }
            shape.add(y);
        }
        container.add("shape", shape);
        System.out.println(container.getAsString());
        return container.getAsString();
    }
}
