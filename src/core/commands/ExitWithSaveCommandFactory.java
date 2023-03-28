package core.commands;

import core.managers.ClientContext;
import core.managers.ServerContext;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;

public class ExitWithSaveCommandFactory extends CommandFactory {
    public ExitWithSaveCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "exit_with_save", "завершает работу программы с сохранением данных");
    }

    @Override
    public Command newInstance() {
        return new Command("exit_with_save",getClient()) {
            @Override
            public void execute() {
                getClient().exit(true);
            }
        };
    }
}
