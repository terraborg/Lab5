package core;

import core.commands.*;
import core.database.DataBaseHolder;
import core.file.FileIn;
import core.file.FileOut;

import java.util.HashMap;

/**
 * @author Volovich Alexey
 * Класс управляющий командами, связывает команды с базой данных и историей исполняемых команд
 * @see DataBaseHolder
 * @see HistoryManager
 */
public class CommandManager {
    private final DataBaseHolder<HumanBeing> dataBaseHolder;
    private final HistoryManager history;

    public CommandManager(DataBaseHolder<HumanBeing> dataBaseHolder) {
        this.dataBaseHolder = dataBaseHolder;
        history = new HistoryManager();
    }

    /**
     * Возвращает массив имён последних 14 выполненных команд
     * @return String[]
     */
    public String[] getHistory() {
        return history.getHistory();
    }

    /**
     * Выполняет команду command
     * @see Command
     * @param command
     */
    public void executeCommand(Command command)
    {
        command.execute(dataBaseHolder);
        history.addCommand(command);
    }

}