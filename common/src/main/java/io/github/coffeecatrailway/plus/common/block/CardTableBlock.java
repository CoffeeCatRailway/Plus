package io.github.coffeecatrailway.plus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * @author CoffeeCatRailway
 * Created: 28/08/2022
 */
public class CardTableBlock extends BaseEntityBlock
{
    private static final VoxelShape SHAPE = Shapes.or(Block.box(1f, 0f, 1f, 15f, 2f, 15f), Block.box(2f, 2f, 2f, 14f, 3f, 14f), Block.box(3f, 3f, 3f, 13f, 7f, 13f), Block.box(2f, 7f, 2f, 14f, 8f, 14f), Block.box(0f, 8f, 0f, 16f, 11f, 16f));

    public CardTableBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context)
    {
        return SHAPE;
    }

}
