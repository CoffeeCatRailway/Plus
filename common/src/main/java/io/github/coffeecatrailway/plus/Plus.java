package io.github.coffeecatrailway.plus;

import io.github.coffeecatrailway.plus.registry.PlusItems;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

    public static PlusConfig SERVER_CONFIG;

    public static void init()
    {
        ConfigHolder<PlusConfig> holder = AutoConfig.register(PlusConfig.class, PartitioningSerializer.wrap(Toml4jConfigSerializer::new));
        SERVER_CONFIG = holder.getConfig();

        PlusItems.init();
    }

    @Environment(EnvType.CLIENT)
    public static void initClient()
    {
        AutoConfig.getGuiRegistry(PlusConfig.class);
    }

    public static ResourceLocation getLocation(String path)
    {
        return new ResourceLocation(MOD_ID, path);
    }
}
