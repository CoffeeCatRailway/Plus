package io.github.coffeecatrailway.plus.util;

import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.world.damagesource.DamageSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 27/09/2021
 */
public class PlusDamageSource
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final DamageSource SAW_BLADE = register("sawBlade", "%1$s was cut in half", DamageSource::bypassArmor);

    private static DamageSource register(String id, String deathMsg, Function<DamageSource, DamageSource> factory)
    {
        id = PlusMod.MOD_ID + "." + id;
        DamageSource source = factory.apply(new DamageSource(id));
        PlusLanguage.EXTRA.put("death.attack." + id, deathMsg);
        return source;
    }

    public static void load()
    {
        LOGGER.debug("Loaded");
    }
}
