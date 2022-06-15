package interfaceTask;


import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static List<Command> commands;

    Main() throws InstantiationException, IllegalAccessException {
        commands = new ArrayList<>();
        init(commands);
    }

    void init( List<Command> commands) throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections("interfaceTask", new SubTypesScanner(false));
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class).stream().collect(Collectors.toSet());

        for(Class<?> cl:allClasses){
            Annotation[] annotations = cl.getAnnotations();
            for (Annotation annotation:annotations){
                if(annotation instanceof  ShellManage){
                    commands.add((Command) cl.newInstance());
                }
            }
        }
    }

    public static List<Command> getCommands() {
        return commands;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        boolean b;

        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        System.out.println("Введите команду");

        while (true){
            String name = scanner.nextLine();
            b = false;
            for(Command command: main.getCommands()){
                if(command.getName().contentEquals(name)){
                    command.exec();
                    b = true;
                    break;
                }
            }
            if(!b){
                System.out.println("Такой команды нет");
            }
        }
    }
}
