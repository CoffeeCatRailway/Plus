package io.github.coffeecatrailway.plus.integration.forge.jei;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.item.crafting.SawBenchRecipe;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

/**
 * @author CoffeeCatRailway
 * Created: 29/09/2021
 */
public class SawBenchCategory implements IRecipeCategory<SawBenchRecipe>
{
    private static final ResourceLocation RECIPE_GUI_VANILLA = new ResourceLocation(ModIds.JEI_ID, "textures/gui/gui_vanilla.png");

    private static final int inputSlot = 0;
    private static final int outputSlot = 1;

    public static final int width = 82;
    public static final int height = 34;

    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;

    public SawBenchCategory(IGuiHelper guiHelper)
    {
        ResourceLocation location = RECIPE_GUI_VANILLA;
        background = guiHelper.createDrawable(location, 0, 220, width, height);
        icon = guiHelper.createDrawableIngredient(new ItemStack(PlusBlocks.SAW_BENCH.get()));
        localizedName = new TranslatableComponent("gui." + Plus.MOD_ID + ".jei.category.saw_bench");
    }

    @Override
    public ResourceLocation getUid()
    {
        return PlusJEIPlugin.SAW_BENCH_UID;
    }

    @Override
    public Class<? extends SawBenchRecipe> getRecipeClass()
    {
        return SawBenchRecipe.class;
    }

    @Override
    public Component getTitle()
    {
        return this.localizedName;
    }

    @Override
    public IDrawable getBackground()
    {
        return this.background;
    }

    @Override
    public IDrawable getIcon()
    {
        return this.icon;
    }

    @Override
    public void setIngredients(SawBenchRecipe recipe, IIngredients ingredients)
    {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SawBenchRecipe recipe, IIngredients ingredients)
    {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(inputSlot, true, 0, 8);
        guiItemStacks.init(outputSlot, false, 60, 8);

        guiItemStacks.set(ingredients);
    }
}
