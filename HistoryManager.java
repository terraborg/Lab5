package core;

import core.commands.Command;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * @author Volovich Alexey
 * @deprecated Класс хранящий историю исполнения команд
 * @see Command
 */
public class HistoryManager {
    private final ArrayDeque<Command> queue;
    private final int maxSize = 14;
    public HistoryManager()
    {
        queue = new ArrayDeque<>();
    }

    /**
     * Возвращает массив названий последних 14 исполненных команд
     * @return String[]
     */
    public String[] getHistory()
    {
        Command[] arr = queue.toArray(new Command[0]);
        ArrayList<String> ans = new ArrayList<>();
        for (Command command : arr)
            ans.add(command.getName());
        return ans.toArray(new String[0]);
    }

    /**
     * Добавляет команду в очередь, если размер превысил допустимое количество, удаляет самую старую команду
     * @param command
     */
    public void addCommand(Command command)
    {
        queue.addLast(command);
        if(queue.size() == maxSize + 1)
            queue.removeFirst();
    }
}
