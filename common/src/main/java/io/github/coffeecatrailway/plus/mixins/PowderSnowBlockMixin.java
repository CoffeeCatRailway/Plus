package io.github.coffeecatrailway.plus.mixins;

import io.github.coffeecatrailway.plus.registry.PlusItems;
import io.github.coffeecatrailway.plus.util.MixinExpectPlatforms;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author CoffeeCatRailway
 * Created: 21/01/2022
 */
@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin
{
    @Inject(method = "entityInside", at = @At(value = "TAIL"))
    private void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, CallbackInfo ci)
    {
        boolean flag = !MixinExpectPlatforms.hasWarmthAmulet(entity);
        if (entity instanceof Player)
            for (int i = 0; i < 9; i++)
                if (((Player) entity).getInventory().items.get(i).getItem() == PlusItems.WARMTH_AMULET.get())
                    flag = false;
        entity.setIsInPowderSnow(flag);
    }
}
