package core.database;

import java.time.LocalDateTime;

/**
 * @author Volovich Alexey
 * Класс хранящий общую информацию о базе данных
 * @see DataBaseHolder
 */
public class DataBaseInfo {
    private final String type;
    private final java.time.LocalDateTime creationTime;
    private final int size;

    public DataBaseInfo(String type, LocalDateTime creationTime, int size) {
        this.type = type;
        this.creationTime = creationTime;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Информация о коллекции:\n" +
                "Тип: " + type + "\n" +
                "Дата создания: " + creationTime.toLocalDate() + "\n" +
                "Время создания: " + creationTime.toLocalTime().withNano(0) + "\n" +
                "Количество элементов: " + size + "\n";
    }
}
