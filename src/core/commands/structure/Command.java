package core.commands.structure;

import core.managers.ClientContext;
import core.managers.ServerManager;

/**
 * @author Volovich Alexey
 * Интерфейс задающий поведение команд.
 * @see ServerManager
 */
public abstract class Command {
    private final String name;
    private ClientContext client;

    protected ClientContext getClient() {
        return client;
    }

    public Command(String name, ClientContext client) {
        this.name = name;
        this.client = client;
    }

    /**
     * @return Возвращает имя команды.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Применяет команду
     */
    abstract public void execute();

    public void preExecute()
    {
        execute();
    }

    public void addArgument(Object value) {

    }

    public void setClient(ClientContext client) {
        this.client = client;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
