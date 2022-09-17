package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.inventory.PlayingCardPackMenu;
import io.github.coffeecatrailway.plus.common.inventory.SimpleItemContainer;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import io.github.coffeecatrailway.plus.registry.PlusStats;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 26/08/2022
 */
public class PlayingCardPackItem extends Item implements HasInitialNbt
{
    private static final Component CONTAINER_TITLE = new TranslatableComponent("container." + Plus.MOD_ID + ".playing_card_pack");

    public PlayingCardPackItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide)
            return InteractionResultHolder.success(stack);
        else
        {
            player.openMenu(new SimpleMenuProvider((id, inventory, player1) -> new PlayingCardPackMenu(id, inventory, new SimpleItemContainer(54, stack)), CONTAINER_TITLE));
            player.awardStat(PlusStats.USE_PLAYING_CARD_PACK);
            return InteractionResultHolder.consume(stack);
        }
    }

    @Override
    public void saveInitialNbt(ItemStack stack, CompoundTag tag)
    {
        if (!tag.contains("Items"))
            tag.put("Items", new ListTag());
        for (int i = 0; i < 54; i++)
        {
            ListTag items = tag.getList("Items", 10);
            CompoundTag itemTag = new CompoundTag();
            itemTag.putByte("Slot", (byte) i);
            items.add(i, ItemStack.EMPTY.save(itemTag));
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack otherStack, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess)
    {
        if (clickAction == ClickAction.SECONDARY && slot.allowModification(player) && !otherStack.isEmpty() && !(player.containerMenu instanceof PlayingCardPackMenu))
        {
            CompoundTag tag = stack.getOrCreateTag();
            if (!tag.contains("Items"))
                return false;

            ListTag items = tag.getList("Items", 10);
            for (int i = 0; i < items.size(); i++)
            {
                ItemStack cardStack = ItemStack.of((CompoundTag) items.get(i));
                if (cardStack.isEmpty())
                {
                    setStack(stack, otherStack.split(1), i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player)
    {
        this.add52Cards(stack);
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list)
    {
        if (this.allowdedIn(tab))
        {
            ItemStack stack = new ItemStack(this);
            this.add52Cards(stack);
            list.add(stack);
        }
    }

    private void add52Cards(ItemStack packStack)
    {
        int i = 0;
        for (Supplier<PlayingCardItem> item : PlusItems.PLAYING_CARDS)
        {
            setStack(packStack, new ItemStack(item.get()), i);
            i++;
        }
        setStack(packStack, new ItemStack(PlusItems.CARD_JOKER_BLACK.get()), 52);
        setStack(packStack, new ItemStack(PlusItems.CARD_JOKER_RED.get()), 53);
    }

    public static void setStack(ItemStack packStack, ItemStack toAdd, int index)
    {
        CompoundTag tag = packStack.getOrCreateTag();
        if (!tag.contains("Items"))
            return;

        ListTag items = tag.getList("Items", 10);
        CompoundTag itemTag = new CompoundTag();
        itemTag.putByte("Slot", (byte) index);
        items.set(index, toAdd.save(itemTag));
    }

    public static ItemStack takeCard(ItemStack packStack, boolean simulate)
    {
        CompoundTag tag = packStack.getOrCreateTag();
        if (!tag.contains("Items"))
            return ItemStack.EMPTY;

        ListTag items = tag.getList("Items", 10);
        if (items.size() < 1)
            return ItemStack.EMPTY;

        boolean[] failedSlots = new boolean[items.size()];
        ItemStack card = ItemStack.EMPTY;
        Random random = Minecraft.getInstance().level != null ? Minecraft.getInstance().level.random : new Random((42L));

        while (card.isEmpty())
        {
            int cardIndex = random.nextInt(items.size());
            if (!failedSlots[cardIndex])
            {
                ItemStack tempStack = ItemStack.of((CompoundTag) items.get(cardIndex));
                if (tempStack.isEmpty() || !isCard(tempStack.getItem(), true))
                    failedSlots[cardIndex] = true;
                else
                {
                    if (!simulate)
                        setStack(packStack, ItemStack.EMPTY, cardIndex);
                    card = tempStack.copy();
                }
            }
        }

        return card;
    }

    private static boolean isCard(Item item, boolean ignoreJokers)
    {
        return (ignoreJokers && (!item.equals(PlusItems.CARD_JOKER_BLACK.get()) || !item.equals(PlusItems.CARD_JOKER_RED.get()))) || PlusItems.PLAYING_CARDS.stream().map(Supplier::get).anyMatch(item::equals);
    }
}
