package io.github.coffeecatrailway.plus.common.inventory;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

/**
 * @author CoffeeCatRailway
 * Created: 27/08/2022
 * <br>
 * Based on <a href="https://github.com/MrCrayfish/Backpacked/blob/1.19.X/src/main/java/com/mrcrayfish/backpacked/inventory/BackpackInventory.java">Backpacked</a>
 */
public class SimpleItemContainer extends SimpleContainer
{
    private final ItemStack stack;

    public SimpleItemContainer(int size, ItemStack stack)
    {
        super(size);
        this.stack = stack;

        // Load item contents
        CompoundTag tag = this.stack.getOrCreateTag();
        if (tag.contains("Items"))
        {
            ListTag items = tag.getList("Items", 10);
            for (int i = 0; i < items.size(); i++)
            {
                CompoundTag itemTag = items.getCompound(i);
                int slot = itemTag.getByte("Slot") & 255;
                if (slot < this.getContainerSize())
                    this.setItem(slot, ItemStack.of(itemTag));
            }
        }
    }

    @Override
    public void setChanged()
    {
        super.setChanged();
        ListTag itemsTag = new ListTag();
        for (int i = 0; i < this.getContainerSize(); i++)
        {
            ItemStack stack = this.getItem(i);
            if (!stack.isEmpty())
            {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putByte("Slot", (byte) i);
                itemsTag.add(stack.save(itemTag));
            }
        }
        this.stack.getOrCreateTag().put("Items", itemsTag);
    }
}
