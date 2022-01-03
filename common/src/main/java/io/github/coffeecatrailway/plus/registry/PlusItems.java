package io.github.coffeecatrailway.plus.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.item.FoxHatItem;
import io.github.coffeecatrailway.plus.common.item.PlusArmorMaterials;
import io.github.coffeecatrailway.plus.data.gen.PlusDataGenHelper;
import net.minecraft.core.Registry;
import net.minecraft.world.item.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 2/01/2022
 */
public class PlusItems
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final PollinatedRegistry<Item> ITEMS = PollinatedRegistry.create(Registry.ITEM, Plus.MOD_ID);

    // Crafting Ingredients
    public static final Supplier<Item> FOX_FUR = registerIdAsName("fox_fur", prop -> new Item(prop.tab(CreativeModeTab.TAB_MISC)));
    public static final Supplier<Item> SNOW_FOX_FUR = registerIdAsName("snow_fox_fur", prop -> new Item(prop.tab(CreativeModeTab.TAB_MISC)));

    public static final Supplier<FoxHatItem> FOX_HAT = registerIdAsName("fox_hat", getFoxHatItem());
    public static final Supplier<FoxHatItem> SNOW_FOX_HAT = registerIdAsName("snow_fox_hat", getSnowFoxHatItem());

//    public static final Supplier<Item> FOX_MEAT = registerWithName("fox_meat", "Raw Fox", prop -> new Item(prop.food(PlusFoods.FOX_MEAT).tab(CreativeModeTab.TAB_FOOD)));
//    public static final Supplier<Item> COOKED_FOX_MEAT = registerWithName("cooked_fox_meat", "Cooked Fox", prop -> new Item(prop.food(PlusFoods.COOKED_FOX_MEAT).tab(CreativeModeTab.TAB_FOOD)));
//
//    public static final Supplier<Item> CHAIN_LINK = registerIdAsName("chain_link", prop -> new Item(prop.tab(CreativeModeTab.TAB_DECORATIONS)));
//
//    public static final Supplier<Item> BAT = registerWithName("bat", "Raw Bat", prop -> new Item(prop.food(PlusFoods.BAT).tab(CreativeModeTab.TAB_FOOD)));
//    public static final Supplier<Item> COOKED_BAT = registerWithName("cooked_bat", "Cooked Bat", prop -> new Item(prop.food(PlusFoods.COOKED_BAT).tab(CreativeModeTab.TAB_FOOD)));
//
//    public static final Supplier<Item> SQUID = registerWithName("squid", "Raw Calamari Ring", prop -> new Item(prop.food(PlusFoods.SQUID).tab(CreativeModeTab.TAB_FOOD)));
//    public static final Supplier<Item> CALAMARI = registerWithName("calamari", "Calamari Ring", prop -> new Item(prop.food(PlusFoods.CALAMARI).tab(CreativeModeTab.TAB_FOOD)));
//
//    public static final Supplier<Item> TURTLE = registerIdAsName("turtle", prop -> new Item(prop.food(PlusFoods.TURTLE).tab(CreativeModeTab.TAB_FOOD)));
//    public static final Supplier<Item> COOKED_TURTLE = registerIdAsName("cooked_turtle", prop -> new Item(prop.food(PlusFoods.COOKED_TURTLE).tab(CreativeModeTab.TAB_FOOD)));
//
//    public static final Supplier<Item> STINGER = registerIdAsName("stinger", prop -> new Item(prop.stacksTo(32).tab(CreativeModeTab.TAB_MISC)));
//
//    // Rose Gold
//    public static final Supplier<Item> RAW_ROSE_GOLD = registerIdAsName("raw_rose_gold", prop -> new Item(prop.tab(CreativeModeTab.TAB_MATERIALS)));
//    public static final Supplier<Item> ROSE_GOLD_INGOT = registerIdAsName("rose_gold_ingot", prop -> new Item(prop.tab(CreativeModeTab.TAB_MATERIALS)));
//    public static final Supplier<Item> ROSE_GOLD_NUGGET = registerIdAsName("rose_gold_nugget", prop -> new Item(prop.tab(CreativeModeTab.TAB_MATERIALS)));
//    public static final Supplier<Item> ROSE_GOLD_SWORD = registerIdAsName("rose_gold_sword", prop -> new SwordItem(PlusTiers.ROSE_GOLD, 3, -2.4f, prop.tab(CreativeModeTab.TAB_COMBAT)));
//    public static final Supplier<Item> ROSE_GOLD_SHOVEL = registerIdAsName("rose_gold_shovel", prop -> new ShovelItem(PlusTiers.ROSE_GOLD, 1.5f, -3f, prop.tab(CreativeModeTab.TAB_TOOLS)));
//    public static final Supplier<Item> ROSE_GOLD_PICKAXE = registerIdAsName("rose_gold_pickaxe", prop -> new PickaxeItem(PlusTiers.ROSE_GOLD, 1, -2.8f, prop.tab(CreativeModeTab.TAB_TOOLS)));
//    public static final Supplier<Item> ROSE_GOLD_AXE = registerIdAsName("rose_gold_axe", prop -> new AxeItem(PlusTiers.ROSE_GOLD, 6f, -3f, prop.tab(CreativeModeTab.TAB_TOOLS)));
//    public static final Supplier<Item> ROSE_GOLD_HOE = registerIdAsName("rose_gold_hoe", prop -> new HoeItem(PlusTiers.ROSE_GOLD, 0, -3f, prop.tab(CreativeModeTab.TAB_TOOLS)));

    protected static <T extends Item> Supplier<T> registerIdAsName(String id, Function<Item.Properties, T> factory)
    {
        return registerWithName(id, null, factory);
    }

    private static <T extends Item> Supplier<T> registerWithName(String id, @Nullable String name, Function<Item.Properties, T> factory)
    {
        return register(id, name, true, factory);
    }

    private static <T extends Item> Supplier<T> register(String id, @Nullable String name, boolean addLang, Function<Item.Properties, T> factory)
    {
        Supplier<T> object = ITEMS.register(id, () -> factory.apply(new Item.Properties()));
        if (addLang)
            PlusDataGenHelper.languageItem(object, id, name);
        return object;
    }

    @ExpectPlatform
    private static Function<Item.Properties, FoxHatItem> getFoxHatItem()
    {
        throw new AssertionError();
    }

    @ExpectPlatform
    private static Function<Item.Properties, FoxHatItem> getSnowFoxHatItem()
    {
        throw new AssertionError();
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        ITEMS.register(platform);
    }
}
