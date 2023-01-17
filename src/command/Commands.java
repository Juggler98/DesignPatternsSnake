package command;

import java.util.HashMap;
import java.util.Map;

public class Commands {

    private static final Map<Integer, ICommand> commands = new HashMap<>();

    public static void execute(int key) {
        ICommand command = commands.get(key);
        if (command != null) {
            command.execute();
        }
    }

    public static void addCommand(int key, ICommand command) {
        commands.put(key, command);
    }


}
