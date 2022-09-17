package io.github.coffeecatrailway.plus.common.block;

import io.github.coffeecatrailway.plus.common.block.entity.CardTableBlockEntity;
import io.github.coffeecatrailway.plus.registry.PlusBlockEntities;
import io.github.coffeecatrailway.plus.registry.PlusStats;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new CardTableBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type)
    {
        return level.isClientSide ? null : createTickerHelper(type, PlusBlockEntities.CARD_TABLE.get(), CardTableBlockEntity::tick);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState)
    {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (level.isClientSide)
        {
            return InteractionResult.SUCCESS;
        } else
        {
            if (level.getBlockEntity(pos) instanceof CardTableBlockEntity blockEntity)
            {
                player.openMenu(blockEntity);
                player.awardStat(PlusStats.INTERACT_WITH_CARD_TABLE);
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState blockState2, boolean bl)
    {
        if (!state.is(blockState2.getBlock()))
        {
            if (level.getBlockEntity(pos) instanceof CardTableBlockEntity blockEntity)
                Containers.dropContents(level, pos, blockEntity.getInventory());
            super.onRemove(state, level, pos, blockState2, bl);
        }
    }
}
