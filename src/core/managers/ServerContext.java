package core.managers;

import core.commands.structure.Command;
import core.database.DataBaseHolder;

public interface ServerContext {
    DataBaseHolder getDataBaseHolder();

    String getHistory();

    String getHistoryWithArgs();

    void executeCommand(Command command);
}
