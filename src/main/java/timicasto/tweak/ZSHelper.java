package timicasto.tweak;

import timicasto.editors.EditorHandler;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ZSHelper {
    public static Map<Integer, ZSOperation> operations = new HashMap<>();

    public static void refresh(JFrame panel) throws InterruptedException {
        System.out.println(operations.toString());
        Thread.sleep(1000);
        EditorHandler.refreshDisplay(operations, panel);
    }

    public static class ZSOperation {
        public OperationType type;
        public String[] inputs;
        public String output;
        public int energyI, energyO;

        public ZSOperation(OperationType type, String[] inputs, String outputs, int energyIn, int energyOut) {
            this.type = type;
            this.inputs = inputs;
            this.output = outputs;
            this.energyI = energyIn;
            this.energyO = energyOut;
        }
    }

    public enum OperationType {
        NULL(0, "", "", Operation.CROSS),
        ADD_VANILLA_SHAPELESS_CRAFTING(1, "recipes.addShapeless(!out_placeholder, !input_placeholder);", "Add shapeless recipe", Operation.ADD),
        REMOVE_VANILLA_CRAFTING(2, "recipes.remove(!out_placeholder);", "Remove recipe by output", Operation.REMOVE),
        MOD_AA_ATOMIC_RECONSTRUCTOR_ADD(3, "mods.actuallyadditions.AtomicReconstructor.addRecipe(!input_placeholder, !out_placeholder, !energy_in_placeholder);", "Add a Atomic Reconstructor Recipe", Operation.ADD),
        MOD_AA_ATOMIC_RECONSTRUCTOR_REMOVE(4, "mods.actuallyadditions.AtomicReconstructor.removeRecipe(!out_placeholder);", "Remove a Atomic Reconstructor Recipe by output", Operation.REMOVE),
        MOD_AA_BALL_OF_FUR_ADD(5, "mods.actuallyadditions.BallOfFur.addReturn(!out_placeholder, !int_range_placeholder);", "Add a fur ball drop", Operation.ADD),
        MOD_AA_BALL_OF_FUR_REMOVE(6, "mods.actuallyadditions.BallOfFur.removeReturn(!out_placeholder);", "Add a fur ball drop", Operation.ADD);

        /*MOD_AA_COMPOST_ADD(, ""),
        MOD_AA_CRUSHER(, ""),
        MOD_AA_EMPOWERER(, ""),
        MOD_AA_MINING_LENS(, ""),
        MOD_AA_OIL_GENERATOR(, ""),
        MOD_AA_TREASUTE_CHEST(, ""),
        MOD_BM_ALCHEMY_ARRAY(, ""),
        MOD_BM_ALCHEMY_TABLE(, ""),
        MOD_BM_BLOOD_ALTAR(, ""),
        MOD_BM_TARTARIC_TABLE(, ""),
        MOD_BOTANIA_BROW(, ""),
        MOD_BOTANIA_ELVEN_TRADE(, ""),
        MOD_BOTANIA_MANA_INFUSIONS(, ""),
        MOD_BOTANIA_ORECHID(, ""),
        MOD_BOTANIA_ORECHID_IGNEM(, ""),
        MOD_BOTANIA_PETAL_APOTHECARY(, ""),
        MOD_BOTANIA_PURE_DAISY(, ""),
        MOD_BOTANIA_RUNE_ALTAR(, ""),
        MOD_CHISEL_CARVING(, ""),
        MOD_EU2_CRUSHER(, "");*/

        public String command;
        public int index;
        public String name;
        public Operation operation;

        OperationType(int index, String command, String name, Operation operation) {
                this.command = command;
                this.index = index;
                this.name = name;
                this.operation = operation;

        }

        public String getSyntax() {
                return command;
        }

        public int getIndex() {
                return index;
        }

        public static OperationType getTypeByIndex(int index) {
            OperationType[] types = OperationType.values();
            for (OperationType type : types) {
                if (type.getIndex() == index) {
                    return type;
                }
            }
            return OperationType.NULL;
        }
    }

    public enum Operation {
        ADD,
        REMOVE,
        CROSS
    }
}
