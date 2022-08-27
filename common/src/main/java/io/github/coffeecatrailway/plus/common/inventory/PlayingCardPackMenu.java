package io.github.coffeecatrailway.plus.common.inventory;

import com.mojang.datafixers.util.Pair;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.item.PlayingCardItem;
import io.github.coffeecatrailway.plus.registry.PlusMenus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 27/08/2022
 */
public class PlayingCardPackMenu extends AbstractContainerMenu
{
    private final Container container;

    public PlayingCardPackMenu(int id, Inventory inventory)
    {
        this(id, inventory, new SimpleContainer(54));
    }

    public PlayingCardPackMenu(int id, Inventory inventory, Container container)
    {
        super(PlusMenus.PLAYING_CARD_PACK.get(), id);
        checkContainerSize(container, 54);
        this.container = container;
        this.container.startOpen(inventory.player);

        int i, j;
        for (i = 0; i < 4; i++)
            for (j = 0; j < 13; j++)
                this.addSlot(new CardSlot(this.container, j + i * 13, 6 + j * 18, 15 + i * 18));

        this.addSlot(new CardSlot(this.container, 52, 96, 90));
        this.addSlot(new CardSlot(this.container, 53, 132, 90));

        for (i = 0; i < 3; i++)
            for (j = 0; j < 9; j++)
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 42 + j * 18, 111 + i * 18));

        for (i = 0; i < 9; i++)
            this.addSlot(new Slot(inventory, i, 42 + i * 18, 169));
    }

    @Override
    public boolean stillValid(Player player)
    {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem())
        {
            ItemStack itemStack2 = slot.getItem();
            stack = itemStack2.copy();
            if (index < 54)
            {
                if (!this.moveItemStackTo(itemStack2, 54, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!this.moveItemStackTo(itemStack2, 0, 54, false))
                return ItemStack.EMPTY;

            if (itemStack2.isEmpty())
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
        }

        return stack;
    }

    @Override
    public void removed(Player player)
    {
        super.removed(player);
        this.container.stopOpen(player);
    }

    public Container getContainer()
    {
        return this.container;
    }

    private static class CardSlot extends Slot
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
            return Pair.of(InventoryMenu.BLOCK_ATLAS, Plus.EMPTY_SLOT_CARD);
        }
    }
}
