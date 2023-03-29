package core.commands;

import core.managers.structure.ClientContext;
import core.HumanBeing;
import core.managers.structure.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;
import core.io.managers.IOManager;
import core.io.readers.HumanBeingInput;
import core.io.readers.structure.WrongInputException;

/**
 * Фабрика создающая экземпляры команды add_if_max
 */
public class AddIfMaxCommandFactory extends CommandFactory {
    public AddIfMaxCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
    }

    private HumanBeing argument;

    @Override
    public boolean readArgs(IOManager io) {
        try {
            io.getIn().nextLine();
            argument = new HumanBeingInput(io).read();
        } catch (WrongInputException e) {
            return false;
        }
        return argument!=null;
    }

    @Override
    public Command newInstance() {
        var res = new ServerCommand(getName(),getClient(),getServer()) {
            private HumanBeing argument;
            public void addArgument(HumanBeing value) {
                argument = value;
            }
            @Override
            public void execute() {
                if(getServer().getDataBaseHolder().compareToMax(argument)==1){
                    getServer().getDataBaseHolder().add(argument);
                    getClient().putCallback(new CallbackUnit(true,"Добавлен элемент с id = "+argument.getId().toString()));
                }
                else
                    getClient().putCallback(new CallbackUnit(false,"Элемент меньше максимального"));
            }
            @Override
            public String toString() {
                return  super.toString() + " " + argument;
            }
        };
        res.addArgument(argument);
        return res;
    }

}
