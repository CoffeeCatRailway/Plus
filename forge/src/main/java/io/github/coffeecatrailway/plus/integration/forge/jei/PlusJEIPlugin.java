package io.github.coffeecatrailway.plus.integration.forge.jei;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.item.crafting.SawBenchRecipe;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.plugins.vanilla.crafting.CategoryRecipeValidator;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CoffeeCatRailway
 * Created: 29/09/2021
 */
@JeiPlugin
public class PlusJEIPlugin implements IModPlugin
{
    protected static final RecipeType<SawBenchRecipe> SAW_BENCH = new RecipeType<>(Plus.getLocation("saw_bench"), SawBenchRecipe.class);

    private SawBenchCategory sawBenchCategory;

    @Override
    public ResourceLocation getPluginUid()
    {
        return Plus.getLocation("plugin/jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration reg)
    {
        IGuiHelper guiHelper = reg.getJeiHelpers().getGuiHelper();
        reg.addRecipeCategories(this.sawBenchCategory = new SawBenchCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration reg)
    {
        reg.addRecipes(SAW_BENCH, getRecipesOfType(PlusRecipes.SAW_BENCH_TYPE.get(), this.sawBenchCategory));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration reg)
    {
        reg.addRecipeCatalyst(new ItemStack(PlusBlocks.SAW_BENCH.get()), SAW_BENCH);
    }

    private static <C extends Container, R extends Recipe<C>> List<R> getRecipesOfType(net.minecraft.world.item.crafting.RecipeType<R> type, IRecipeCategory<R> category)
    {
        CategoryRecipeValidator<R> validator = new CategoryRecipeValidator<>(category, 1);
        return getValidHandledRecipes(type, validator);
    }

    private static <C extends Container, R extends Recipe<C>> List<R> getValidHandledRecipes(net.minecraft.world.item.crafting.RecipeType<R> recipeType, CategoryRecipeValidator<R> validator)
    {
        return Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(recipeType)
                .stream()
                .filter(r -> validator.isRecipeValid(r) && validator.isRecipeHandled(r))
                .toList();
    }
}
