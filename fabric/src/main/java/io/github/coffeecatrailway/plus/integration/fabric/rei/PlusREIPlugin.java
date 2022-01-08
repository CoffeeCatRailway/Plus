package io.github.coffeecatrailway.plus.integration.fabric.rei;

import io.github.coffeecatrailway.plus.Plus;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;

/**
 * @author CoffeeCatRailway
 * Created: 8/01/2022
 */
public class PlusREIPlugin implements REIServerPlugin
{
    public static final CategoryIdentifier<SawBenchDisplay> SAW_BENCH = CategoryIdentifier.of(Plus.getLocation("plugins/saw_bench"));

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry)
    {
        registry.register(SAW_BENCH, SawBenchDisplay.serializer());
    }
}
