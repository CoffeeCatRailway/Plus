package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.Plus;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 27/02/2022
 */
public class DescribedItem extends Item
{
    public DescribedItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag)
    {
        list.add(new TranslatableComponent(stack.getItem().getDescriptionId() + ".description").withStyle(ChatFormatting.GRAY));
    }
}
