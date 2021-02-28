package tweak;

import java.util.HashMap;
import java.util.Map;

public class ZSHelper {
    public Map<Integer, ZSOperation> operations = new HashMap<>();
    public static class ZSOperation {

    }

    protected enum OperationType {
        ADD_VANILLA_CRAFTING,
        REMOVE_VANILLA_CRAFTING,

    }
}
