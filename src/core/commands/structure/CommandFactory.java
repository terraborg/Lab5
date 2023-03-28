package core.commands.structure;

import core.managers.ClientContext;
import core.managers.ServerContext;
import core.io.managers.IOManager;

abstract public class CommandFactory {
    private final ClientContext client;
    private final ServerContext server;

    private final String name;
    private final String description;

    public CommandFactory(ClientContext client, ServerContext server, String name, String description) {
        this.client = client;
        this.server = server;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean readArgs(IOManager io)
    {
        return true;
    }
    abstract public Command newInstance();

    public ServerContext getServer() {
        return server;
    }

    public ClientContext getClient() {
        return client;
    }
}
