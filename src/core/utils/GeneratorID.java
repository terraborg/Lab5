package core.utils;

/**
 * @author Volovich Alexey
 * Класс отвечающий за генерацию id
 * @see core.HumanBeing
 */
public class GeneratorID {
    private static long id = 1;
    public static Long next()
    {
        return id++;
    }
    public static void setId(Long id)
    {
        GeneratorID.id = id;
    }
}
