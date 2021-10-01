package io.github.coffeecatrailway.plus.mixin;

import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.common.inventory.FletchingTableMenu;
import io.github.coffeecatrailway.plus.registry.PlusStats;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FletchingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author CoffeeCatRailway
 * Created: 1/10/2021
 */
@Mixin(FletchingTableBlock.class)
public class FletchingTableBlockMixin extends Block
{
    private static final Component CONTAINER_TITLE = new TranslatableComponent("container." + PlusMod.MOD_ID + ".fletching_table");

    public FletchingTableBlockMixin(Properties p_i48440_1_)
    {
        super(p_i48440_1_);
    }

    @Inject(method = "use(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;", at = @At(value = "HEAD"), cancellable = true)
    public void use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> callback)
    {
        if (level.isClientSide())
            callback.setReturnValue(InteractionResult.SUCCESS);
        else
        {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(PlusStats.INTERACT_WITH_FLETCHING_TABLE);
            callback.setReturnValue(InteractionResult.CONSUME);
        }
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos)
    {
        return new SimpleMenuProvider((id, inventory, player) -> new FletchingTableMenu(id, inventory, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE);
    }
}
