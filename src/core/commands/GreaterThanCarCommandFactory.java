package core.commands;

import core.managers.ClientContext;
import core.HumanBeing;
import core.managers.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;
import core.io.managers.IOManager;
import core.io.readers.CarInput;
import core.io.readers.structure.WrongInputException;

public class GreaterThanCarCommandFactory extends CommandFactory {
    public GreaterThanCarCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "filter_greater_than_car", "вывести элементы, значение поля car которых больше заданного");
    }

    HumanBeing.Car argument;

    @Override
    public boolean readArgs(IOManager io) {
        try {
            io.getIn().nextLine();
            argument = new CarInput(io).read();
        } catch (WrongInputException e) {
            return false;
        }
        return true;
    }

    @Override
    public Command newInstance() {
        var res = new ServerCommand(getName(),getClient(),getServer()) {
            HumanBeing.Car argument;


            public void addArgument(HumanBeing.Car value) {
                argument = value;
            }

            @Override
            public void execute() {
                var res = getServer().getDataBaseHolder().getByFilter(e -> new HumanBeing.Car("",null).compare(e.getCar(),argument) > 0);
                if(res.length==0) {
                    getClient().putCallback(new CallbackUnit(false,"Таких элементов не найдено"));
                    return;
                }
                var b = new StringBuilder();
                for(var e : res)
                    b.append(e).append("\n");
                getClient().putCallback(new CallbackUnit(true,"Резудьтат поиска:\n"+b));
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
