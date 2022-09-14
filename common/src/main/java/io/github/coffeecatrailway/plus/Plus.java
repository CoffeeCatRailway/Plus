package io.github.coffeecatrailway.plus;

import gg.moonflower.pollen.api.config.ConfigManager;
import gg.moonflower.pollen.api.config.PollinatedConfigType;
import gg.moonflower.pollen.api.event.events.entity.EntityEvents;
import gg.moonflower.pollen.api.event.events.lifecycle.TickEvents;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import io.github.coffeecatrailway.plus.client.PlusClient;
import io.github.coffeecatrailway.plus.common.entity.ai.goal.FindGlowLanternGoal;
import io.github.coffeecatrailway.plus.data.gen.*;
import io.github.coffeecatrailway.plus.data.gen.loot.PlusLootModifierProvider;
import io.github.coffeecatrailway.plus.data.gen.loot.PlusLootTableProvider;
import io.github.coffeecatrailway.plus.mixins.MobAccessor;
import io.github.coffeecatrailway.plus.registry.*;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.particle.BreakingItemParticle;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.Material;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class Plus
{
    public static final String MOD_ID = "plus";
    public static PlusConfig.Server CONFIG_SERVER = ConfigManager.register(MOD_ID, PollinatedConfigType.SERVER, PlusConfig.Server::new);
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(() -> PlusClient::onClientInit)
            .clientPostInit(() -> PlusClient::onClientPostInit)
            .commonInit(Plus::onCommonInit)
            .commonPostInit(Plus::onCommonPostInit)
            .dataInit(Plus::onDataInit)
            .build();

    public static void onCommonInit()
    {
        PlusItems.load(PLATFORM);
        PlusBlocks.load(PLATFORM);
        PlusRecipes.load(PLATFORM);
        PlusStats.Localize.load();
        PlusDamageSources.load();
        PlusEnchantments.load(PLATFORM);
        PlusMenus.load(PLATFORM);
        PlusEntities.load(PLATFORM);
        PlusParticles.load(PLATFORM);

        EntityEvents.JOIN.register(((entity, level) -> {
            if (entity instanceof GlowSquid)
                ((MobAccessor) entity).getTargetSelector().addGoal(0, new FindGlowLanternGoal((GlowSquid) entity));
            return true;
        }));

        TickEvents.LIVING_POST.register(entity -> {
            BlockState state = entity.level.getBlockState(entity.blockPosition());
            if (state.is(Blocks.STONECUTTER))
                entity.hurt(PlusDamageSources.SAW_BLADE_DAMAGE_SOURCE, CONFIG_SERVER.sawBladeDamage.get().floatValue());
        });
    }

    public static void onCommonPostInit(Platform.ModSetupContext ctx)
    {
        ctx.enqueueWork(() -> PlusStats.load(PLATFORM));
    }

    public static void onDataInit(Platform.DataSetupContext ctx)
    {
        DataGenerator generator = ctx.getGenerator();
        PollinatedModContainer container = ctx.getMod();
        PlusBlockTags blockTags = new PlusBlockTags(generator, container);
        generator.addProvider(blockTags);
        generator.addProvider(new PlusItemTags(generator, container, blockTags));
        generator.addProvider(new PlusModels(generator, container));
        generator.addProvider(new PlusLanguage(generator, container));
        generator.addProvider(new PlusRecipeProvider(generator));
        generator.addProvider(new PlusLootTableProvider(generator));
        generator.addProvider(new PlusLootModifierProvider(generator));
    }

    public static ResourceLocation getLocation(String path)
    {
        return new ResourceLocation(MOD_ID, path);
    }
}
