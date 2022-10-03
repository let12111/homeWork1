package home.work.command;

import java.util.List;
import java.util.Map;

public class CommandHandManager {

    public CommandHandManager(Map<String, ICommandHadler> commands) {
        this.commands = commands;

    }

    public String Handle(String command, List<String> params) throws Exception {
        if (!commands.containsKey(command)) {
            throw new Exception("Unknown command");
        }

        var commandHabdler = commands.get(command);
        return commandHabdler.executeCommand(params);
    }

    Map<String, ICommandHadler> commands;
}
