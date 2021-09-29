package io.github.coffeecatrailway.plus.integration.jei;

import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.client.gui.SawBenchScreen;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CoffeeCatRailway
 * Created: 29/09/2021
 */
@JeiPlugin
public class PlusJEIPlugin implements IModPlugin
{
    private static final ResourceLocation UID = PlusMod.getLocation("plugin/jei");

    protected static final ResourceLocation SAW_BENCH_UID = PlusMod.getLocation("category/saw_bench");

    @Override
    public ResourceLocation getPluginUid()
    {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new SawBenchCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        registration.addRecipes(getRecipesOfType(PlusRecipes.SAW_BENCH_TYPE), SAW_BENCH_UID);
    }

    private static List<Recipe<?>> getRecipesOfType(RecipeType<?> type)
    {
        return Minecraft.getInstance().level.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType() == type).collect(Collectors.toList());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(new ItemStack(PlusBlocks.SAW_BENCH.get()), SAW_BENCH_UID);
    }
}
