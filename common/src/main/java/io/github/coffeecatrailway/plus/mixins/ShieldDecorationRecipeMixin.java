package io.github.coffeecatrailway.plus.mixins;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.crafting.ShieldDecorationRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author CoffeeCatRailway
 * Created: 10/08/2022
 */
@Mixin(ShieldDecorationRecipe.class)
public class ShieldDecorationRecipeMixin
{
    @Redirect(method = "matches(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/world/level/Level;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean matches(ItemStack instance, Item item)
    {
        return instance.is(item) || instance.getItem() instanceof ShieldItem;
    }

    @Redirect(method = "assemble(Lnet/minecraft/world/inventory/CraftingContainer;)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean assemble(ItemStack instance, Item item)
    {
        return instance.is(item) || instance.getItem() instanceof ShieldItem;
    }
}
