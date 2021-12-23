package io.github.coffeecatrailway.plus.common.block;

import io.github.coffeecatrailway.plus.PlusConfig;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.inventory.SawBenchMenu;
import io.github.coffeecatrailway.plus.registry.PlusStats;
import io.github.coffeecatrailway.plus.util.PlusDamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 27/09/2021
 */
public class SawBenchBlock extends StonecutterBlock
{
    private static final Component CONTAINER_TITLE = new TranslatableComponent("container." + Plus.MOD_ID + ".saw_bench");

    public SawBenchBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(PlusStats.INTERACT_WITH_SAW_BENCH);
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new SawBenchMenu(id, inventory, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity)
    {
        if (entity instanceof LivingEntity)
            entity.hurt(PlusDamageSource.SAW_BLADE, (float) (PlusConfig.SERVER.sawBladeDamage.get() * 1f));
        super.entityInside(state, level, pos, entity);
    }
}
