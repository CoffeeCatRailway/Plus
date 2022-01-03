package io.github.coffeecatrailway.plus.data.gen;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
public class PlusDataGenHelperCommon
{
    public static String capitalize(String id)
    {
        String[] names = id.split("_");
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String name : names)
        {
            builder.append(name.substring(0, 1).toUpperCase()).append(name.substring(1));
            i++;
            if (i != names.length)
                builder.append(" ");
        }
        return builder.toString();
    }
}
