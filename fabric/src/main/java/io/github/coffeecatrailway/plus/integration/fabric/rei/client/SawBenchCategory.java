package io.github.coffeecatrailway.plus.integration.fabric.rei.client;

import com.google.common.collect.Lists;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.integration.fabric.rei.PlusREIPlugin;
import io.github.coffeecatrailway.plus.integration.fabric.rei.SawBenchDisplay;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 8/01/2022
 */
public class SawBenchCategory implements DisplayCategory<SawBenchDisplay>
{
    public SawBenchCategory()
    {
    }

    @Override
    public Renderer getIcon()
    {
        return EntryStacks.of(PlusBlocks.SAW_BENCH.get());
    }

    @Override
    public Component getTitle()
    {
        return new TranslatableComponent("category." + Plus.MOD_ID + ".rei.saw_bench");
    }

    @Override
    public CategoryIdentifier<? extends SawBenchDisplay> getCategoryIdentifier()
    {
        return PlusREIPlugin.SAW_BENCH;
    }

    @Override
    public List<Widget> setupDisplay(SawBenchDisplay display, Rectangle bounds)
    {
        Point startPoint = new Point(bounds.getCenterX() - 41, bounds.getCenterY() - 13);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 27, startPoint.y + 4)));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 61, startPoint.y + 5)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 61, startPoint.y + 5)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 4, startPoint.y + 5)).entries(display.getInputEntries().get(0)).markInput());
        return widgets;
    }

    @Override
    public int getDisplayHeight()
    {
        return 36;
    }
}
