package home.work.command.utils.checker.concreteChecker;

import home.work.command.utils.checker.Checker;

import java.util.ArrayList;
import java.util.List;

public class CurrencyCommandNameChecker extends Checker {
    List<String> commands;
    public  CurrencyCommandNameChecker(List<String> commands)
    {
        this.commands=commands;
    }
    int POSTITION_OF_CURRENCY=0;
    public   void check(List<String> params)throws Exception
    {
        ArrayList<String> listParam=new ArrayList<>(params);
        listParam.remove(POSTITION_OF_CURRENCY);
        String badCommand="";
    boolean existCommand=true;
        for (var param:listParam ) {
          if ( !commands.stream().anyMatch(s->s.toLowerCase().equals(param.toLowerCase())))
          {
              badCommand=param;
              existCommand=false;
          }
        }
 if (!existCommand)
 {
     throw new Exception("Неправильный параметр команды: "+badCommand);

 }
        super.check(params);
    }

}
