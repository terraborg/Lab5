package core.utils;

/**
 * @author Volovich Alexey
 * @deprecated Класс отвечающий за генерацию id
 * @see core.HumanBeing
 */
public class generatorID {
    private static long id = 1;
    public static Long next()
    {
        return id++;
    }
    public static void setId(Long id)
    {
        generatorID.id = id;
    }
}
