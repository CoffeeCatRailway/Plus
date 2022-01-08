package io.github.coffeecatrailway.plus.integration.fabric.rei;

import io.github.coffeecatrailway.plus.common.item.crafting.SawBenchRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author CoffeeCatRailway
 * Created: 8/01/2022
 */
public class SawBenchDisplay extends BasicDisplay
{
    public SawBenchDisplay(SawBenchRecipe recipe)
    {
        super(EntryIngredients.ofIngredients(recipe.getIngredients()), Collections.singletonList(EntryIngredients.of(recipe.getResultItem())), Optional.ofNullable(recipe.getId()));
    }

    public SawBenchDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location)
    {
        super(inputs, outputs, location);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier()
    {
        return PlusREIPlugin.SAW_BENCH;
    }

    public static Serializer<SawBenchDisplay> serializer()
    {
        return Serializer.ofSimple(SawBenchDisplay::new);
    }
}
