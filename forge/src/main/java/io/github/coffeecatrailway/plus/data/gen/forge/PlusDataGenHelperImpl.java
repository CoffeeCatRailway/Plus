package io.github.coffeecatrailway.plus.data.gen.forge;

import io.github.coffeecatrailway.plus.data.gen.PlusDataGenHelper;
import io.github.coffeecatrailway.plus.data.gen.PlusDataGenHelperCommon;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
public class PlusDataGenHelperImpl
{
    public static <T extends Item> void languageItem(Supplier<T> supplier, String id, @Nullable String name)
    {
        PlusLanguage.ITEMS.put(supplier, name == null ? PlusDataGenHelperCommon.capitalize(id) : name);
    }
}
