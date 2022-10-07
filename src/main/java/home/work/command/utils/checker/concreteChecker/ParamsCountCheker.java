package home.work.command.utils.checker.concreteChecker;

import home.work.command.utils.checker.Checker;
import org.springframework.stereotype.Component;

import java.util.List;


public class ParamsCountCheker extends Checker {
    public ParamsCountCheker(Integer minCommandSize, Integer maxCommandSize) {
        this.minCommandSize = minCommandSize;
        this.maxCommandSize = maxCommandSize;
    }

    Integer maxCommandSize;
    Integer minCommandSize;

    public void check(List<String> params) throws Exception {
        if (params.size() > maxCommandSize || params.size() < minCommandSize) {
            throw new Exception("Неправильное количество команд");
        }
        super.check(params);
    }
}
