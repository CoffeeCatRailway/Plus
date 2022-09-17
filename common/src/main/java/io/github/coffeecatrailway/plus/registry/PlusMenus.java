package io.github.coffeecatrailway.plus.registry;

import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.inventory.CardTableMenu;
import io.github.coffeecatrailway.plus.common.inventory.PlayingCardPackMenu;
import io.github.coffeecatrailway.plus.common.inventory.SawBenchMenu;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 27/09/2021
 */
public class PlusMenus
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final PollinatedRegistry<MenuType<?>> MENUS = PollinatedRegistry.create(Registry.MENU, Plus.MOD_ID);

    public static final Supplier<MenuType<SawBenchMenu>> SAW_BENCH = register("saw_bench", () -> new MenuType<>(SawBenchMenu::new));

    public static final Supplier<MenuType<PlayingCardPackMenu>> PLAYING_CARD_PACK = register("playing_card_pack", () -> new MenuType<>(PlayingCardPackMenu::new));
    public static final Supplier<MenuType<CardTableMenu>> CARD_TABLE = register("card_table", () -> new MenuType<>(CardTableMenu::new));

    private static <T extends MenuType<?>> Supplier<T> register(String id, Supplier<T> menu)
    {
        Supplier<T> object = MENUS.register(id, menu);
        PlusLanguage.EXTRA.put("container." + Plus.MOD_ID + "." + id, PlusLanguage.capitalize(id));
        return object;
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        MENUS.register(platform);
    }
}
