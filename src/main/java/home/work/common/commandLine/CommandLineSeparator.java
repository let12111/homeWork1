package home.work.common.commandLine;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class CommandLineSeparator {
    private static final int POSITION_FOR_CURRENCY = 0;

    public CommandLineResult parse(String commandText) {
        var listParam = new ArrayList<>(Arrays.asList(commandText.replaceAll("\\s", "#").split("#")));
        String commandName=listParam.get(POSITION_FOR_CURRENCY);
        listParam.remove(POSITION_FOR_CURRENCY);
        var result = new CommandLineResult(commandName, listParam);
        return result;
    }
}
