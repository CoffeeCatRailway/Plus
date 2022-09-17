package io.github.coffeecatrailway.plus.registry;

import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
public class PlusStats
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final PollinatedRegistry<ResourceLocation> STATS = PollinatedRegistry.createVanilla(Registry.CUSTOM_STAT, Plus.MOD_ID);

    public static final ResourceLocation INTERACT_WITH_SAW_BENCH = register("interact_with_saw_bench", StatFormatter.DEFAULT);

    public static final ResourceLocation USE_PLAYING_CARD_PACK = register("use_playing_card_pack", StatFormatter.DEFAULT);
    public static final ResourceLocation INTERACT_WITH_CARD_TABLE = register("interact_with_card_table", StatFormatter.DEFAULT);

    private static ResourceLocation register(String id, StatFormatter formatter)
    {
        ResourceLocation stat = Plus.getLocation(id);
        STATS.register(id, () -> stat);
        Stats.CUSTOM.get(stat, formatter);
        return stat;
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        STATS.register(platform);
    }

    public static class Localize
    {
        public static void load()
        {
            register("interact_with_saw_bench", "Interactions with Saw Bench");
            register("use_playing_card_pack", "Uses of Playing Card Pack");
            register("interact_with_card_table", "Interactions with Card Table");
        }

        private static void register(String id, String name)
        {
            PlusLanguage.EXTRA.put("stat." + Plus.MOD_ID + "." + id, name);
        }
    }
}
