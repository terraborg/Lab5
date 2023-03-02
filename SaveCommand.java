package core.commands;

import core.CommandManager;
import core.HumanBeing;
import core.database.DataBaseHolder;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;


/**
 * @author Volovich Alexey
 * Команда сохраняющая базу данных в файл
 * @see DataBaseHolder
 */
public class SaveCommand implements Command {
    public SaveCommand(CommandManager myManager) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        //TODO make descr
        return null;
    }

    @Override
    public void execute(DataBaseHolder<HumanBeing> dataBase) {
        try {
            dataBase.getFileOut().writeCollection(dataBase);
        } catch (IOException e) {
            //TODO make callback
        } catch (XMLStreamException e) {
            //TODO make callback2
        }
    }
}
