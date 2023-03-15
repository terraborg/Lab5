package core.database;

import core.file.FileIn;
import core.file.FileOut;

import java.nio.file.DirectoryStream;
import java.util.function.Predicate;

/**
 * @author Volovich Alexey
 * @deprecated Интерфейс задающий поведение баз данных.
 * @see core.commands.Command
 * @see core.CommandManager
 */
public interface DataBaseHolder<E extends Comparable<?>> {
    /**
     * @return Команда возвращает общую информацию о базе данных в виде объекта класса DataBaseInfo
     * @see DataBaseInfo
     */
    DataBaseInfo getInfo();

    /**
     * Удаляет из базы данных все элементы.
     */
    void clear();

    /**
     * Удаляет из базы данных один элемент.
     * @param id - индекс удаляемого элемента.
     */
    void removeById(int id);

    /**
     * @return Возвращет все элементы базы данных в виде массива
     */
    E[] getAllElements();

    /**
     * Добавляет элемент в коллекцию
     * @param e - элемент который будет добавлен.
     */
    void add(E e);

    /**
     * Заменяет все элементы на новые.
     * @param e - массив новых элементов
     */
    void setAllElements(E[] e);

    /**
     * Сравнивает элемент с минимальным элементом коллекции.
     * @param e - элемент который нужно сравнить
     * @return Возвращаемое значение аналогично возвращаемым значением функции compareTo интерфейса Comparable
     */
    int compareToMin(E e);
    /**
     * Сравнивает элемент с максимальным элементом коллекции.
     * @param e - элемент который нужно сравнить
     * @return Возвращаемое значение аналогично возвращаемым значением функции compareTo интерфейса Comparable
     */
    int compareToMax(E e);

    /**
     * Считает элементы проходящие фильтр
     * @param filter - фильтр элементов
     * @return Возвращает количество подсчитанных элементов
     * @see Filter
     */
    int countByFilter(Filter<E> filter);
    /**
     * Возвращает элементы проходящие фильтр
     * @param filter - фильтр элементов
     * @return Возвращает массив элементов.
     * @see Filter
     */
    E[] getByFilter(Filter<E> filter);

    /**
     * Обновляет элемент базы данных.
     * @param id - индекс обновляемого элемента
     * @param e - новое значение элемента
     * @return Возвращает false, если нужного элемента нет в базе данных, и true в противном случае.
     */
    boolean updateById(int id, E e);

    /**
     * @return Возвращает класс отвечающий за запись данных в файл
     * @see FileOut
     */
    FileOut getFileOut();
    /**
     * @return Возвращает класс отвечающий получение данных из файл
     * @see FileIn
     */
    FileIn getFileIn();
}
