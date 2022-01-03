package io.github.coffeecatrailway.plus.registry.forge;

import io.github.coffeecatrailway.plus.common.item.FoxHatItem;
import io.github.coffeecatrailway.plus.common.item.PlusArmorMaterials;
import io.github.coffeecatrailway.plus.common.item.forge.FoxHatItemForge;
import net.minecraft.world.item.Item;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
public class PlusItemsImpl
{
    public static Function<Item.Properties, FoxHatItem> getFoxHatItem()
    {
        return prop -> new FoxHatItemForge(PlusArmorMaterials.FOX_HAT, prop);
    }

    public static Function<Item.Properties, FoxHatItem> getSnowFoxHatItem()
    {
        return prop -> new FoxHatItemForge(PlusArmorMaterials.SNOW_FOX_HAT, prop);
    }
}
