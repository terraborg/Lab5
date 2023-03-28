package core.managers;

import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.io.managers.IOManager;


public interface ClientContext {
    void putCallback(CallbackUnit callbackUnit);
    void exit(boolean save);
    String getCommandList();

    void execute(String command, IOManager io);

    void executeScript(String path);
}
