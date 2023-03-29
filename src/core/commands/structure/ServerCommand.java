package core.commands.structure;

import core.managers.structure.ClientContext;
import core.managers.structure.ServerContext;

/**
 * Абстрактный класс для команд, которые необходимо передать на сервер.
 */
abstract public class ServerCommand extends Command {
    private final ServerContext server;

    public ServerCommand(String name, ClientContext client, ServerContext server) {
        super(name, client);
        this.server = server;
    }

    /**
     * В данной функции в дальнейшем предполагается сделать передачу данных на сервер
     */
    @Override
    public void preExecute() {
        getServer().executeCommand(this);
    }

    protected ServerContext getServer() {
        return server;
    }
}
