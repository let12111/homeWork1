package home.work;

import home.work.command.CommandHandManager;
import home.work.command.ICommandHadler;
import home.work.command.rateCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@ComponentScan
@Configuration
public class MyApplicationContextConfiguration {

@Bean
  public CommandHandManager   commandHandManager(rateCommandHandler rateCommand)
  {
      var commands= new HashMap<String, ICommandHadler>();
      commands.put("rate",rateCommand );
      CommandHandManager commandHandManager = new CommandHandManager(commands);
return commandHandManager;
  }



}
