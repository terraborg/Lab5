package core.commands;

import core.managers.ClientContext;
import core.managers.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;

public class HistoryWithArgsCommandFactory extends CommandFactory {
    public HistoryWithArgsCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "history_with_args", "вывести последние 14 команд (с их аргументами)");
    }

    @Override
    public Command newInstance() {
        return new ServerCommand(getName(),getClient(),getServer()) {
            @Override
            public void execute() {
                getClient().putCallback(new CallbackUnit(true,"История выполненных команд:\n" + getServer().getHistoryWithArgs()));
            }
        };
    }
}
