package core.managers.structure;

import core.io.managers.IOManager;

public interface CanExecute {
    String getCommandList();
    void execute(String command, IOManager io);
    void executeScript(String path);
}
