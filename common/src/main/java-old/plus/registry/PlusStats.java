package io.github.coffeecatrailway.plus.registry;

import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 27/09/2021
 */
public class PlusStats
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation INTERACT_WITH_SAW_BENCH;

    public static void register(RegistryEvent.Register<StatType<?>> event)
    {
        if (!event.getName().equals(ForgeRegistries.STAT_TYPES.getRegistryName()))
            return;

        INTERACT_WITH_SAW_BENCH = register("interact_with_saw_bench", "Interactions with Saw Bench", StatFormatter.DEFAULT);

        LOGGER.debug("Loaded");
    }

    private static ResourceLocation register(String idPath, String localizedName, StatFormatter formatter)
    {
        ResourceLocation id = PlusMod.getLocation(idPath);
        Registry.register(Registry.CUSTOM_STAT, idPath, id);
        Stats.CUSTOM.get(id, formatter);
        PlusLanguage.EXTRA.put("stat." + PlusMod.MOD_ID + "." + idPath, localizedName);
        return id;
    }
}
