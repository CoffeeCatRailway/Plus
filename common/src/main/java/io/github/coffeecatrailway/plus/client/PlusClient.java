package io.github.coffeecatrailway.plus.client;

import gg.moonflower.pollen.api.event.events.registry.client.ParticleFactoryRegistryEvent;
import gg.moonflower.pollen.api.event.events.registry.client.RegisterAtlasSpriteEvent;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.client.*;
import gg.moonflower.pollen.api.registry.resource.ResourceRegistry;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.client.entity.model.AmuletModel;
import io.github.coffeecatrailway.plus.client.entity.model.FoxHatModel;
import io.github.coffeecatrailway.plus.client.entity.renderer.PlayingCardEntityRenderer;
import io.github.coffeecatrailway.plus.client.gui.CardTableScreen;
import io.github.coffeecatrailway.plus.client.gui.PlayingCardPackScreen;
import io.github.coffeecatrailway.plus.client.gui.SawBenchScreen;
import io.github.coffeecatrailway.plus.client.item.PlusShieldItemRenderer;
import io.github.coffeecatrailway.plus.client.particle.PlayingCardParticleProvider;
import io.github.coffeecatrailway.plus.registry.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

/**
 * @author CoffeeCatRailway
 * Created: 26/06/2022
 */
@Environment(EnvType.CLIENT)
public class PlusClient
{
    public static final ModelLayerLocation FOX_HAT = new ModelLayerLocation(Plus.getLocation("fox_hat"), "main");
    public static final ModelLayerLocation AMULET = new ModelLayerLocation(Plus.getLocation("amulet"), "main");
    public static final ModelLayerLocation SHIELD = new ModelLayerLocation(Plus.getLocation("shield"), "main");

    public static final Material WOODEN_SHIELD_BASE = new Material(InventoryMenu.BLOCK_ATLAS, Plus.getLocation("entity/shield/wooden_pattern"));
    public static final Material WOODEN_SHIELD_NO_PATTERN = new Material(InventoryMenu.BLOCK_ATLAS, Plus.getLocation("entity/shield/wooden_nopattern"));
    public static final Material STONE_SHIELD_BASE = new Material(InventoryMenu.BLOCK_ATLAS, Plus.getLocation("entity/shield/stone_pattern"));
    public static final Material STONE_SHIELD_NO_PATTERN = new Material(InventoryMenu.BLOCK_ATLAS, Plus.getLocation("entity/shield/stone_nopattern"));
    public static final Material GOLD_SHIELD_BASE = new Material(InventoryMenu.BLOCK_ATLAS, Plus.getLocation("entity/shield/gold_pattern"));
    public static final Material GOLD_SHIELD_NO_PATTERN = new Material(InventoryMenu.BLOCK_ATLAS, Plus.getLocation("entity/shield/gold_nopattern"));
    public static final Material DIAMOND_SHIELD_BASE = new Material(InventoryMenu.BLOCK_ATLAS, Plus.getLocation("entity/shield/diamond_pattern"));
    public static final Material DIAMOND_SHIELD_NO_PATTERN = new Material(InventoryMenu.BLOCK_ATLAS, Plus.getLocation("entity/shield/diamond_nopattern"));
    public static final Material NETHERITE_SHIELD_BASE = new Material(InventoryMenu.BLOCK_ATLAS, Plus.getLocation("entity/shield/netherite_pattern"));
    public static final Material NETHERITE_SHIELD_NO_PATTERN = new Material(InventoryMenu.BLOCK_ATLAS, Plus.getLocation("entity/shield/netherite_nopattern"));
    public static final Material ROSE_GOLD_SHIELD_BASE = new Material(InventoryMenu.BLOCK_ATLAS, Plus.getLocation("entity/shield/rose_gold_pattern"));
    public static final Material ROSE_GOLD_SHIELD_NO_PATTERN = new Material(InventoryMenu.BLOCK_ATLAS, Plus.getLocation("entity/shield/rose_gold_nopattern"));

    public static final ResourceLocation EMPTY_SLOT_CARD = Plus.getLocation("item/empty_card_slot");
    public static final ResourceLocation EMPTY_SLOT_CARD_PACK = Plus.getLocation("item/empty_card_pack_slot");

    public static void onClientInit()
    {
        EntityRendererRegistry.registerLayerDefinition(PlusClient.FOX_HAT, FoxHatModel::createBodyLayer);
        EntityRendererRegistry.registerLayerDefinition(PlusClient.AMULET, AmuletModel::createBodyLayer);
        EntityRendererRegistry.registerLayerDefinition(PlusClient.SHIELD, ShieldModel::createLayer);

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

            registry.accept(EMPTY_SLOT_CARD);
            registry.accept(EMPTY_SLOT_CARD_PACK);
        });

        ParticleFactoryRegistryEvent.EVENT.register(registry -> registry.register(PlusParticles.ITEM_PLAYING_CARD.get(), (particleOptions, clientLevel, d, e, f, g, h, i) -> new PlayingCardParticleProvider().createParticle(particleOptions, clientLevel, d, e, f, g, h, i)));

        PollinatedModContainer.get(Plus.MOD_ID).ifPresent(container -> {
            ResourceRegistry.registerBuiltinResourcePack(Plus.getLocation("shieldrevamp"), container, true);
        });
    }

    public static void onClientPostInit(Platform.ModSetupContext ctx)
    {
        ctx.enqueueWork(() -> {
            ScreenRegistry.register(PlusMenus.SAW_BENCH.get(), SawBenchScreen::new);
            ScreenRegistry.register(PlusMenus.PLAYING_CARD_PACK.get(), PlayingCardPackScreen::new);
            ScreenRegistry.register(PlusMenus.CARD_TABLE.get(), CardTableScreen::new);
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
}
