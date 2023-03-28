package core.commands;

import core.managers.ClientContext;
import core.managers.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;
import core.utils.GeneratorID;

/**
 * Фабрика создающая экземпляры команды clear
 */
public class ClearCommandFactory extends CommandFactory {
    public ClearCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "clear", "очистить коллекцию");
    }

    @Override
    public Command newInstance() {
        return new ServerCommand(getName(),getClient(),getServer()) {
            @Override
            public void execute() {
                getServer().getDataBaseHolder().clear();
                GeneratorID.setId(1L);
                getClient().putCallback(new CallbackUnit(true));
            }
        };
    }
}
