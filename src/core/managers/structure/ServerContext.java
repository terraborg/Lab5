package core.managers.structure;

import core.commands.structure.Command;
import core.database.DataBaseHolder;

/**
 * Интерфейс задающий объект взаймодействия с сервером
 */
public interface ServerContext {
    DataBaseHolder getDataBaseHolder();
    void executeCommand(Command command);
}
