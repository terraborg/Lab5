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
import utils.RandomHumanBeingFiller;

/**
 * Фабрика создающая экземпляры команды rnd_fill
 */
public class RndFillCommandFactory extends CommandFactory {
    public RndFillCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "rnd_fill count", "заполняет коллекцию случайными элементами");
    }
    private Long n;

    @Override
    public boolean readArgs(IOManager io) {
        try {
            n = new IdInput(io).read();
        } catch (WrongInputException e) {
            return false;
        }
        return true;
    }

    @Override
    public Command newInstance() {
        var res = new ServerCommand(getName(), getClient(), getServer()) {
            private Long n;


            public void addArgument(Long value) {
                n = value;
            }
            @Override
            public void execute() {
                for(var i = 0; i < n; i++)
                {
                    getServer().getDataBaseHolder().add(RandomHumanBeingFiller.getRandomHuman());
                }
                getClient().putCallback(new CallbackUnit(true));
            }
            @Override
            public String toString() {
                return  super.toString() + " " + n;
            }
        };
        res.addArgument(n);
        return res;
    }

}
