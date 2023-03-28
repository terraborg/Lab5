package core.managers;

import core.commands.structure.Command;
import core.database.DataBaseHolder;

/**
 * @author Volovich Alexey
 * Класс управляющий командами, связывает команды с базой данных и историей исполняемых команд
 * @see DataBaseHolder
 * @see HistoryManager
 */
public class ServerManager implements ServerContext {
    private final DataBaseHolder dataBaseHolder;
    private final HistoryManager history;

    public ServerManager(DataBaseHolder dataBaseHolder) {
        this.dataBaseHolder = dataBaseHolder;
        history = new HistoryManager();
    }

    /**
     * Возвращает массив имён последних 14 выполненных команд
     * @return String[]
     */
    public String getHistory() {
        var b = new StringBuilder();
        for(var e : history.getHistory())
        {
            b.append(e.getName()).append("\n");
        }
        return b.toString();
    }

    @Override
    public String getHistoryWithArgs() {
        var b = new StringBuilder();
        for(var e : history.getHistory())
        {
            b.append(e.toString()).append("\n");
        }
        return b.toString();
    }

    /**
     * Выполняет команду command
     */
    public void executeCommand(Command command)
    {
        command.execute();
        history.addCommand(command);
    }

    @Override
    public DataBaseHolder getDataBaseHolder() {
        return dataBaseHolder;
    }
}