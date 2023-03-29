package core.commands;

import core.managers.structure.ClientContext;
import core.managers.structure.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Фабрика создающая экземпляры команды save
 */
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
                } catch (NullPointerException e) {
                    getClient().putCallback(new CallbackUnit(false,"Переменная окружения не определена"));
                } catch (XMLStreamException e) {
                    getClient().putCallback(new CallbackUnit(false,"Файл поврежден или недоступен"));
                } catch (IOException e) {
                    getClient().putCallback(new CallbackUnit(false,"Файл не найден"));
                }
            }
        };
    }
}
