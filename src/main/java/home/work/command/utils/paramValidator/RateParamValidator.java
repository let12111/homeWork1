package home.work.command.utils.paramValidator;

import home.work.command.utils.checker.Checker;

import java.util.List;

public class RateParamValidator
{
    Checker checker;
    public RateParamValidator(Checker checker)
    {
     this.checker=   checker;
    }
    public void Validate(List<String> params) throws Exception {
        checker.check(params);
    }
}
