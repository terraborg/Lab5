package core.database;

/**
 * @author Volovich Alexey
 * @deprecated Функциональный интерфейс для фильтрации элементов базы данных
 * @see DataBaseHolder
 */
public interface Filter<E> {
    /**
     * Команда проверяющая на соответствие элемента базы данных фильтру
     * @param e - элемент базы данных
     * @return Возвращает true в случае когда элемент соотвотствует фильтру и false в обратном случае
     */
    boolean isMatching(E e);
}
