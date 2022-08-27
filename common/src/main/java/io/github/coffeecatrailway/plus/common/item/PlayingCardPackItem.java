package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.inventory.PlayingCardPackMenu;
import io.github.coffeecatrailway.plus.common.inventory.SimpleItemContainer;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import io.github.coffeecatrailway.plus.registry.PlusStats;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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
    }

    public static void addStack(ItemStack stack, ItemStack toAdd, int index)
    {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("Items"))
            return;

        ListTag items = tag.getList("Items", 10);
        CompoundTag itemTag = new CompoundTag();
        itemTag.putByte("Slot", (byte) index);
        items.add(index, toAdd.save(itemTag));
    }
}
