package timicasto.io;

import timicasto.tweak.ZSHelper;

import java.util.ArrayList;
import java.util.List;

public class ZSGenerator {
    public static List<String> commands = new ArrayList<>();

    public static class ZSLineCommand {
        public String command;

        public ZSLineCommand(String command) {
            this.command = command;
        }

        public static String generateCommandByItemIO(ZSHelper.OperationType type, String[] inputs, String output) {
            if (type.equals(ZSHelper.OperationType.ADD_VANILLA_SHAPELESS_CRAFTING)) {
                StringBuilder string = new StringBuilder();
                string.append('[');
                for (int i = 0; i < inputs.length; i++) {
                    if (i == inputs.length - 1) {
                        string.append(inputs[i]);
                    } else {
                        string.append(inputs[i]);
                        string.append(',');
                        string.append(' ');
                    }
                }
                string.append(']');
                String commandBase = type.command;
                commandBase = commandBase.replaceAll("!input_placeholder", string.toString());
                String outputBuilder = output;
                commandBase = commandBase.replaceAll("!out_placeholder", outputBuilder);
                System.out.println(commandBase);
                return commandBase;
            }
            if (type.equals(ZSHelper.OperationType.REMOVE_VANILLA_CRAFTING)) {
                String commandBase = type.command;
                commandBase = commandBase.replaceAll("!out_placeholder", output);
                System.out.println(commandBase);
                return commandBase;
            }
            return null;
        }
    }

}
