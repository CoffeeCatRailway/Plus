package io.github.coffeecatrailway.plus.common.inventory;

import com.mojang.datafixers.util.Pair;
import io.github.coffeecatrailway.plus.client.PlusClient;
import io.github.coffeecatrailway.plus.common.item.PlayingCardPackItem;
import io.github.coffeecatrailway.plus.registry.PlusItems;
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
 * Created: 28/08/2022
 */
public class CardTableMenu extends AbstractContainerMenu
{
    private final Container container;

    public CardTableMenu(int id, Inventory inventory)
    {
        this(id, inventory, new SimpleContainer(2));
    }

    public CardTableMenu(int id, Inventory inventory, Container container)
    {
        super(PlusMenus.CARD_TABLE.get(), id);
        checkContainerSize(container, 2);
        this.container = container;
        this.container.startOpen(inventory.player);

        this.addSlot(new Slot(this.container, 0, 11, 123)
        {
            @Override
            public boolean mayPlace(ItemStack stack)
            {
                return stack.getItem().equals(PlusItems.PLAYING_CARD_PACK.get());
            }

            @Nullable
            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon()
            {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, PlusClient.EMPTY_SLOT_CARD_PACK);
            }
        });
        this.addSlot(new CardSlot(this.container, 1, 11, 141)
        {
        });

        int i;
        for (i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 36 + j * 18, 123 + i * 18));

        for (i = 0; i < 9; i++)
            this.addSlot(new Slot(inventory, i, 36 + i * 18, 181));
    }

    @Override
    public boolean stillValid(Player player)
    {
        return this.container.stillValid(player);
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
                if (!this.moveItemStackTo(itemStack2, 2, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!this.moveItemStackTo(itemStack2, 0, 2, false))
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
}
