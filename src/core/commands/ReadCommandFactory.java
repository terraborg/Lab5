package core.commands;

import core.managers.ClientContext;
import core.managers.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;
import core.database.DataBaseHolder;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

/**
 * Фабрика создающая экземпляры команды read
 */
public class ReadCommandFactory extends CommandFactory {

    public ReadCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "read", "считать коллекцию из XML файла");
    }

    @Override
    public Command newInstance() {
        return new ServerCommand(getName(),getClient(),getServer()) {
            @Override
            public void execute() {
                DataBaseHolder dataBase = getServer().getDataBaseHolder();
                try {
                    dataBase.setAllElements(dataBase.getFileIn().readFile());
                    var b = new StringBuilder();
                    int i = 0;
                    for(var x : dataBase.getAllElements())
                    {
                        b.append(x.getId().toString()).append(", ");
                        i++;
                    }
                    b.deleteCharAt(b.length()-1);
                    b.deleteCharAt(b.length()-1);
                    getClient().putCallback(new CallbackUnit(true,"В коллекцию считаны элементы с id:\n"+b));
                } catch (FileNotFoundException e) {
                    //e.printStackTrace();
                    getClient().putCallback(new CallbackUnit(false,"Файл не найден"));
                } catch (XMLStreamException e) {
                    //e.printStackTrace();
                    getClient().putCallback(new CallbackUnit(false,"Файл поврежден или не является XML"));
                }
            }
        };
    }
}
