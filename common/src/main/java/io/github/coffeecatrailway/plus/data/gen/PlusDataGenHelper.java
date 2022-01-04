package io.github.coffeecatrailway.plus.data.gen;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
public class PlusDataGenHelper
{
    @ExpectPlatform
    public static <T extends Item> void languageItem(Supplier<T> supplier, String id, @Nullable String name)
    {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Block> void languageBlock(Supplier<T> supplier, String id, @Nullable String name)
    {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void languageExtra(String key, String value)
    {
        throw new AssertionError();
    }
}
