package core.commands;

import core.managers.structure.ClientContext;
import core.managers.structure.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;
import core.io.managers.IOManager;
import core.io.readers.IdInput;
import core.io.readers.structure.WrongInputException;

/**
 * Фабрика создающая экземпляры команды remove_by_id
 */
public class RemoveCommandFactory extends CommandFactory {
    public RemoveCommandFactory(ClientContext client, ServerContext server) {
        super(client, server,"remove_by_id id","удалить элемент из коллекции по его id");
    }
    private Long id;
    @Override
    public boolean readArgs(IOManager io) {
        try {
            id = new IdInput(io).read();
        } catch (WrongInputException e) {
            return false;
        }
        return true;
    }

    @Override
    public Command newInstance() {
        var res = new ServerCommand(getName(),getClient(),getServer()) {
            private Long id;

            public void addArgument(Long value) {
                id = value;
            }

            @Override
            public void execute() {
                if(!getServer().getDataBaseHolder().removeById(id))
                    getClient().putCallback(new CallbackUnit(false,"Такого элемента не существует"));
                else {
                    getClient().putCallback(new CallbackUnit(true));
                }
            }
            @Override
            public String toString() {
                return super.toString() + " " + id;
            }
        };
        res.addArgument(id);
        return res;
    }

}
