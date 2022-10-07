package home.work.command.utils.checker.concreteChecker;

import home.work.command.utils.checker.Checker;
import org.springframework.stereotype.Component;

import java.util.List;

public class NullOrEmptyCheker extends Checker {
    public   void check(List<String> params)throws Exception
    {
        if (params==null ||params.size()==0 )
            throw new Exception("Количество переменных не должно быть равно 0");

        super.check(params);
    }

}
