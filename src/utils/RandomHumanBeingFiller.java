package utils;

import core.HumanBeing;

/**
 * Класс для генерации случайных элементов коллекции
 */
public class RandomHumanBeingFiller {
    private static int name = 1;
    public static HumanBeing getRandomHuman()
    {
        String name = String.valueOf(RandomHumanBeingFiller.name);
        RandomHumanBeingFiller.name++;
        HumanBeing.Coordinates coordinates = new HumanBeing.Coordinates((float) Math.random()*1000,Math.random()*1000);
        Boolean realHero = Math.random() > 0.5;
        Boolean hasToothpick = Math.random() > 0.5;
        Float impactSpeed = (float)(Math.random()*5000 - 508);
        HumanBeing.WeaponType weaponType = HumanBeing.WeaponType.values()[(int)(Math.random()*4)];
        HumanBeing.Mood mood = HumanBeing.Mood.values()[(int)(Math.random()*5)];
        HumanBeing.Car car = new HumanBeing.Car(name, Math.random() > 0.5);
        return new HumanBeing(name,coordinates, realHero, hasToothpick, impactSpeed, weaponType, mood, car);
    }
}
