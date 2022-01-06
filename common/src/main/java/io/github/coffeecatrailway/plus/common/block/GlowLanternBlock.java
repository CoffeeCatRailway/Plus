package io.github.coffeecatrailway.plus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * @author CoffeeCatRailway
 * Created: 1/01/2022
 */
public class GlowLanternBlock extends LanternBlock
{
    protected static final VoxelShape PLACED_SHAPE = Shapes.or(Block.box(5f, 0f, 5f, 11f, 8f, 11f), Block.box(6f, 8f, 6f, 10f, 9f, 10f));
    protected static final VoxelShape HANGING_SHAPE = Shapes.or(Block.box(5f, 1f, 5f, 11f, 9f, 11f), Block.box(6f, 9f, 6f, 10f, 10f, 10f));

    public GlowLanternBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState arg, BlockGetter arg2, BlockPos arg3, CollisionContext arg4)
    {
        return arg.getValue(HANGING) ? HANGING_SHAPE : PLACED_SHAPE;
    }
}
