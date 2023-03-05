package core.commands;

import core.HumanBeing;
import core.database.DataBaseHolder;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

/**
 * @author Volovich Alexey
 * @deprecated Интерфейс задающий поведение команд.
 * @see core.CommandManager
 */
public interface Command {
    /**
     * @return Возвращает имя команды.
     */
    String getName();

    /**
     * @return Возвращает описание команды
     */
    String getDescription();

    /**
     * Применяет команду к базе данных
     * @param dataBase - база данных которой управляет команда.
     * @see DataBaseHolder
     */
    void execute(DataBaseHolder<HumanBeing> dataBase);
}
