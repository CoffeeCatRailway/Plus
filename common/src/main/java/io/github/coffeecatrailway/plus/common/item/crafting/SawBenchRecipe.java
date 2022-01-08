package io.github.coffeecatrailway.plus.common.item.crafting;

import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;

/**
 * @author CoffeeCatRailway
 * Created: 27/09/2021
 */
public class SawBenchRecipe extends SingleItemRecipe
{
    public SawBenchRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result)
    {
        super(PlusRecipes.SAW_BENCH_TYPE.get(), PlusRecipes.SAW_BENCH_SERIALIZER.get(), id, group, ingredient, result);
    }

    @Override
    public boolean matches(Container menu, Level level)
    {
        return this.ingredient.test(menu.getItem(0));
    }

    @Override
    public ItemStack getToastSymbol()
    {
        return new ItemStack(PlusBlocks.SAW_BENCH.get());
    }
}
