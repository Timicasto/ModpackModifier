package tweak;

import java.util.HashMap;
import java.util.Map;

public class ZSHelper {
    public Map<Integer, ZSOperation> operations = new HashMap<>();
    public static class ZSOperation {

    }

    public enum OperationType {
        NULL(0, ""),
        ADD_VANILLA_SHAPELESS_CRAFTING(1, "recipes.addShapeless(!$^out_placeholder, !$^input_placeholder)"),
        REMOVE_VANILLA_CRAFTING(2, "recipes.remove(!$^out_placeholder);"),
        MOD_AA_ATOMIC_RECONSTRUCTOR(3, ""),
        MOD_AA_BALL_OF_FUR(4, ""),
        MOD_AA_COMPOST(5, ""),
        MOD_AA_CRUSHER(6, ""),
        MOD_AA_EMPOWERER(7, ""),
        MOD_AA_MINING_LENS(8, ""),
        MOD_AA_OIL_GENERATOR(9, ""),
        MOD_AA_TREASUTE_CHEST(10, ""),
        MOD_BM_ALCHEMY_ARRAY(11, ""),
        MOD_BM_ALCHEMY_TABLE(12, ""),
        MOD_BM_BLOOD_ALTAR(13, ""),
        MOD_BM_TARTARIC_TABLE(14, ""),
        MOD_BOTANIA_BROW(15, ""),
        MOD_BOTANIA_ELVEN_TRADE(16, ""),
        MOD_BOTANIA_MANA_INFUSIONS(17, ""),
        MOD_BOTANIA_ORECHID(18, ""),
        MOD_BOTANIA_ORECHID_IGNEM(19, ""),
        MOD_BOTANIA_PETAL_APOTHECARY(20, ""),
        MOD_BOTANIA_PURE_DAISY(21, ""),
        MOD_BOTANIA_RUNE_ALTAR(22, ""),
        MOD_CHISEL_CARVING(23, ""),
        MOD_EU2_CRUSHER(24, "");

        public String command;
        public int index;

        public OperationType(int index, String command) {
                this.command = command;
                this.index = index;
        }

        public static String getSyntax() {
                return command;
        }

        public static int getIndex() {
                return index;
        }

        public static OperationType getTypeByIndex(int index) {
                OperationType[] types = OperationType.values();
                for (int i = 0 ; i < types.length ; i++) {
                        if (types[i].getIndex() == index) {
                                return types[i];
                        }
                }
                return OperationType.NULL;
        }
    }
}
