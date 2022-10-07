package home.work.common.commandLine;

import java.util.List;

public class CommandLineResult {
    public CommandLineResult(String commandName, List<String> commandParams) {
    this.commandName=commandName;
    this.commandParams=commandParams;
    }

    public String getCommandName() {
        return commandName;
    }

    public List<String> getCommandParams() {
        return commandParams;
    }

    String commandName;
    List<String> commandParams;
}
