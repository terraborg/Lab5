package core.managers;

import core.commands.structure.Command;
import core.database.DataBaseHolder;
import core.managers.structure.ServerContext;

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