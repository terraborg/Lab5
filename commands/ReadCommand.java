package core.commands;

import core.CommandManager;
import core.HumanBeing;
import core.database.DataBaseHolder;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

/**
 * @author Volovich Alexey
 * @deprecated  Команда чтения из файла, выполняется при запуске программы
 * @see DataBaseHolder
 * @see Command
 */
public class ReadCommand implements Command {

    @Override
    public String getName() {
        return "read";
    }

    @Override
    public String getDescription() {
        //TODO make descr
        return null;
    }

    @Override
    public void execute(DataBaseHolder<HumanBeing> dataBase){

        try {
            dataBase.setAllElements(dataBase.getFileIn().readFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //TODO make callback
        } catch (XMLStreamException e) {
            e.printStackTrace();
            //TODO make callback2
        }
    }
}
