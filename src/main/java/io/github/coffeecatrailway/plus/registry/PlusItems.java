package io.github.coffeecatrailway.plus.registry;

import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.common.item.FoxHatItem;
import io.github.coffeecatrailway.plus.common.item.PlusArmorMaterials;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 1/02/2021
 */
public class PlusItems
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PlusMod.MOD_ID);

    // Crafting Ingredients
    public static final RegistryObject<Item> FOX_FUR = registerIdAsName("fox_fur", prop -> new Item(prop.tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SNOW_FOX_FUR = registerIdAsName("snow_fox_fur", prop -> new Item(prop.tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<FoxHatItem> FOX_HAT = registerIdAsName("fox_hat", prop -> new FoxHatItem(PlusArmorMaterials.FOX_HAT, prop));
    public static final RegistryObject<FoxHatItem> SNOW_FOX_HAT = registerIdAsName("snow_fox_hat", prop -> new FoxHatItem(PlusArmorMaterials.SNOW_FOX_HAT, prop));

    public static final RegistryObject<Item> FOX_MEAT = registerWithName("fox_meat", "Raw Fox", prop -> new Item(prop.food(PlusFoods.FOX_MEAT).tab(CreativeModeTab.TAB_FOOD)));
    public static final RegistryObject<Item> COOKED_FOX_MEAT = registerWithName("cooked_fox_meat", "Cooked Fox", prop -> new Item(prop.food(PlusFoods.COOKED_FOX_MEAT).tab(CreativeModeTab.TAB_FOOD)));

    protected static <T extends Item> RegistryObject<T> registerIdAsName(String id, Function<Item.Properties, T> factory)
    {
        return registerWithName(id, null, factory);
    }

    private static <T extends Item> RegistryObject<T> registerWithName(String id, @Nullable String name, Function<Item.Properties, T> factory)
    {
        return register(id, name, true, factory);
    }

    private static <T extends Item> RegistryObject<T> register(String id, @Nullable String name, boolean addLang, Function<Item.Properties, T> factory)
    {
        RegistryObject<T> object = ITEMS.register(id, () -> factory.apply(new Item.Properties()));
        if (addLang)
            PlusLanguage.ITEMS.put(object, name == null ? PlusLanguage.capitalize(id) : name);
        return object;
    }

    public static void load(IEventBus bus)
    {
        LOGGER.debug("Loaded");
        ITEMS.register(bus);
    }
}
