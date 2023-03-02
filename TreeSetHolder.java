package core.database;

import core.HumanBeing;
import core.file.FileIn;
import core.file.FileOut;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Volovich Alexey
 * Класс управляющий коллекцией, реализует интерфейс DataBaseHolder
 * @see DataBaseHolder
 */
public class TreeSetHolder implements DataBaseHolder<HumanBeing>{
    private final TreeSet<HumanBeing> collection;
    private final LocalDateTime creationTime;

    private final FileIn fileIn;
    private final FileOut fileOut;
    public TreeSetHolder(FileIn fileIn, FileOut fileOut) {
        this.fileIn = fileIn;
        this.fileOut = fileOut;
        creationTime = LocalDateTime.now();
        collection = new TreeSet<>();
    }


    @Override
    public DataBaseInfo getInfo() {
        String type = "TreeSet";
        return new DataBaseInfo(type,creationTime,collection.size());
    }

    @Override
    public void clear() {
        collection.clear();
    }

    @Override
    public void removeById(int id) {
        collection.removeIf(e -> e.getId() == id);
    }

    @Override
    public HumanBeing[] getAllElements() {
        return collection.toArray(new HumanBeing[0]);
    }

    @Override
    public void add(HumanBeing humanBeing) {
        collection.add(humanBeing);
    }

    @Override
    public void setAllElements(HumanBeing[] e) {
        collection.clear();
        Collections.addAll(collection,e);
    }

    @Override
    public int compareToMin(HumanBeing humanBeing) {
        try{
            return humanBeing.compareTo(collection.first());
        }
        catch (NoSuchElementException e)
        {
            return -1;
        }
    }

    @Override
    public int compareToMax(HumanBeing humanBeing) {
        try{
            return humanBeing.compareTo(collection.last());
        }
        catch (NoSuchElementException e)
        {
            return 1;
        }
    }

    @Override
    public int countByFilter(Filter<HumanBeing> filter) {
        int count = 0;
        for(var e : collection)
            if(filter.isMatching(e))
                count++;
        return count;
    }

    @Override
    public HumanBeing[] getByFilter(Filter<HumanBeing> filter) {
        ArrayList<HumanBeing> res = new ArrayList<>();
        for(var e : collection)
            if(filter.isMatching(e))
                res.add(e);
        return res.toArray(new HumanBeing[0]);
    }

    @Override
    public boolean updateById(int id, HumanBeing humanBeing) {
        return false;
    }

    public FileOut getFileOut() {
        return fileOut;
    }

    public FileIn getFileIn() {
        return fileIn;
    }
}
