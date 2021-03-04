package io;

import tweak.ZSHelper;

public class ZSGenerator {

    public static class ZSLineCommand {
        public String command;
        public ZSLineCommand(String command) {
            this.command = command;
        }

        public static ZSLineCommand generateCommandByItemIO(ZSHelper.OperationType type, String[] inputs, String output) {
            StringBuilder string = new StringBuilder();
            for (int i = 0 ; i < inputs.length ; i++) {
                if (i == inputs.length - 1) {
                    string.append(inputs[i]);
                }
                string.append(inputs[i]);
                string.append(',');
            }
            String commandBase = type.command;
            commandBase = commandBase.replaceAll("!input_placeholder", string.toString());
            String outputBuilder = output + ',';
            commandBase = commandBase.replaceAll("!out_placeholder", outputBuilder);
            System.out.println(commandBase);
            return new ZSLineCommand(commandBase);
        }

    }

}
