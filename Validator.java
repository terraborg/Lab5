import java.util.Arrays;
public class Validator {
    final String commandList[]= {"help", "info", "show", "add", "update", "remove_by_id", "clear", "save",
            "execute_script", "exit", "add_if_max", "add_if_min", "history", "count_by_weapon_type",
            "filter_greater_than_car", "print_ascending"};
    public Boolean isCommand(String command){
        return Arrays.asList(commandList).contains(command);
    }

    public Boolean isCommandArg (String argument) {return null; }

    public Boolean isValid (String argument){
        return null;
    }
}
