package io.github.coffeecatrailway.plus.integration.forge.jei;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.item.crafting.SawBenchRecipe;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
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
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(PlusBlocks.SAW_BENCH.get()));
        localizedName = new TranslatableComponent("gui." + Plus.MOD_ID + ".jei.category.saw_bench");
    }

    @Override
    public RecipeType<SawBenchRecipe> getRecipeType()
    {
        return PlusJEIPlugin.SAW_BENCH;
    }

    @SuppressWarnings("removal")
    @Override
    public ResourceLocation getUid()
    {
        return this.getRecipeType().getUid();
    }

    @SuppressWarnings("removal")
    @Override
    public Class<? extends SawBenchRecipe> getRecipeClass()
    {
        return this.getRecipeType().getRecipeClass();
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
    public void setRecipe(IRecipeLayoutBuilder builder, SawBenchRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 9).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 9).addIngredient(VanillaTypes.ITEM, recipe.getResultItem());
    }
}
