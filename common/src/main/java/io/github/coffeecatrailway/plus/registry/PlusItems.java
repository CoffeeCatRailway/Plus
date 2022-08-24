package io.github.coffeecatrailway.plus.registry;

import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import dev.architectury.injectables.annotations.ExpectPlatform;
import gg.moonflower.pollen.api.item.TabInsertItem;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.item.*;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.core.Registry;
import net.minecraft.world.item.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
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

    public static final Supplier<Item> FOX_MEAT = registerWithName("fox_meat", "Raw Fox", prop -> new Item(prop.food(PlusFoods.FOX_MEAT).tab(CreativeModeTab.TAB_FOOD)));
    public static final Supplier<Item> COOKED_FOX_MEAT = registerWithName("cooked_fox_meat", "Cooked Fox", prop -> new Item(prop.food(PlusFoods.COOKED_FOX_MEAT).tab(CreativeModeTab.TAB_FOOD)));

    public static final Supplier<DescribedItem> CHAIN_LINK = registerWithDescription("chain_link", prop -> new DescribedItem(Items.CHAIN, prop.tab(CreativeModeTab.TAB_DECORATIONS)), "Used to make chainmail & maybe something else...");

    public static final Supplier<Item> BAT = registerWithName("bat", "Raw Bat", prop -> new Item(prop.food(PlusFoods.BAT).tab(CreativeModeTab.TAB_FOOD)));
    public static final Supplier<Item> COOKED_BAT = registerWithName("cooked_bat", "Cooked Bat", prop -> new Item(prop.food(PlusFoods.COOKED_BAT).tab(CreativeModeTab.TAB_FOOD)));

    public static final Supplier<Item> SQUID = registerWithName("squid", "Raw Calamari Ring", prop -> new Item(prop.food(PlusFoods.SQUID).tab(CreativeModeTab.TAB_FOOD)));
    public static final Supplier<Item> CALAMARI = registerWithName("calamari", "Calamari Ring", prop -> new Item(prop.food(PlusFoods.CALAMARI).tab(CreativeModeTab.TAB_FOOD)));

    public static final Supplier<Item> TURTLE = registerIdAsName("turtle", prop -> new Item(prop.food(PlusFoods.TURTLE).tab(CreativeModeTab.TAB_FOOD)));
    public static final Supplier<Item> COOKED_TURTLE = registerIdAsName("cooked_turtle", prop -> new Item(prop.food(PlusFoods.COOKED_TURTLE).tab(CreativeModeTab.TAB_FOOD)));

    public static final Supplier<DescribedItem> STINGER = registerWithDescription("stinger", prop -> new DescribedItem(Items.RABBIT_FOOT, prop.stacksTo(32).tab(CreativeModeTab.TAB_BREWING)), "A poisonous stinger");

    public static final Supplier<DescribedItem> WARMTH_CRYSTAL = registerWithDescription("warmth_crystal", prop -> new DescribedItem(Items.AMETHYST_SHARD, prop.tab(CreativeModeTab.TAB_MISC)), "Maybe it's heat can be used?");

    // Vanity
    public static final Supplier<FoxHatItem> FOX_HAT = registerIdAsName("fox_hat", getFoxHatItem());
    public static final Supplier<FoxHatItem> SNOW_FOX_HAT = registerIdAsName("snow_fox_hat", getSnowFoxHatItem());

    public static final Supplier<WarmthAmuletItem> WARMTH_AMULET = registerIdAsName("warmth_amulet", getWarmthAmuletItem());

    // Tools/Weapons
    public static final Supplier<PlusShieldItem> WOODEN_SHIELD = registerIdAsName("wooden_shield", prop -> new PlusShieldItem(prop, Tiers.WOOD, Plus.WOODEN_SHIELD_BASE, Plus.WOODEN_SHIELD_NO_PATTERN));
    public static final Supplier<PlusShieldItem> STONE_SHIELD = registerIdAsName("stone_shield", prop -> new PlusShieldItem(prop, Tiers.STONE, Plus.STONE_SHIELD_BASE, Plus.STONE_SHIELD_NO_PATTERN));
    public static final Supplier<PlusShieldItem> GOLD_SHIELD = registerIdAsName("gold_shield", prop -> new PlusShieldItem(prop, Tiers.GOLD, Plus.GOLD_SHIELD_BASE, Plus.GOLD_SHIELD_NO_PATTERN));
    public static final Supplier<PlusShieldItem> DIAMOND_SHIELD = registerIdAsName("diamond_shield", prop -> new PlusShieldItem(prop, Tiers.DIAMOND, Plus.DIAMOND_SHIELD_BASE, Plus.DIAMOND_SHIELD_NO_PATTERN));
    public static final Supplier<PlusShieldItem> NETHERITE_SHIELD = registerIdAsName("netherite_shield", prop -> new PlusShieldItem(prop, Tiers.NETHERITE, Plus.NETHERITE_SHIELD_BASE, Plus.NETHERITE_SHIELD_NO_PATTERN));
    public static final Supplier<PlusShieldItem> ROSE_GOLD_SHIELD = registerIdAsName("rose_gold_shield", prop -> new PlusShieldItem(prop, PlusTiers.ROSE_GOLD, Plus.ROSE_GOLD_SHIELD_BASE, Plus.ROSE_GOLD_SHIELD_NO_PATTERN));

    // Rose Gold
    public static final Supplier<Item> RAW_ROSE_GOLD = registerIdAsName("raw_rose_gold", prop -> new TabInsertItem(Items.GOLD_INGOT, prop.tab(CreativeModeTab.TAB_MISC)));
    public static final Supplier<Item> ROSE_GOLD_INGOT = registerIdAsName("rose_gold_ingot", prop -> new TabInsertItem(RAW_ROSE_GOLD.get(), prop.tab(CreativeModeTab.TAB_MISC)));
    public static final Supplier<Item> ROSE_GOLD_NUGGET = registerIdAsName("rose_gold_nugget", prop -> new Item(prop.tab(CreativeModeTab.TAB_MISC)));
    public static final Supplier<SwordItem> ROSE_GOLD_SWORD = registerIdAsName("rose_gold_sword", prop -> new SwordItem(PlusTiers.ROSE_GOLD, 3, -2.4f, prop.tab(CreativeModeTab.TAB_COMBAT)));
    public static final Supplier<ShovelItem> ROSE_GOLD_SHOVEL = registerIdAsName("rose_gold_shovel", prop -> new ShovelItem(PlusTiers.ROSE_GOLD, 1.5f, -3f, prop.tab(CreativeModeTab.TAB_TOOLS)));
    public static final Supplier<PickaxeItem> ROSE_GOLD_PICKAXE = registerIdAsName("rose_gold_pickaxe", prop -> new PlusPickaxeItem(PlusTiers.ROSE_GOLD, 1, -2.8f, prop.tab(CreativeModeTab.TAB_TOOLS)));
    public static final Supplier<AxeItem> ROSE_GOLD_AXE = registerIdAsName("rose_gold_axe", prop -> new PlusAxeItem(PlusTiers.ROSE_GOLD, 6f, -3f, prop.tab(CreativeModeTab.TAB_TOOLS)));
    public static final Supplier<HoeItem> ROSE_GOLD_HOE = registerIdAsName("rose_gold_hoe", prop -> new PlusHoeItem(PlusTiers.ROSE_GOLD, 0, -3f, prop.tab(CreativeModeTab.TAB_TOOLS)));

    // Cards
    public static final Supplier<PlayingCardItem> CARD_JOKER_BLACK = registerWithName("playing_card_joker_black", "Black Joker", prop -> new PlayingCardItem(prop.stacksTo(54).tab(CreativeModeTab.TAB_MISC), null, PlayingCardItem.Number.JOKER));
    public static final Supplier<PlayingCardItem> CARD_JOKER_RED = registerWithName("playing_card_joker_red", "Red Joker", prop -> new PlayingCardItem(prop.stacksTo(54).tab(CreativeModeTab.TAB_MISC), null, PlayingCardItem.Number.JOKER));
    public static final Table<PlayingCardItem.Suit, PlayingCardItem.Number, Supplier<PlayingCardItem>> PLAYING_CARDS = Tables.newCustomTable(new HashMap<>(), HashMap::new);
    static
    {
        for (PlayingCardItem.Suit suit : PlayingCardItem.Suit.values())
        {
            for (PlayingCardItem.Number number : PlayingCardItem.Number.values())
            {
                if (number.equals(PlayingCardItem.Number.JOKER))
                    continue;
                String name = PlusLanguage.capitalize(number.getName()) + " of " + PlusLanguage.capitalize(suit.getName());
                PLAYING_CARDS.put(suit, number, registerWithName("playing_card_" + suit.getName() + "_" + number.getName(), name, prop -> new PlayingCardItem(prop.stacksTo(54).tab(CreativeModeTab.TAB_MISC), suit, number)));
            }
        }
    }

    protected static <T extends DescribedItem> Supplier<T> registerWithDescription(String id, Function<Item.Properties, T> factory, String description)
    {
        PlusLanguage.EXTRA.put("item." + Plus.MOD_ID + "." + id + ".description", description);
        return registerIdAsName(id, factory);
    }

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
            PlusLanguage.ITEMS.put(object, name == null ? PlusLanguage.capitalize(id) : name);
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

    @ExpectPlatform
    private static Function<Item.Properties, WarmthAmuletItem> getWarmthAmuletItem()
    {
        throw new AssertionError();
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        ITEMS.register(platform);
    }
}
