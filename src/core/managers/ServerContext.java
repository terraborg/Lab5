package core.managers;

import core.commands.structure.Command;
import core.database.DataBaseHolder;

/**
 * Интерфейс задающий объект взаймодействия с сервером
 */
public interface ServerContext {
    DataBaseHolder getDataBaseHolder();

    String getHistory();

    String getHistoryWithArgs();

    void executeCommand(Command command);
}
