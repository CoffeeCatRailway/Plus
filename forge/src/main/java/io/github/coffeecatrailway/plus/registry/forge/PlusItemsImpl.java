package io.github.coffeecatrailway.plus.registry.forge;

import gg.moonflower.pollen.api.platform.Platform;
import io.github.coffeecatrailway.plus.common.item.FoxHatItem;
import io.github.coffeecatrailway.plus.common.item.PlusArmorMaterials;
import io.github.coffeecatrailway.plus.common.item.WarmthAmuletItem;
import io.github.coffeecatrailway.plus.common.item.forge.FoxHatItemForge;
import io.github.coffeecatrailway.plus.common.item.forge.WarmthAmuletItemCurios;
import io.github.coffeecatrailway.plus.common.item.forge.WarmthAmuletItemForge;
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
        return prop -> new FoxHatItemForge(PlusArmorMaterials.FOX_HAT, prop);
    }

    public static Function<Item.Properties, FoxHatItem> getSnowFoxHatItem()
    {
        return prop -> new FoxHatItemForge(PlusArmorMaterials.SNOW_FOX_HAT, prop);
    }

    public static Function<Item.Properties, WarmthAmuletItem> getWarmthAmuletItem()
    {
        if (Platform.isModLoaded("curios"))
            return WarmthAmuletItemCurios::new;
        return WarmthAmuletItemForge::new;
    }
}
