package io.github.coffeecatrailway.plus.data.gen.forge;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.block.BrittleBasaltBlock;
import io.github.coffeecatrailway.plus.common.block.GlowLanternBlock;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author CoffeeCatRailway
 * Created: 7/04/2021
 */
public class PlusBlockStates extends BlockStateProvider
{
    public PlusBlockStates(DataGenerator gen, ExistingFileHelper existingFileHelper)
    {
        super(gen, Plus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        int i;
        VariantBlockStateBuilder.PartialBlockstate partialState = this.getVariantBuilder(PlusBlocks.BRITTLE_BASALT.get()).partialState();
        ModelFile model;
        for (i = 0; i < 4; i++)
        {
            model = this.models().cubeColumn("brittle_basalt_" + i, Plus.getLocation("block/brittle_basalt_side_" + i), Plus.getLocation("block/brittle_basalt_top_" + i));
            for (Direction.Axis axis : Direction.Axis.values())
                partialState.with(BrittleBasaltBlock.AGE, i).with(BrittleBasaltBlock.AXIS, axis).modelForState().rotationX(axis == Direction.Axis.X || axis == Direction.Axis.Z ? 90 : 0).rotationY(axis == Direction.Axis.X ? 90 : 0).modelFile(model).addModel();
        }

//        partialState = this.getVariantBuilder(PlusBlocks.SAW_BENCH.get()).partialState();
//        model = this.models().withExistingParent("saw_bench", "stonecutter")
//                .texture("particle", Plus.getLocation("block/saw_bench_bottom"))
//                .texture("bottom", Plus.getLocation("block/saw_bench_bottom"))
//                .texture("top", Plus.getLocation("block/saw_bench_top"))
//                .texture("side", Plus.getLocation("block/saw_bench_side"));
//        for (Direction dir : Direction.Plane.HORIZONTAL)
//            partialState.with(SawBenchBlock.FACING, dir).modelForState().rotationY((int) dir.getOpposite().toYRot()).modelFile(model).addModel();
//        this.toItem(PlusBlocks.SAW_BENCH.get());

        this.simpleBlock(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get());
        this.toItem(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get());
        this.simpleBlock(PlusBlocks.ROSE_GOLD_BLOCK.get());
        this.toItem(PlusBlocks.ROSE_GOLD_BLOCK.get());

        this.getVariantBuilder(PlusBlocks.GLOW_LANTERN.get())
                .partialState().with(GlowLanternBlock.HANGING, false).modelForState().modelFile(this.models().getExistingFile(Plus.getLocation("block/glow_lantern"))).addModel()
                .partialState().with(GlowLanternBlock.HANGING, true).modelForState().modelFile(this.models().getExistingFile(Plus.getLocation("block/hanging_glow_lantern"))).addModel();
    }

    private void toItem(Block block)
    {
        this.toItem(block, Plus.getLocation("block/" + block.getRegistryName().getPath()));
    }

    private void toItem(Block block, ResourceLocation model)
    {
        String path = block.getRegistryName().getPath();
        this.itemModels().withExistingParent(path, model);
    }
}
