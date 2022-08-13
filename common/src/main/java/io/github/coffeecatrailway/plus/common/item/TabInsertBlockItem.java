package io.github.coffeecatrailway.plus.common.item;

import gg.moonflower.pollen.api.item.TabFiller;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.function.Predicate;

/**
 * @author CoffeeCatRailway
 * Created: 13/08/2022
 * <p>
 * Based on {@link gg.moonflower.pollen.api.item.TabInsertItem}
 */
public class TabInsertBlockItem extends BlockItem
{
    private final Predicate<ItemStack> filter;

    public TabInsertBlockItem(Item insertAfter, Block block, Properties properties)
    {
        super(block, properties);
        this.filter = stack -> stack.getItem() == insertAfter;
    }

    @Override
    public void fillItemCategory(CreativeModeTab category, NonNullList<ItemStack> items) {
        if (this.allowdedIn(category))
            TabFiller.insert(new ItemStack(this), false, items, this.filter);
    }
}
