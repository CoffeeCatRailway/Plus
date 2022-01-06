package io.github.coffeecatrailway.plus.data.gen.forge;

import io.github.coffeecatrailway.plus.data.gen.PlusDataGenHelperCommon;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
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

    public static <T extends Block> void languageBlock(Supplier<T> supplier, String id, @Nullable String name)
    {
        PlusLanguage.BLOCKS.put(supplier, name == null ? PlusDataGenHelperCommon.capitalize(id) : name);
    }

    public static <T extends Enchantment> void languageEnchantment(Supplier<T> supplier, String id)
    {
        PlusLanguage.ENCHANTMENTS.put(supplier, PlusDataGenHelperCommon.capitalize(id));
    }

    public static void languageExtra(String key, String value)
    {
        PlusLanguage.EXTRA.put(key, value);
    }
}
