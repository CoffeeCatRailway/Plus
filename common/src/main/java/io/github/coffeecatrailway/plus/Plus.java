package io.github.coffeecatrailway.plus;

import gg.moonflower.pollen.api.event.events.lifecycle.TickEvent;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.client.RenderTypeRegistry;
import gg.moonflower.pollen.api.registry.client.ScreenRegistry;
import io.github.coffeecatrailway.plus.client.gui.SawBenchScreen;
import io.github.coffeecatrailway.plus.registry.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

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
        ctx.enqueueWork(() -> {
            ScreenRegistry.register(PlusMenuTypes.SAW_BENCH.get(), SawBenchScreen::new);
        });

        RenderTypeRegistry.register(PlusBlocks.SAW_BENCH.get(), RenderType.cutoutMipped());
        RenderTypeRegistry.register(PlusBlocks.GLOW_LANTERN.get(), RenderType.cutout());
    }

    public static void onCommonInit()
    {
        ConfigHolder<PlusConfig> holder = AutoConfig.register(PlusConfig.class, PartitioningSerializer.wrap(Toml4jConfigSerializer::new));
        CONFIG_SERVER = holder.getConfig();

        PlusBlocks.load(PLATFORM);
        PlusItems.load(PLATFORM);
        PlusEnchantments.load(PLATFORM);
        PlusMenuTypes.load(PLATFORM);
        PlusRecipes.load(PLATFORM);

        PlusExtras.load();
    }

    public static void onCommonPostInit(Platform.ModSetupContext ctx)
    {
        TickEvent.LIVING_POST.register(entity -> { // TODO: Test
            BlockState state = entity.level.getBlockState(entity.blockPosition());
            if (!state.is(Blocks.STONECUTTER))
                return;
            entity.hurt(PlusExtras.SAW_BLADE_DAMAGE_SOURCE, CONFIG_SERVER.blocks.sawBladeDamage * 1.5f);
        });
    }

    public static ResourceLocation getLocation(String path)
    {
        return new ResourceLocation(MOD_ID, path);
    }
}
