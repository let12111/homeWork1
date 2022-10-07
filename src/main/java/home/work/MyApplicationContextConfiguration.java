package home.work;

import home.work.command.CommandHandManager;
import home.work.command.handlers.ICommandHadler;
import home.work.command.handlers.rateCommandHandler;
import home.work.command.utils.checker.Checker;
import home.work.command.utils.checker.CheckerBuilder;
import home.work.command.utils.checker.concreteChecker.CurrencyCommandNameChecker;
import home.work.command.utils.checker.concreteChecker.NullOrEmptyCheker;
import home.work.command.utils.checker.concreteChecker.ParamsCountCheker;
import home.work.command.utils.paramValidator.RateParamValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

@Bean
   public RateParamValidator rateParamValidator(CheckerBuilder builder)
{
    var  noeChecker = new NullOrEmptyCheker();
    var paramsCountCheker=new ParamsCountCheker(2,2);
   var commands= new ArrayList<String>();
    commands.add("WEEK");
    commands.add("TOMORROW");
    var commandCheker= new CurrencyCommandNameChecker(commands);
    builder.add(noeChecker);
    builder.add(paramsCountCheker);
    builder.add(commandCheker
    );
    return new  RateParamValidator(builder.build());
}

}
