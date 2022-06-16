package io.github.coffeecatrailway.plus.registry;

import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
public class PlusExtras
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final PollinatedRegistry<ResourceLocation> STATS = PollinatedRegistry.create(Registry.CUSTOM_STAT, Plus.MOD_ID);

    public static final DamageSource SAW_BLADE_DAMAGE_SOURCE = registerDamageSource("sawBlade", "%1$s was cut in half", DamageSource::bypassArmor);

    public static final ResourceLocation INTERACT_WITH_SAW_BENCH;

    static
    {
        INTERACT_WITH_SAW_BENCH = registerStat("interact_with_saw_bench", "Interactions with Saw Bench", StatFormatter.DEFAULT);
    }

    private static DamageSource registerDamageSource(String id, String deathMsg, Function<DamageSource, DamageSource> factory)
    {
        id = Plus.MOD_ID + "." + id;
        DamageSource source = factory.apply(new DamageSource(id));
        PlusLanguage.EXTRA.put("death.attack." + id, deathMsg);
        return source;
    }

    private static ResourceLocation registerStat(String name, String localizedName, StatFormatter formatter)
    {
        ResourceLocation stat = Plus.getLocation(name);
        STATS.register(name, () -> stat);
        Stats.CUSTOM.get(stat, formatter);
        PlusLanguage.EXTRA.put("stat." + Plus.MOD_ID + "." + name, localizedName);
        return stat;
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Extras loaded");
        STATS.register(platform);
    }
}
