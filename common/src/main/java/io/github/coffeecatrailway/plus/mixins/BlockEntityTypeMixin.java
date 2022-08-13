package io.github.coffeecatrailway.plus.mixins;

import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author CoffeeCatRailway
 * Created: 13/08/2022
 */
@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin
{
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    public void isValid(BlockState state, CallbackInfoReturnable<Boolean> callback) {
        if (BlockEntityType.CAMPFIRE.equals(this) && state.getBlock() instanceof CampfireBlock)
            callback.setReturnValue(true);
    }
}
