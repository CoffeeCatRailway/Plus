package io.github.coffeecatrailway.plus.common.item;

import gg.moonflower.pollen.api.item.TabFiller;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author CoffeeCatRailway
 * Created: 27/02/2022
 * <p>
 * Based on {@link gg.moonflower.pollen.api.item.TabInsertItem}
 */
public class DescribedItem extends Item
{
    private final Predicate<ItemStack> filter;

    public DescribedItem(Item insertAfter, Properties properties)
    {
        super(properties);
        this.filter = stack -> stack.getItem() == insertAfter;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag)
    {
        list.add(new TranslatableComponent(stack.getItem().getDescriptionId() + ".description").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void fillItemCategory(CreativeModeTab category, NonNullList<ItemStack> items) {
        if (this.allowdedIn(category))
            TabFiller.insert(new ItemStack(this), false, items, this.filter);
    }
}
