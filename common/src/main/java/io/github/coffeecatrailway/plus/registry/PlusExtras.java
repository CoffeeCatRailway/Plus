package io.github.coffeecatrailway.plus.registry;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.data.gen.PlusDataGenHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
public class PlusExtras
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final DamageSource SAW_BLADE_DAMAGE_SOURCE = registerDamageSource("sawBlade", "%1$s was cut in half", DamageSource::bypassArmor);

    private static DamageSource registerDamageSource(String id, String deathMsg, Function<DamageSource, DamageSource> factory)
    {
        id = Plus.MOD_ID + "." + id;
        DamageSource source = factory.apply(new DamageSource(id));
        PlusDataGenHelper.languageExtra("death.attack." + id, deathMsg);
        return source;
    }

    public static void load()
    {
        LOGGER.debug("Loaded");
    }
}
