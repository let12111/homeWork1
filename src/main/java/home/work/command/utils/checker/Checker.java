package home.work.command.utils.checker;

import java.util.List;

public class Checker {

    public void setSuccessor(Checker successor) {
        this.successor = successor;
    }

     protected Checker successor;

    public   void check(List<String> params)throws Exception
    {
        if (successor!=null)
        successor.check(params);

    }

}
