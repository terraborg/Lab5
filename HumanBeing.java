package core;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.lang.Math.*;

/**
 * @author Volovich Alexey
 * Класс описывающий хранимые элементы коллекции.
 * Полностью соответствует условию лабораторной.
 */

public class HumanBeing implements Comparable<HumanBeing> {
    private static long last_id = 1;
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDateTime creationTime;
    private Boolean realHero;
    private Boolean hasToothpick;
    private float impactSpeed;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Boolean getRealHero() {
        return realHero;
    }

    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    public float getImpactSpeed() {
        return impactSpeed;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Mood getMood() {
        return mood;
    }

    public Car getCar() {
        return car;
    }

    public HumanBeing(XMLStreamReader reader) throws XMLStreamException {
        new InnerXMLReader().readObject(reader);
    }

    /**
     * Функция заполняющая авто-генерируемые поля.
     */
    private void generateInfo() {
        id = HumanBeing.last_id;
        HumanBeing.last_id++;
        creationTime = java.time.LocalDateTime.now();
    }

    public HumanBeing(String name, Coordinates coordinates, Boolean realHero, Boolean hasToothpick, float impactSpeed, WeaponType weaponType, Mood mood, Car car) {
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
        generateInfo();
    }

    /**
     * Функция сравнения из интерфейса Comparable.
     * @param humanBeing the object to be compared.
     * @return int
     */
    @Override
    public int compareTo(HumanBeing humanBeing) {
        if(realHero.compareTo(humanBeing.realHero)!=0)
            return realHero.compareTo(humanBeing.realHero);

        if(car.compareTo(humanBeing.car)!=0)
            return car.compareTo(humanBeing.car);

        if(weaponType==null)
        {
            if(humanBeing.weaponType!=null)
                return -1;
        } else if (weaponType != humanBeing.weaponType) {
            return weaponType.power - humanBeing.weaponType.power;
        }

        if(abs(impactSpeed - humanBeing.impactSpeed) > 0.0001)
            return (int) signum(impactSpeed - humanBeing.impactSpeed);

        if(hasToothpick.compareTo(humanBeing.hasToothpick)!=0)
            return hasToothpick.compareTo(humanBeing.hasToothpick);

        return (int) (id - humanBeing.id);
    }

    public static class Coordinates
    {
        private float x;
        private double y;

        public Coordinates(float x, double y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }
    public static class Car implements Comparable<Car>
    {
        private final String name;
        private final Boolean cool;

        public Car(String name, Boolean cool) {
            this.name = name;
            this.cool = cool;
        }

        public String getName() {
            return name;
        }

        public Boolean getCool() {
            return cool;
        }
        private int getPower()
        {
            int power = 0;
            if(cool)
                power+=100;
            power+=name.length();
            return power;
        }

        @Override
        public int compareTo(Car o) {
            return getPower()-o.getPower();
        }
    }
    public enum WeaponType{
        AXE(2),
        SHOTGUN(3),
        RIFLE(4),
        KNIFE(1);

        private final int power;

        public int getPower() {
            return power;
        }

        WeaponType(int power)
        {
            this.power = power;
        }
    }
    public enum Mood{
        SADNESS,
        SORROW,
        LONGING,
        GLOOM,
        APATHY;
    }


    //TODO make validator

    /**
     * Класс выполняющий чтение из файла отдельного объекта класса HumanBeing.
     * Является внутренним в целях избежания нарушения инкапсуляции.
     * @see core.file.FromXMLToObject
     */
    private class InnerXMLReader
    {
        public void readObject(XMLStreamReader reader) throws XMLStreamException {
            id = Long.valueOf(reader.getAttributeValue(0));
            name = reader.getAttributeValue(1);
            realHero = Boolean.valueOf(reader.getAttributeValue(2));
            hasToothpick = Boolean.valueOf(reader.getAttributeValue(3));
            impactSpeed = Float.parseFloat(reader.getAttributeValue(4));
            weaponType = WeaponType.valueOf(reader.getAttributeValue(5));
            mood = Mood.valueOf(reader.getAttributeValue(6));
            reader.next();
            creationTime = LocalDateTime.of(LocalDate.parse(reader.getAttributeValue(0)), LocalTime.parse(reader.getAttributeValue(1)));
            reader.next();
            reader.next();
            coordinates = new Coordinates(Float.parseFloat(reader.getAttributeValue(0)), Double.parseDouble(reader.getAttributeValue(1)));
            reader.next();
            reader.next();
            car = new Car(reader.getAttributeValue(0),Boolean.valueOf(reader.getAttributeValue(1)));
        }
    }

}
