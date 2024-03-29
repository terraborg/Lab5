package core.commands;

import core.managers.structure.ClientContext;
import core.managers.structure.ServerContext;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.io.managers.IOManager;
import core.io.readers.NameInput;
import core.io.readers.structure.WrongInputException;

/**
 * Фабрика создающая экземпляры команды execute_script
 */
public class ExecuteScriptCommandFactory extends CommandFactory {
    public ExecuteScriptCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "execute_script file_name", "считывает и исполняет скрипт из указанного файла");
    }
    private String path;

    @Override
    public boolean readArgs(IOManager io) {
        try {
            path = new NameInput(io,false,false,"").read();
        } catch (WrongInputException e) {
            return false;
        }
        return true;
    }

    @Override
    public Command newInstance() {
        var res = new Command("execute_script", getClient()) {
            private String path;
            public void addArgument(String value) {
                path = value;
            }

            @Override
            public void execute() {
                getClient().executeScript(path);
            }
        };
        res.addArgument(path);
        return res;
    }
}
