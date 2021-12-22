package io.github.coffeecatrailway.plus.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.datagen.PlusLanguageHandler;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 20/12/2021
 */
public class PlusItems
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Plus.MOD_ID, Registry.ITEM_REGISTRY);

    // Crafting Ingredients
    public static final RegistrySupplier<Item> FOX_FUR = registerIdAsName("fox_fur", prop -> new Item(prop.tab(CreativeModeTab.TAB_MISC)));
    public static final RegistrySupplier<Item> SNOW_FOX_FUR = registerIdAsName("snow_fox_fur", prop -> new Item(prop.tab(CreativeModeTab.TAB_MISC)));

    protected static <T extends Item> RegistrySupplier<T> registerIdAsName(String id, Function<Item.Properties, T> factory)
    {
        return registerWithName(id, null, factory);
    }

    private static <T extends Item> RegistrySupplier<T> registerWithName(String id, @Nullable String name, Function<Item.Properties, T> factory)
    {
        return register(id, name, true, factory);
    }

    private static <T extends Item> RegistrySupplier<T> register(String id, @Nullable String name, boolean addLang, Function<Item.Properties, T> factory)
    {
        RegistrySupplier<T> supplier = ITEMS.register(id, () -> factory.apply(new Item.Properties()));
        if (addLang)
            PlusLanguageHandler.items(supplier, id, name);
        return supplier;
    }

    public static void init()
    {
        ITEMS.register();
        LOGGER.debug("Loaded");
    }
}
