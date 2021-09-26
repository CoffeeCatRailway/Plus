package io.github.coffeecatrailway.plus.data.gen;

import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;

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

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PlusItems.FOX_MEAT.get()), PlusItems.COOKED_FOX_MEAT.get(), .35f, 200).unlockedBy("has_fox_meat", has(PlusItems.FOX_MEAT.get())).save(consumer);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.FOX_MEAT.get()), PlusItems.COOKED_FOX_MEAT.get(), .35f, 100, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_fox_meat", has(PlusItems.FOX_MEAT.get())).save(consumer, PlusMod.getLocation("cooked_fox_meat_from_smoking"));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.FOX_MEAT.get()), PlusItems.COOKED_FOX_MEAT.get(), .35f, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_fox_meat", has(PlusItems.FOX_MEAT.get())).save(consumer, PlusMod.getLocation("cooked_fox_meat_from_campfire"));
    }
}
