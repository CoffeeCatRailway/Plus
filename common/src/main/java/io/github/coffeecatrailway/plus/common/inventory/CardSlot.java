package io.github.coffeecatrailway.plus.common.inventory;

import com.mojang.datafixers.util.Pair;
import io.github.coffeecatrailway.plus.client.PlusClient;
import io.github.coffeecatrailway.plus.common.item.PlayingCardItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 28/08/2022
 */
public class CardSlot extends Slot
{
    public CardSlot(Container container, int index, int x, int y)
    {
        super(container, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {
        return stack.getItem() instanceof PlayingCardItem;
    }

    @Nullable
    @Override
    public Pair<ResourceLocation, ResourceLocation> getNoItemIcon()
    {
        return Pair.of(InventoryMenu.BLOCK_ATLAS, PlusClient.EMPTY_SLOT_CARD);
    }
}
