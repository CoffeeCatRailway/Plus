package io.github.coffeecatrailway.plus.data.gen;

import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.function.Consumer;

/**
 * @author CoffeeCatRailway
 * Created: 26/09/2021
 */
public class PlusRecipeProvider extends RecipeProvider
{
    public PlusRecipeProvider(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(PlusItems.FOX_HAT.get()).define('f', PlusItems.FOX_FUR.get()).pattern("fff").pattern("f f").pattern(" f ").unlockedBy("has_fur", has(PlusItems.FOX_FUR.get())).save(consumer);
        ShapedRecipeBuilder.shaped(PlusItems.SNOW_FOX_HAT.get()).define('f', PlusItems.SNOW_FOX_FUR.get()).pattern("fff").pattern("f f").pattern(" f ").unlockedBy("has_fur", has(PlusItems.SNOW_FOX_FUR.get())).save(consumer);
    }
}
