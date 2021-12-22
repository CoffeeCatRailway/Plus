package io.github.coffeecatrailway.plus.datagen;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 20/12/2021
 */
public class PlusLanguageHandler
{
    @ExpectPlatform
    public static <T extends Item> void items(RegistrySupplier<T> supplier, String id, @Nullable String name)
    {
        throw new AssertionError();
    }
}
