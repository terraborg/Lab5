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
import core.io.readers.IdInput;
import core.io.readers.structure.WrongInputException;

/**
 * Фабрика создающая экземпляры команды update
 */
public class UpdateCommandFactory extends CommandFactory {

    public UpdateCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
    }

    private HumanBeing argument;
    private Long id;

    @Override
    public boolean readArgs(IOManager io) {
        try {
            id = new IdInput(io).read();
            argument = new HumanBeingInput(io).read();
        } catch (WrongInputException e) {
            return false;
        }
        return true;
    }

    @Override
    public Command newInstance() {
        var res = new ServerCommand(getName(),getClient(),getServer()) {
            private HumanBeing argument;
            private Long id;
            public void addArgument(HumanBeing value) {
                argument = value;
            }
            public void addArgument(Long value){
                id = value;
            }
            @Override
            public void execute() {
                if(getServer().getDataBaseHolder().updateById(id,argument))
                    getClient().putCallback(new CallbackUnit(true));
                else
                    getClient().putCallback(new CallbackUnit(false,"Такого элемента не существует"));

            }
            @Override
            public String toString() {
                return  super.toString()+ " "+ id + " " + argument;
            }
        };
        res.addArgument(id);
        res.addArgument(argument);
        return res;
    }

}
