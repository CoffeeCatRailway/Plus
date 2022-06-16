package io.github.coffeecatrailway.plus.registry;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.world.damagesource.DamageSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
public class PlusDamageSources
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final DamageSource SAW_BLADE_DAMAGE_SOURCE = register("sawBlade", "%1$s was cut in half", DamageSource::bypassArmor);

    private static DamageSource register(String id, String message, Function<DamageSource, DamageSource> factory)
    {
        id = Plus.MOD_ID + "." + id;
        DamageSource source = factory.apply(new DamageSource(id));
        PlusLanguage.EXTRA.put("death.attack." + id, message);
        return source;
    }

    public static void load()
    {
        LOGGER.debug("Loaded");
    }
}
