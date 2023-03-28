package core.commands;

import core.managers.ClientContext;
import core.HumanBeing;
import core.managers.ServerContext;
import core.commands.structure.CallbackUnit;
import core.commands.structure.Command;
import core.commands.structure.CommandFactory;
import core.commands.structure.ServerCommand;
import core.io.managers.IOManager;
import core.io.readers.WeaponInput;
import core.io.readers.structure.WrongInputException;

/**
 * Фабрика создающая экземпляры команды count_by_weapon_type
 */
public class CountByWeaponTypeCommandFactory extends CommandFactory {
    public CountByWeaponTypeCommandFactory(ClientContext client, ServerContext server) {
        super(client, server, "count_by_weapon_type weaponType","вывести количество элементов, значение поля weaponType которых равно заданному");
    }
    HumanBeing.WeaponType argument;

    @Override
    public boolean readArgs(IOManager io) {
        try {
            argument = new WeaponInput(io,false,"").read();
        } catch (WrongInputException e) {
            return false;
        }
        return true;
    }

    @Override
    public Command newInstance() {
        var res = new ServerCommand(getName(),getClient(),getServer()) {
            private HumanBeing.WeaponType argument;

            public void addArgument(HumanBeing.WeaponType value) {
                argument = value;
            }

            @Override
            public void execute() {
                int res = getServer().getDataBaseHolder().countByFilter(e -> e.getWeaponType() == argument);
                getClient().putCallback(new CallbackUnit(true,"Количество посчитанных элементов равно "+res));
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
