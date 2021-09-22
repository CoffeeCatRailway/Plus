package io.github.coffeecatrailway.plus.mixin;

import io.github.coffeecatrailway.plus.registry.PlusEnchantments;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.item.enchantment.WaterWalkerEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author CoffeeCatRailway
 * Created: 22/09/2021
 */
@Mixin(WaterWalkerEnchantment.class)
public class WaterWalkerEnchantmentMixin
{
    @Inject(method = "checkCompatibility(Lnet/minecraft/world/item/enchantment/Enchantment;)Z", at = @At("RETURN"), cancellable = true)
    public void checkCompatibility(Enchantment enchantment, CallbackInfoReturnable<Boolean> callback)
    {
        callback.setReturnValue(callback.getReturnValue() && enchantment != PlusEnchantments.HEAT_WALKER.get());
    }
}
