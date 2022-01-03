package io.github.coffeecatrailway.plus.registry.fabric;

import io.github.coffeecatrailway.plus.common.item.FoxHatItem;
import io.github.coffeecatrailway.plus.common.item.PlusArmorMaterials;
import net.minecraft.world.item.Item;

import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
public class PlusItemsImpl
{
    public static Function<Item.Properties, FoxHatItem> getFoxHatItem()
    {
        return prop -> new FoxHatItem(PlusArmorMaterials.FOX_HAT, prop);
    }

    public static Function<Item.Properties, FoxHatItem> getSnowFoxHatItem()
    {
        return prop -> new FoxHatItem(PlusArmorMaterials.SNOW_FOX_HAT, prop);
    }
}
