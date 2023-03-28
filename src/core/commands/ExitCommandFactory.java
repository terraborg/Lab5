package core.commands;

import core.managers.ClientContext;
import core.managers.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;

/**
 * Фабрика создающая экземпляры команды exit
 */
public class ExitCommandFactory extends CommandFactory {
    public ExitCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "exit", "завершает работу программы (без сохранения)");
    }

    @Override
    public Command newInstance() {
        return new Command("exit",getClient()) {
            @Override
            public void execute() {
                getClient().putCallback(new CallbackUnit(true, "Выполнение программы завершено\n"));
                getClient().exit(false);
            }
        };
    }
}
