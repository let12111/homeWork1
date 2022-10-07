package home.work.command.utils.checker;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CheckerBuilder {

    List<Checker>  chekerList =new ArrayList<Checker>();

    Checker checker;

    public void add(Checker checker)
    {
        if (checker==null){
        checker=checker;
        }
        checker.setSuccessor(this.checker);
        this.checker=checker;
    }

    public Checker  build()
    {
       return checker;
    }

}
