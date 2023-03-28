package core.database;

import core.HumanBeing;
import core.file.FileIn;
import core.file.FileOut;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;

/**
 * @author Volovich Alexey
 * Класс управляющий коллекцией, реализует интерфейс DataBaseHolder
 * @see DataBaseHolder
 */
public class TreeSetHolder implements DataBaseHolder{
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
    public boolean removeById(Long id) {
        return collection.removeIf(e -> e.getId().longValue() == id.longValue());
    }

    @Override
    public boolean contains(Long id) {
        return countByFilter(e -> e.getId().equals(id)) > 0;
    }

    @Override
    public HumanBeing[] getAllElements() {
        return collection.toArray(new HumanBeing[0]);
    }

    @Override
    public int getSize() {
        return collection.size();
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
    public int countByFilter(Predicate<HumanBeing> filter) {
        int count = 0;
        for(var e : collection)
            if(filter.test(e))
                count++;
        return count;
    }

    @Override
    public HumanBeing[] getByFilter(Predicate<HumanBeing> filter) {
        ArrayList<HumanBeing> res = new ArrayList<>();
        for(var e : collection)
            if(filter.test(e))
                res.add(e);
        return res.toArray(new HumanBeing[0]);
    }

    @Override
    public boolean updateById(Long id, HumanBeing humanBeing) {
        var res = getByFilter(e -> e.getId().equals(id));
        if(res.length !=0)
        {
            res[0].update(humanBeing);
            return true;
        }
        return false;
    }

    public FileOut getFileOut() {
        return fileOut;
    }

    public FileIn getFileIn() {
        return fileIn;
    }
}
