package io.github.coffeecatrailway.plus.mixin;

import io.github.coffeecatrailway.plus.PlusConfig;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * @author CoffeeCatRailway
 * Created: 22/09/2021
 */
@Mixin(FrostWalkerEnchantment.class)
public class FrostWalkerEnchantmentMixin
{
    @ModifyArg(method = "onEntityMoved(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;I)V", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"), index = 1)
    private static int onEntityMoved(int b)
    {
        return (int) (b - 2 + PlusConfig.SERVER.frostWalkerLevel.get());
    }
}
