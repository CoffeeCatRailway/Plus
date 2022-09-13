package io.github.coffeecatrailway.plus;

import gg.moonflower.pollen.api.config.ConfigManager;
import gg.moonflower.pollen.api.config.PollinatedConfigType;
import gg.moonflower.pollen.api.event.events.entity.EntityEvents;
import gg.moonflower.pollen.api.event.events.lifecycle.TickEvents;
import gg.moonflower.pollen.api.event.events.registry.client.ParticleFactoryRegistryEvent;
import gg.moonflower.pollen.api.event.events.registry.client.RegisterAtlasSpriteEvent;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.client.*;
import gg.moonflower.pollen.api.registry.resource.ResourceRegistry;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import io.github.coffeecatrailway.plus.client.PlusModelLayers;
import io.github.coffeecatrailway.plus.client.entity.model.AmuletModel;
import io.github.coffeecatrailway.plus.client.entity.model.FoxHatModel;
import io.github.coffeecatrailway.plus.client.entity.renderer.PlayingCardEntityRenderer;
import io.github.coffeecatrailway.plus.client.gui.PlayingCardPackScreen;
import io.github.coffeecatrailway.plus.client.gui.SawBenchScreen;
import io.github.coffeecatrailway.plus.client.item.PlusShieldItemRenderer;
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

    public static final Material WOODEN_SHIELD_BASE = new Material(InventoryMenu.BLOCK_ATLAS, getLocation("entity/shield/wooden_pattern"));
    public static final Material WOODEN_SHIELD_NO_PATTERN = new Material(InventoryMenu.BLOCK_ATLAS, getLocation("entity/shield/wooden_nopattern"));
    public static final Material STONE_SHIELD_BASE = new Material(InventoryMenu.BLOCK_ATLAS, getLocation("entity/shield/stone_pattern"));
    public static final Material STONE_SHIELD_NO_PATTERN = new Material(InventoryMenu.BLOCK_ATLAS, getLocation("entity/shield/stone_nopattern"));
    public static final Material GOLD_SHIELD_BASE = new Material(InventoryMenu.BLOCK_ATLAS, getLocation("entity/shield/gold_pattern"));
    public static final Material GOLD_SHIELD_NO_PATTERN = new Material(InventoryMenu.BLOCK_ATLAS, getLocation("entity/shield/gold_nopattern"));
    public static final Material DIAMOND_SHIELD_BASE = new Material(InventoryMenu.BLOCK_ATLAS, getLocation("entity/shield/diamond_pattern"));
    public static final Material DIAMOND_SHIELD_NO_PATTERN = new Material(InventoryMenu.BLOCK_ATLAS, getLocation("entity/shield/diamond_nopattern"));
    public static final Material NETHERITE_SHIELD_BASE = new Material(InventoryMenu.BLOCK_ATLAS, getLocation("entity/shield/netherite_pattern"));
    public static final Material NETHERITE_SHIELD_NO_PATTERN = new Material(InventoryMenu.BLOCK_ATLAS, getLocation("entity/shield/netherite_nopattern"));
    public static final Material ROSE_GOLD_SHIELD_BASE = new Material(InventoryMenu.BLOCK_ATLAS, getLocation("entity/shield/rose_gold_pattern"));
    public static final Material ROSE_GOLD_SHIELD_NO_PATTERN = new Material(InventoryMenu.BLOCK_ATLAS, getLocation("entity/shield/rose_gold_nopattern"));

    public static final ResourceLocation EMPTY_SLOT_CARD = getLocation("item/empty_card_slot");
    public static final ResourceLocation EMPTY_SLOT_CARD_PACK = getLocation("item/empty_card_pack_slot");

    public static void onClientInit()
    {
        EntityRendererRegistry.registerLayerDefinition(PlusModelLayers.FOX_HAT, FoxHatModel::createBodyLayer);
        EntityRendererRegistry.registerLayerDefinition(PlusModelLayers.AMULET, AmuletModel::createBodyLayer);
        EntityRendererRegistry.registerLayerDefinition(PlusModelLayers.SHIELD, ShieldModel::createLayer);

        EntityRendererRegistry.register(PlusEntities.PLAYING_CARD, PlayingCardEntityRenderer::new);

        RegisterAtlasSpriteEvent.event(InventoryMenu.BLOCK_ATLAS).register((atlas, registry) -> {
            registry.accept(WOODEN_SHIELD_BASE.texture());
            registry.accept(WOODEN_SHIELD_NO_PATTERN.texture());
            registry.accept(STONE_SHIELD_BASE.texture());
            registry.accept(STONE_SHIELD_NO_PATTERN.texture());
            registry.accept(GOLD_SHIELD_BASE.texture());
            registry.accept(GOLD_SHIELD_NO_PATTERN.texture());
            registry.accept(DIAMOND_SHIELD_BASE.texture());
            registry.accept(DIAMOND_SHIELD_NO_PATTERN.texture());
            registry.accept(NETHERITE_SHIELD_BASE.texture());
            registry.accept(NETHERITE_SHIELD_NO_PATTERN.texture());
            registry.accept(ROSE_GOLD_SHIELD_BASE.texture());
            registry.accept(ROSE_GOLD_SHIELD_NO_PATTERN.texture());
        });

        ParticleFactoryRegistryEvent.EVENT.register(registry -> registry.register(PlusParticles.ITEM_PLAYING_CARD.get(), (particleOptions, clientLevel, d, e, f, g, h, i) -> new BreakingItemParticle(clientLevel, d, e, f, new ItemStack(PlusItems.CARD_JOKER_BLACK.get()))));

        PollinatedModContainer.get(MOD_ID).ifPresent(container -> {
            ResourceRegistry.registerBuiltinResourcePack(getLocation("shieldrevamp"), container, true);
        });

        RegisterAtlasSpriteEvent.event(InventoryMenu.BLOCK_ATLAS).register((atlas, registry) -> {
            registry.accept(EMPTY_SLOT_CARD);
            registry.accept(EMPTY_SLOT_CARD_PACK);
        });
    }

    public static void onClientPostInit(Platform.ModSetupContext ctx)
    {
        ctx.enqueueWork(() -> {
            ScreenRegistry.register(PlusMenus.SAW_BENCH.get(), SawBenchScreen::new);
            ScreenRegistry.register(PlusMenus.PLAYING_CARD_PACK.get(), PlayingCardPackScreen::new);
        });

        RenderTypeRegistry.register(PlusBlocks.SAW_BENCH.get(), RenderType.cutoutMipped());
        RenderTypeRegistry.register(PlusBlocks.GLOW_LANTERN.get(), RenderType.cutout());

        RenderTypeRegistry.register(PlusBlocks.CAMPFIRE_BIRCH.get(), RenderType.cutout());
        RenderTypeRegistry.register(PlusBlocks.CAMPFIRE_SPRUCE.get(), RenderType.cutout());
        RenderTypeRegistry.register(PlusBlocks.CAMPFIRE_JUNGLE.get(), RenderType.cutout());
        RenderTypeRegistry.register(PlusBlocks.CAMPFIRE_ACACIA.get(), RenderType.cutout());
        RenderTypeRegistry.register(PlusBlocks.CAMPFIRE_DARK_OAK.get(), RenderType.cutout());

        RenderTypeRegistry.register(PlusBlocks.SOUL_CAMPFIRE_BIRCH.get(), RenderType.cutout());
        RenderTypeRegistry.register(PlusBlocks.SOUL_CAMPFIRE_SPRUCE.get(), RenderType.cutout());
        RenderTypeRegistry.register(PlusBlocks.SOUL_CAMPFIRE_JUNGLE.get(), RenderType.cutout());
        RenderTypeRegistry.register(PlusBlocks.SOUL_CAMPFIRE_ACACIA.get(), RenderType.cutout());
        RenderTypeRegistry.register(PlusBlocks.SOUL_CAMPFIRE_DARK_OAK.get(), RenderType.cutout());

        RenderTypeRegistry.register(PlusBlocks.CAMPFIRE_CRIMSON.get(), RenderType.cutout());
        RenderTypeRegistry.register(PlusBlocks.CAMPFIRE_WARPED.get(), RenderType.cutout());

        RenderTypeRegistry.register(PlusBlocks.CAMPFIRE_MAPLE.get(), RenderType.cutout());
        RenderTypeRegistry.register(PlusBlocks.SOUL_CAMPFIRE_MAPLE.get(), RenderType.cutout());

        RenderTypeRegistry.register(PlusBlocks.CARD_TABLE.get(), RenderType.cutout());

        ItemPredicateRegistry.register(PlusItems.WOODEN_SHIELD.get(), new ResourceLocation("blocking"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1f : 0f);
        ItemPredicateRegistry.register(PlusItems.STONE_SHIELD.get(), new ResourceLocation("blocking"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1f : 0f);
        ItemPredicateRegistry.register(PlusItems.GOLD_SHIELD.get(), new ResourceLocation("blocking"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1f : 0f);
        ItemPredicateRegistry.register(PlusItems.DIAMOND_SHIELD.get(), new ResourceLocation("blocking"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1f : 0f);
        ItemPredicateRegistry.register(PlusItems.NETHERITE_SHIELD.get(), new ResourceLocation("blocking"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1f : 0f);
        ItemPredicateRegistry.register(PlusItems.ROSE_GOLD_SHIELD.get(), new ResourceLocation("blocking"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1f : 0f);

        ItemRendererRegistry.registerRenderer(PlusItems.WOODEN_SHIELD.get(), PlusShieldItemRenderer.INSTANCE);
        ItemRendererRegistry.registerRenderer(PlusItems.STONE_SHIELD.get(), PlusShieldItemRenderer.INSTANCE);
        ItemRendererRegistry.registerRenderer(PlusItems.GOLD_SHIELD.get(), PlusShieldItemRenderer.INSTANCE);
        ItemRendererRegistry.registerRenderer(PlusItems.DIAMOND_SHIELD.get(), PlusShieldItemRenderer.INSTANCE);
        ItemRendererRegistry.registerRenderer(PlusItems.NETHERITE_SHIELD.get(), PlusShieldItemRenderer.INSTANCE);
        ItemRendererRegistry.registerRenderer(PlusItems.ROSE_GOLD_SHIELD.get(), PlusShieldItemRenderer.INSTANCE);
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
