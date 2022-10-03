package home.work.common;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
@Component
public class CommandLineSeparator {

  public  CommandLineResult parse(String commandText) {
        var result = new CommandLineResult();
        var listParam = new ArrayList<>( Arrays.asList(commandText.replaceAll("\\s", "#").split("#")));
        result.commandName =listParam.get(0);
        listParam.remove(0);
        result.commandParams = listParam;

        return result;
    }
}
