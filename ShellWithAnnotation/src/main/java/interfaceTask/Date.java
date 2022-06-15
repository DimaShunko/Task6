package interfaceTask;

import java.time.LocalDate;

@ShellManage
public class Date implements Command {

    @Override
    public String getName() {
        return "date";
    }

    @Override
    public void exec() {
        LocalDate date =  LocalDate.now();
        System.out.println(date);
    }

    @Override
    public String help() {
        return "date - дата";
    }
}
