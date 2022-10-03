package home.work.command;

import java.util.List;

public interface ICommandHadler {


    public  String executeCommand( List<String> params) throws Exception;
}
