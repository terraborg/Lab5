package core.commands;

import core.managers.structure.ClientContext;
import core.managers.structure.ServerContext;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;

/**
 * Фабрика создающая экземпляры команды exit_with_save
 */
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
