package core.commands;

import core.managers.structure.ClientContext;
import core.managers.structure.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;

/**
 * Фабрика создающая экземпляры команды history_with_args
 */
public class HistoryWithArgsCommandFactory extends CommandFactory {
    public HistoryWithArgsCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "history_with_args", "вывести последние 14 команд (с их аргументами)");
    }

    @Override
    public Command newInstance() {
        return new ServerCommand(getName(),getClient(),getServer()) {
            @Override
            public void execute() {
                getClient().putCallback(new CallbackUnit(true,"История выполненных команд:\n" + getClient().getHistoryWithArgs()));
            }
        };
    }
}
