package home.work;
import home.work.command.CommandHandManager;
import home.work.common.commandLine.CommandLineSeparator;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {



        var commandHandler = DI.getAppContext().getBean(CommandHandManager.class);
        var commandSeparator = DI.getAppContext().getBean(CommandLineSeparator.class);
        String EXIT_COMMAND = "exit";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter some command, or '" + EXIT_COMMAND + "' to quit");
        while (true) {

            System.out.print("> ");
            String input = br.readLine();
           // System.out.println(input);

            if (input.length() == EXIT_COMMAND.length() && input.toLowerCase().equals(EXIT_COMMAND)) {
                System.out.println("Exiting.");
                return;
            }
            try {
                if (input.length()==0){continue;}
                var commandPar = commandSeparator.parse(input);
                var commandResult = commandHandler.Handle(commandPar.getCommandName(), commandPar.getCommandParams());

                System.out.println(commandResult);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }


    }
}
