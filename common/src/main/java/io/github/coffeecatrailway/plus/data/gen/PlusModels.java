package io.github.coffeecatrailway.plus.data.gen;

import com.google.gson.JsonElement;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedBlockModelGenerator;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedItemModelGenerator;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedModelProvider;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.block.SawBenchBlock;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
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

            this.generateFlatItem(PlusBlocks.GLOW_LANTERN.get().asItem(), Plus.getLocation("item/glow_lantern"), ModelTemplates.FLAT_ITEM);
        }

        private void generateFlatItem(Item item, ResourceLocation texture, ModelTemplate modelTemplate)
        {
            modelTemplate.create(ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(texture), this.getModelOutput());
        }
    }

    private static class BlockModelGenerator extends PollinatedBlockModelGenerator
    {
        private static final ModelTemplate STONECUTTER = new ModelTemplate(Optional.of(new ResourceLocation("block/stonecutter")), Optional.empty(), TextureSlot.PARTICLE, TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE);

        public BlockModelGenerator(Consumer<BlockStateGenerator> blockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput, Consumer<Item> skippedAutoModelsOutput)
        {
            super(blockStateOutput, modelOutput, skippedAutoModelsOutput);
        }

        @Override
        public void run()
        {
//            int i;
//            VariantBlockStateBuilder.PartialBlockstate partialState = this.getVariantBuilder(PlusBlocks.BRITTLE_BASALT.get()).partialState();
//            ModelFile model;
//            for (i = 0; i < 4; i++)
//            {
//                model = this.models().cubeColumn("brittle_basalt_" + i, Plus.getLocation("block/brittle_basalt_side_" + i), Plus.getLocation("block/brittle_basalt_top_" + i));
//                for (Direction.Axis axis : Direction.Axis.values())
//                    partialState.with(BrittleBasaltBlock.AGE, i).with(BrittleBasaltBlock.AXIS, axis).modelForState().rotationX(axis == Direction.Axis.X || axis == Direction.Axis.Z ? 90 : 0).rotationY(axis == Direction.Axis.X ? 90 : 0).modelFile(model).addModel();
//            }

            ResourceLocation cutterModelLocation = STONECUTTER.create(PlusBlocks.SAW_BENCH.get(),
                    new TextureMapping().put(TextureSlot.PARTICLE, Plus.getLocation("block/saw_bench_bottom"))
                            .put(TextureSlot.BOTTOM, Plus.getLocation("block/saw_bench_bottom"))
                            .put(TextureSlot.TOP, Plus.getLocation("block/saw_bench_top"))
                            .put(TextureSlot.SIDE, Plus.getLocation("block/saw_bench_side")),
                    this.getModelOutput());
            PropertyDispatch.C1<Direction> propertyDispatch = PropertyDispatch.property(SawBenchBlock.FACING);
            for (Direction direction : Direction.Plane.HORIZONTAL)
                propertyDispatch = propertyDispatch.select(direction, Variant.variant().with(VariantProperties.MODEL, cutterModelLocation).with(VariantProperties.Y_ROT, this.yRotationFromDirection(direction)));

            this.getBlockStateOutput().accept(MultiVariantGenerator.multiVariant(PlusBlocks.SAW_BENCH.get()).with(propertyDispatch));
            this.delegateItemModel(PlusBlocks.SAW_BENCH.get(), cutterModelLocation);

//            this.simpleBlock(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get());
//            this.toItem(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get());
//            this.simpleBlock(PlusBlocks.ROSE_GOLD_BLOCK.get());
//            this.toItem(PlusBlocks.ROSE_GOLD_BLOCK.get());

//            this.getVariantBuilder(PlusBlocks.GLOW_LANTERN.get())
//                    .partialState().with(GlowLanternBlock.HANGING, false).modelForState().modelFile(this.models().getExistingFile(Plus.getLocation("block/glow_lantern"))).addModel()
//                    .partialState().with(GlowLanternBlock.HANGING, true).modelForState().modelFile(this.models().getExistingFile(Plus.getLocation("block/hanging_glow_lantern"))).addModel();
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
