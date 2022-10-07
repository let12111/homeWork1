package home.work.command.handlers;

import java.util.List;

public interface ICommandHadler {


    public  String executeCommand( List<String> params) throws Exception;
}
