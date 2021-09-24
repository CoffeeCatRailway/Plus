package io.github.coffeecatrailway.plus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 22/09/2021
 */
public class BrittleBasaltBlock extends RotatedPillarBlock
{
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public BrittleBasaltBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random)
    {
        this.tick(state, level, pos, random);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random)
    {
        if ((random.nextInt(3) == 0 || this.fewerNeigboursThan(level, pos, 4)) && this.slightlyCrack(state, level, pos))
        {
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

            for (Direction direction : Direction.values())
            {
                mutablePos.setWithOffset(pos, direction);
                BlockState blockstate = level.getBlockState(mutablePos);
                if (blockstate.is(this) && !this.slightlyCrack(blockstate, level, mutablePos))
                    level.getBlockTicks().scheduleTick(mutablePos, this, Mth.nextInt(random, 20, 40));
            }
        } else
            level.getBlockTicks().scheduleTick(pos, this, Mth.nextInt(random, 20, 40));
    }

    private boolean slightlyCrack(BlockState state, Level level, BlockPos pos)
    {
        int i = state.getValue(AGE);
        if (i < 3)
        {
            level.setBlock(pos, state.setValue(AGE, i + 1), 2);
            return false;
        } else
        {
            this.crack(level, pos);
            return true;
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos1, boolean p_220069_6_)
    {
        if (block == this && this.fewerNeigboursThan(level, pos, 2))
            this.crack(level, pos);
        super.neighborChanged(state, level, pos, block, pos1, p_220069_6_);
    }

    protected void crack(Level level, BlockPos pos)
    {
        if (level.dimensionType().ultraWarm())
            level.removeBlock(pos, false);
        else
        {
            level.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
            level.neighborChanged(pos, Blocks.LAVA, pos);
        }
    }

    private boolean fewerNeigboursThan(BlockGetter blockGetter, BlockPos pos, int max)
    {
        int count = 0;
        BlockPos.MutableBlockPos mutableBlock = new BlockPos.MutableBlockPos();
        for (Direction direction : Direction.values())
        {
            mutableBlock.setWithOffset(pos, direction);
            if (blockGetter.getBlockState(mutableBlock).is(this))
            {
                count++;
                if (count >= max)
                    return false;
            }
        }
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(AGE);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos pos, BlockState state)
    {
        return ItemStack.EMPTY;
    }
}
