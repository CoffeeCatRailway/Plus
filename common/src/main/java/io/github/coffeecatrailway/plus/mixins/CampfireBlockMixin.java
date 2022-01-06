package io.github.coffeecatrailway.plus.mixins;

import io.github.coffeecatrailway.plus.registry.PlusEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.CampfireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author CoffeeCatRailway
 * Created: 6/01/2022
 */
@Mixin(CampfireBlock.class)
public class CampfireBlockMixin
{
    @Redirect(method = "entityInside", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;hasFrostWalker(Lnet/minecraft/world/entity/LivingEntity;)Z"))
    public boolean entityInside(LivingEntity entity)
    {
        return (EnchantmentHelper.hasFrostWalker(entity) || EnchantmentHelper.getEnchantmentLevel(PlusEnchantments.HEAT_WALKER.get(), entity) > 0);
    }
}
