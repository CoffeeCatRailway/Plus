package io.github.coffeecatrailway.plus.data.gen;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.Item;
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
}
