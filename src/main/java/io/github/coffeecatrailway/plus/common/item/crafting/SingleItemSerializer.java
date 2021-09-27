package io.github.coffeecatrailway.plus.common.item.crafting;

import net.minecraft.world.item.crafting.SingleItemRecipe;

/**
 * @author CoffeeCatRailway
 * Created: 27/09/2021
 */
public class SingleItemSerializer<T extends SingleItemRecipe> extends SingleItemRecipe.Serializer<T>
{
    public SingleItemSerializer(SingleItemMaker maker)
    {
        super(maker);
    }
}
