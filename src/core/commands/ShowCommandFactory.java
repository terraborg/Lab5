package core.commands;

import core.managers.ClientContext;
import core.managers.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;

import java.util.Arrays;

public class ShowCommandFactory extends CommandFactory {
    public ShowCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public Command newInstance() {
        return new ServerCommand(getName(),getClient(),getServer()) {
            @Override
            public void execute() {
                getClient().putCallback(new CallbackUnit(true, Arrays.toString(getServer().getDataBaseHolder().getAllElements())));
            }
        };
    }
}
