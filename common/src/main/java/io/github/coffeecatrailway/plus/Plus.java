package io.github.coffeecatrailway.plus;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 19/12/2021
 */
public class Plus
{
    public static final String MOD_ID = "plus";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init()
    {

    }

    public static ResourceLocation getLocation(String path)
    {
        return new ResourceLocation(MOD_ID, path);
    }
}
