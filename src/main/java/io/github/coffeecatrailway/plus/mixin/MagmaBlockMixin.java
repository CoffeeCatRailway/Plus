package io.github.coffeecatrailway.plus.mixin;

import io.github.coffeecatrailway.plus.registry.PlusEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.MagmaBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author CoffeeCatRailway
 * Created: 24/09/2021
 */
@Mixin(MagmaBlock.class)
public class MagmaBlockMixin
{
    @Redirect(method = "stepOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;hasFrostWalker(Lnet/minecraft/world/entity/LivingEntity;)Z"))
    public boolean stepOn(LivingEntity entity)
    {
        return (EnchantmentHelper.hasFrostWalker(entity) || EnchantmentHelper.getEnchantmentLevel(PlusEnchantments.HEAT_WALKER.get(), entity) > 0);
    }
}
