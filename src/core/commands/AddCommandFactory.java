package core.commands;

import core.managers.ClientContext;
import core.HumanBeing;
import core.managers.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;
import core.io.managers.IOManager;
import core.io.readers.HumanBeingInput;
import core.io.readers.structure.WrongInputException;

public class AddCommandFactory extends CommandFactory {
    public AddCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "add {element}", "добавить новый элемент в коллекцию");
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
                HumanBeing new_e = argument;
                getServer().getDataBaseHolder().add(new_e);
                getClient().putCallback(new CallbackUnit(true,"Добавлен элемент с id = "+new_e.getId().toString()));
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
