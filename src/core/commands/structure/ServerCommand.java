package core.commands.structure;

import core.managers.ClientContext;
import core.managers.ServerContext;

abstract public class ServerCommand extends Command {
    private final ServerContext server;

    public ServerCommand(String name, ClientContext client, ServerContext server) {
        super(name, client);
        this.server = server;
    }

    @Override
    public void preExecute() {
        getServer().executeCommand(this);
    }

    protected ServerContext getServer() {
        return server;
    }
}
