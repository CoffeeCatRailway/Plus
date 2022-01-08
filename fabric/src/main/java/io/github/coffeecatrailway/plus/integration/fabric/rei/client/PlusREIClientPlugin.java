package io.github.coffeecatrailway.plus.integration.fabric.rei.client;

import io.github.coffeecatrailway.plus.common.item.crafting.SawBenchRecipe;
import io.github.coffeecatrailway.plus.integration.fabric.rei.PlusREIPlugin;
import io.github.coffeecatrailway.plus.integration.fabric.rei.SawBenchDisplay;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusRecipes;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * @author CoffeeCatRailway
 * Created: 8/01/2022
 */
@Environment(EnvType.CLIENT)
public class PlusREIClientPlugin implements REIClientPlugin
{
    @Override
    public void registerCategories(CategoryRegistry registry)
    {
        registry.add(new SawBenchCategory());
        registry.addWorkstations(PlusREIPlugin.SAW_BENCH, EntryStacks.of(PlusBlocks.SAW_BENCH.get()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry)
    {
        registry.registerRecipeFiller(SawBenchRecipe.class, PlusRecipes.SAW_BENCH_TYPE.get(), SawBenchDisplay::new);
    }
}
