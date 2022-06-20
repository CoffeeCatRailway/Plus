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
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

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
//
//            partialState = this.getVariantBuilder(PlusBlocks.SAW_BENCH.get()).partialState();
//            model = this.models().withExistingParent("saw_bench", "stonecutter")
//                    .texture("particle", Plus.getLocation("block/saw_bench_bottom"))
//                    .texture("bottom", Plus.getLocation("block/saw_bench_bottom"))
//                    .texture("top", Plus.getLocation("block/saw_bench_top"))
//                    .texture("side", Plus.getLocation("block/saw_bench_side"));
//            for (Direction dir : Direction.Plane.HORIZONTAL)
//                partialState.with(SawBenchBlock.FACING, dir).modelForState().rotationY((int) dir.getOpposite().toYRot()).modelFile(model).addModel();
//            this.toItem(PlusBlocks.SAW_BENCH.get());
//
//            this.simpleBlock(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get());
//            this.toItem(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get());
//            this.simpleBlock(PlusBlocks.ROSE_GOLD_BLOCK.get());
//            this.toItem(PlusBlocks.ROSE_GOLD_BLOCK.get());
//
//            this.getVariantBuilder(PlusBlocks.GLOW_LANTERN.get())
//                    .partialState().with(GlowLanternBlock.HANGING, false).modelForState().modelFile(this.models().getExistingFile(Plus.getLocation("block/glow_lantern"))).addModel()
//                    .partialState().with(GlowLanternBlock.HANGING, true).modelForState().modelFile(this.models().getExistingFile(Plus.getLocation("block/hanging_glow_lantern"))).addModel();
        }

//        private void toItem(Block block)
//        {
//            this.toItem(block, Plus.getLocation("block/" + block.getRegistryName().getPath()));
//        }
//
//        private void toItem(Block block, ResourceLocation model)
//        {
//            String path = block.getRegistryName().getPath();
//            this.itemModels().withExistingParent(path, model);
//        }
    }
}
