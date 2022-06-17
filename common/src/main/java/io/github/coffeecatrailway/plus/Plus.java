package io.github.coffeecatrailway.plus;

import gg.moonflower.pollen.api.config.ConfigManager;
import gg.moonflower.pollen.api.config.PollinatedConfigType;
import gg.moonflower.pollen.api.event.events.lifecycle.TickEvent;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.client.RenderTypeRegistry;
import gg.moonflower.pollen.api.registry.client.ScreenRegistry;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import io.github.coffeecatrailway.plus.client.gui.SawBenchScreen;
import io.github.coffeecatrailway.plus.data.gen.PlusBlockTags;
import io.github.coffeecatrailway.plus.data.gen.PlusItemModels;
import io.github.coffeecatrailway.plus.data.gen.PlusItemTags;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import io.github.coffeecatrailway.plus.registry.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class Plus
{
    public static final String MOD_ID = "plus";
    public static PlusConfig.Server CONFIG_SERVER = ConfigManager.register(MOD_ID, PollinatedConfigType.SERVER, PlusConfig.Server::new);
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(() -> Plus::onClientInit)
            .clientPostInit(() -> Plus::onClientPostInit)
            .commonInit(Plus::onCommonInit)
            .commonPostInit(Plus::onCommonPostInit)
            .dataInit(Plus::onDataInit)
            .build();

    public static void onClientInit()
    {
    }

    public static void onClientPostInit(Platform.ModSetupContext ctx)
    {
        ctx.enqueueWork(() -> {
            ScreenRegistry.register(PlusMenus.SAW_BENCH.get(), SawBenchScreen::new);
        });

        RenderTypeRegistry.register(PlusBlocks.SAW_BENCH.get(), RenderType.cutoutMipped());
        RenderTypeRegistry.register(PlusBlocks.GLOW_LANTERN.get(), RenderType.cutout());
    }

    public static void onCommonInit()
    {
        PlusItems.load(PLATFORM);
        PlusBlocks.load(PLATFORM);
        PlusRecipes.load(PLATFORM);
        PlusStats.Localize.load();
        PlusDamageSources.load();
        PlusEnchantments.load(PLATFORM);
        PlusMenus.load(PLATFORM);
    }

    public static void onCommonPostInit(Platform.ModSetupContext ctx)
    {
        ctx.enqueueWork(() -> PlusStats.load(PLATFORM));

        TickEvent.LIVING_POST.register(entity -> {
            BlockState state = entity.level.getBlockState(entity.blockPosition());
            if (!state.is(Blocks.STONECUTTER))
                return;
            entity.hurt(PlusDamageSources.SAW_BLADE_DAMAGE_SOURCE, CONFIG_SERVER.sawBladeDamage.get().floatValue());
        });
    }

    public static void onDataInit(Platform.DataSetupContext ctx)
    {
        DataGenerator generator = ctx.getGenerator(); // TODO: Convert to fabric
        PollinatedModContainer container = ctx.getMod();
        PlusBlockTags blockTags = new PlusBlockTags(generator, container);
        generator.addProvider(blockTags);
        generator.addProvider(new PlusItemTags(generator, container, blockTags));
        generator.addProvider(new PlusItemModels(generator, container));
        generator.addProvider(new PlusLanguage(generator, container));
    }

    public static ResourceLocation getLocation(String path)
    {
        return new ResourceLocation(MOD_ID, path);
    }
}
