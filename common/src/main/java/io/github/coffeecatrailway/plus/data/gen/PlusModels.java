package io.github.coffeecatrailway.plus.data.gen;

import com.google.gson.JsonElement;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedBlockModelGenerator;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedItemModelGenerator;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedModelProvider;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.block.BrittleBasaltBlock;
import io.github.coffeecatrailway.plus.common.block.GlowLanternBlock;
import io.github.coffeecatrailway.plus.common.block.SawBenchBlock;
import io.github.coffeecatrailway.plus.common.item.PlayingCardItem;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 17/06/2022
 */
public class PlusModels extends PollinatedModelProvider
{
    public PlusModels(DataGenerator generator, PollinatedModContainer container)
    {
        super(generator, container);
        this.addGenerator((blockStateOutput, modelOutput, skippedAutoModelsOutput) -> new ItemModelGenerator(modelOutput));
        this.addGenerator(BlockModelGenerator::new);
    }

    private static class ItemModelGenerator extends PollinatedItemModelGenerator
    {
        private static final TextureSlot LAYER1 = TextureSlot.create("layer1");
        private static final TextureSlot LAYER2 = TextureSlot.create("layer2");

        public ItemModelGenerator(BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput)
        {
            super(modelOutput);
        }

        @Override
        public void run()
        {
            this.generateFlatItem(PlusItems.FOX_FUR.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.SNOW_FOX_FUR.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.FOX_MEAT.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.COOKED_FOX_MEAT.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.CHAIN_LINK.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.BAT.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.COOKED_BAT.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.SQUID.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.CALAMARI.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.TURTLE.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.COOKED_TURTLE.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.STINGER.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.WARMTH_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.FOX_HAT.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.SNOW_FOX_HAT.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.WARMTH_AMULET.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.RAW_ROSE_GOLD.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_INGOT.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_NUGGET.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

            ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(PlusItems.CARD_JOKER_BLACK.get()), TextureMapping.layer0(Plus.getLocation("item/playing_cards/blank")).putForced(LAYER1, Plus.getLocation("item/playing_cards/joker_black")), this.getModelOutput());
            ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(PlusItems.CARD_JOKER_RED.get()), TextureMapping.layer0(Plus.getLocation("item/playing_cards/blank")).putForced(LAYER1, Plus.getLocation("item/playing_cards/joker_red")), this.getModelOutput());
            PlusItems.PLAYING_CARDS.stream().map(Supplier::get).forEach(card -> {
                ResourceLocation suitLocation = Plus.getLocation("item/playing_cards/suits/" + card.getSuit().getName() + "/" + card.getNumber().getName());
                if (card.getNumber().equals(PlayingCardItem.Number.JACK) || card.getNumber().equals(PlayingCardItem.Number.QUEEN) || card.getNumber().equals(PlayingCardItem.Number.KING))
                    suitLocation = Plus.getLocation("item/playing_cards/suits/" + card.getSuit().getName() + "/" + PlayingCardItem.Number.ACE.getName());
                ResourceLocation numberLocation = Plus.getLocation("item/playing_cards/" + card.getNumber().getName() + (card.isRed() ? "_red" : "_black"));
                ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(card), TextureMapping.layer0(Plus.getLocation("item/playing_cards/blank")).putForced(LAYER1, suitLocation).putForced(LAYER2, numberLocation), this.getModelOutput());
            });
            this.generateFlatItem(PlusItems.PLAYING_CARD_PACK.get(), ModelTemplates.FLAT_ITEM);
        }
    }

    private static class BlockModelGenerator extends PollinatedBlockModelGenerator
    {
        private static final TextureSlot LOG_TEXTURE_SLOT = TextureSlot.create("log");

        private static final ModelTemplate STONECUTTER = new ModelTemplate(Optional.of(new ResourceLocation("block/stonecutter")), Optional.empty(), TextureSlot.PARTICLE, TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE);
        private static final ModelTemplate CAMPFIRE_OFF = new ModelTemplate(Optional.of(new ResourceLocation("block/campfire_off")), Optional.empty(), TextureSlot.PARTICLE, LOG_TEXTURE_SLOT);

        public BlockModelGenerator(Consumer<BlockStateGenerator> blockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput, Consumer<Item> skippedAutoModelsOutput)
        {
            super(blockStateOutput, modelOutput, skippedAutoModelsOutput);
        }

        @Override
        public void run()
        {
            PropertyDispatch.C2<Integer, Direction.Axis> dispatchBasalt = PropertyDispatch.properties(BrittleBasaltBlock.AGE, BrittleBasaltBlock.AXIS);
            Function<Integer, ResourceLocation> basaltModelLocation = stage -> TexturedModel.COLUMN.updateTexture(mapping -> mapping.put(TextureSlot.SIDE, Plus.getLocation("block/brittle_basalt_side_" + stage)).put(TextureSlot.END, Plus.getLocation("block/brittle_basalt_top_" + stage))).createWithSuffix(PlusBlocks.BRITTLE_BASALT.get(), "_" + stage, this.getModelOutput());
            for (int i = 0; i < 4; i++)
            {
                ResourceLocation model = basaltModelLocation.apply(i);
                for (Direction.Axis axis : Direction.Axis.values())
                {
                    dispatchBasalt = dispatchBasalt.select(i, axis, Variant.variant().with(VariantProperties.MODEL, model)
                            .with(VariantProperties.X_ROT, axis == Direction.Axis.X || axis == Direction.Axis.Z ? VariantProperties.Rotation.R90 : VariantProperties.Rotation.R0)
                            .with(VariantProperties.Y_ROT, axis == Direction.Axis.X ? VariantProperties.Rotation.R90 : VariantProperties.Rotation.R0));
                }
            }
            this.getBlockStateOutput().accept(MultiVariantGenerator.multiVariant(PlusBlocks.BRITTLE_BASALT.get()).with(dispatchBasalt));
            this.delegateItemModel(PlusBlocks.BRITTLE_BASALT.get(), Plus.getLocation("block/brittle_basalt_0"));

            ResourceLocation cutterModelLocation = STONECUTTER.create(PlusBlocks.SAW_BENCH.get(),
                    new TextureMapping().put(TextureSlot.PARTICLE, Plus.getLocation("block/saw_bench_bottom"))
                            .put(TextureSlot.BOTTOM, Plus.getLocation("block/saw_bench_bottom"))
                            .put(TextureSlot.TOP, Plus.getLocation("block/saw_bench_top"))
                            .put(TextureSlot.SIDE, Plus.getLocation("block/saw_bench_side")),
                    this.getModelOutput());
            PropertyDispatch.C1<Direction> dispatchSawBench = PropertyDispatch.property(SawBenchBlock.FACING);
            for (Direction direction : Direction.Plane.HORIZONTAL)
                dispatchSawBench = dispatchSawBench.select(direction, Variant.variant().with(VariantProperties.MODEL, cutterModelLocation).with(VariantProperties.Y_ROT, this.yRotationFromDirection(direction)));

            this.getBlockStateOutput().accept(MultiVariantGenerator.multiVariant(PlusBlocks.SAW_BENCH.get()).with(dispatchSawBench));
            this.delegateItemModel(PlusBlocks.SAW_BENCH.get(), cutterModelLocation);

            this.createTrivialCube(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get());
            this.createTrivialCube(PlusBlocks.ROSE_GOLD_BLOCK.get());

            PropertyDispatch.C2<Boolean, Boolean> dispatchLantern = PropertyDispatch.properties(GlowLanternBlock.HANGING, GlowLanternBlock.WATERLOGGED)
                    .select(false, false, Variant.variant().with(VariantProperties.MODEL, Plus.getLocation("block/glow_lantern")))
                    .select(false, true, Variant.variant().with(VariantProperties.MODEL, Plus.getLocation("block/glow_lantern")))
                    .select(true, false, Variant.variant().with(VariantProperties.MODEL, Plus.getLocation("block/hanging_glow_lantern")))
                    .select(true, true, Variant.variant().with(VariantProperties.MODEL, Plus.getLocation("block/hanging_glow_lantern")));
            this.getBlockStateOutput().accept(MultiVariantGenerator.multiVariant(PlusBlocks.GLOW_LANTERN.get()).with(dispatchLantern));
            this.createSimpleFlatItemModel(PlusBlocks.GLOW_LANTERN.get().asItem());

            this.campfire(PlusBlocks.CAMPFIRE_BIRCH.get(), false, false);
            this.campfire(PlusBlocks.CAMPFIRE_SPRUCE.get(), false, false);
            this.campfire(PlusBlocks.CAMPFIRE_JUNGLE.get(), false, false);
            this.campfire(PlusBlocks.CAMPFIRE_ACACIA.get(), false, false);
            this.campfire(PlusBlocks.CAMPFIRE_DARK_OAK.get(), false, false);

            this.campfire(PlusBlocks.SOUL_CAMPFIRE_BIRCH.get(), true, false);
            this.campfire(PlusBlocks.SOUL_CAMPFIRE_SPRUCE.get(), true, false);
            this.campfire(PlusBlocks.SOUL_CAMPFIRE_JUNGLE.get(), true, false);
            this.campfire(PlusBlocks.SOUL_CAMPFIRE_ACACIA.get(), true, false);
            this.campfire(PlusBlocks.SOUL_CAMPFIRE_DARK_OAK.get(), true, false);

            this.campfire(PlusBlocks.CAMPFIRE_CRIMSON.get(), false, true);
            this.campfire(PlusBlocks.CAMPFIRE_WARPED.get(), false, true);

            this.campfire(PlusBlocks.CAMPFIRE_MAPLE.get(), false, false);
            this.campfire(PlusBlocks.SOUL_CAMPFIRE_MAPLE.get(), true, false);
        }

        private void campfire(CampfireBlock campfireBlock, boolean isSoulFire, boolean hasSoulFire)
        {
            ResourceLocation campfireLogTexture = TextureMapping.getBlockTexture(campfireBlock, "_log");
            if (isSoulFire)
                campfireLogTexture = new ResourceLocation(campfireLogTexture.getNamespace(), campfireLogTexture.getPath().replace("soul_", ""));
            ResourceLocation offLocation = CAMPFIRE_OFF.createWithSuffix(campfireBlock, "_off", new TextureMapping().put(TextureSlot.PARTICLE, campfireLogTexture).put(LOG_TEXTURE_SLOT, campfireLogTexture), this.getModelOutput());
            ResourceLocation onLocation = ModelTemplates.CAMPFIRE.create(campfireBlock, new TextureMapping().put(TextureSlot.LIT_LOG, TextureMapping.getBlockTexture(campfireBlock, "_log_lit")).put(TextureSlot.FIRE, new ResourceLocation(isSoulFire || hasSoulFire ? "block/soul_campfire_fire" : "block/campfire_fire")).putForced(TextureSlot.PARTICLE, campfireLogTexture).putForced(LOG_TEXTURE_SLOT, campfireLogTexture), this.getModelOutput());

            this.createSimpleFlatItemModel(campfireBlock.asItem());
            this.getBlockStateOutput().accept(MultiVariantGenerator.multiVariant(campfireBlock).with(createBooleanModelDispatch(BlockStateProperties.LIT, onLocation, offLocation)).with(createHorizontalFacingDispatchAlt()));
        }

        private VariantProperties.Rotation yRotationFromDirection(Direction direction)
        {
            return switch (direction)
                    {
                        default -> VariantProperties.Rotation.R0;
                        case SOUTH -> VariantProperties.Rotation.R180;
                        case WEST -> VariantProperties.Rotation.R270;
                        case EAST -> VariantProperties.Rotation.R90;
                    };
        }
    }
}
