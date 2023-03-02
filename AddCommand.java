package core.commands;

import core.CommandManager;
import core.database.DataBaseHolder;
import core.HumanBeing;

/**
 * @author Volovich Alexey
 * Команда добавления элемента в базу данных
 * @see DataBaseHolder
 * @see HumanBeing
 */

public class AddCommand implements Command {

    /**
     * Добавляемый элемент
     */
    private final HumanBeing argument;

    AddCommand(HumanBeing argument)
    {
        this.argument = argument;
    }
    @Override
    public void execute(DataBaseHolder<HumanBeing> dataBase) {
        HumanBeing new_e = argument;
        dataBase.add(new_e);
        Long id = new_e.getId();
        //TODO make callback
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        //TODO make descr
        return null;
    }
}
