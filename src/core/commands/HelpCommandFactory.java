package core.commands;

import core.managers.ClientContext;
import core.managers.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;

/**
 * Фабрика создающая экземпляры команды help
 */
public class HelpCommandFactory extends CommandFactory {
    public HelpCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "help", "вывести справку по доступным командам");
    }

    @Override
    public Command newInstance() {
        return new Command(getName(),getClient()) {
            @Override
            public void execute() {
                getClient().putCallback(new CallbackUnit(true,"Список всех доступных команд:\n"+getClient().getCommandList()));
            }
        };
    }
}
