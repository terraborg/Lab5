package core.commands;

import core.managers.ClientContext;
import core.managers.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class SaveCommandFactory extends CommandFactory {
    public SaveCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "save", "сохранить коллекцию в XML файл");
    }

    @Override
    public Command newInstance() {
        return new ServerCommand(getName(),getClient(),getServer()) {
            @Override
            public void execute() {
                try {
                    var dataBase = getServer().getDataBaseHolder();
                    dataBase.getFileOut().writeCollection(dataBase);
                    getClient().putCallback(new CallbackUnit(true));
                } catch (IOException | XMLStreamException e) {
                    getClient().putCallback(new CallbackUnit(false,e.getMessage()));
                }
            }
        };
    }
}
