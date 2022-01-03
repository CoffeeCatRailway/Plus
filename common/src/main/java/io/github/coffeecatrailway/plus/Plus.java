package io.github.coffeecatrailway.plus;

import gg.moonflower.pollen.api.advancement.AdvancementModifier;
import gg.moonflower.pollen.api.platform.Platform;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.resources.ResourceLocation;

public class Plus
{
    public static final String MOD_ID = "plus";
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(Plus::onClientInit)
            .clientPostInit(Plus::onClientPostInit)
            .commonInit(Plus::onCommonInit)
            .commonPostInit(Plus::onCommonPostInit)
            .build();

    public static PlusConfig CONFIG_SERVER;

    public static void onClientInit()
    {
        AutoConfig.getGuiRegistry(PlusConfig.class);
    }

    public static void onClientPostInit(Platform.ModSetupContext ctx)
    {
    }

    public static void onCommonInit()
    {
        ConfigHolder<PlusConfig> holder = AutoConfig.register(PlusConfig.class, PartitioningSerializer.wrap(Toml4jConfigSerializer::new));
        CONFIG_SERVER = holder.getConfig();

        PlusItems.load(PLATFORM);
    }

    public static void onCommonPostInit(Platform.ModSetupContext ctx)
    {
    }

    public static ResourceLocation getLocation(String path)
    {
        return new ResourceLocation(MOD_ID, path);
    }
}
