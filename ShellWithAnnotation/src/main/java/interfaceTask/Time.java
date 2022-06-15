package interfaceTask;

import java.time.LocalTime;

@ShellManage
public class Time implements Command {
    @Override
    public String getName() {
        return "time";
    }

    @Override
    public void exec() {
        LocalTime time = LocalTime.now();
        System.out.println(time);
    }

    @Override
    public String help() {
        return "time - Московское время";
    }
}
