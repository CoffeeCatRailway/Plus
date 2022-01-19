package io.github.coffeecatrailway.plus.common.item.fabric;

import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.client.TrinketRenderer;
import io.github.coffeecatrailway.plus.common.item.WarmthAmuletItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * @author CoffeeCatRailway
 * Created: 18/01/2022
 */
public class WarmthAmuletItemFabric extends WarmthAmuletItem implements Trinket, TrinketRenderer
{
    public WarmthAmuletItemFabric(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (TrinketItem.equipItem(player, stack))
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        return super.use(level, player, hand);
    }
}
