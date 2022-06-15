package interfaceTask;

@ShellManage
public class Exit implements Command {
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public void exec() {
        System.out.println("Выход");
        System.exit(0);
    }

    @Override
    public String help() {
        return "exit - выход";
    }
}
