package core.commands;

import core.managers.ClientContext;
import core.managers.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;

public class ShowIdListCommandFactory extends CommandFactory {
    public ShowIdListCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "show_id_list", "вывести в консоль список id всех элементов коллекции");
    }

    @Override
    public Command newInstance() {
        return new ServerCommand(getName(),getClient(),getServer()) {
            @Override
            public void execute() {
                var res = getServer().getDataBaseHolder().getAllElements();
                var b = new StringBuilder();
                for(var e : res)
                    b.append(e.getId()).append("\n");
                getClient().putCallback(new CallbackUnit(true,"Список id элементов:\n"+b));
            }
        };
    }
}
