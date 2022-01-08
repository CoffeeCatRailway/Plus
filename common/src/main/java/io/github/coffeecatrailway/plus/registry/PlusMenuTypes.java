package io.github.coffeecatrailway.plus.registry;

import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.inventory.SawBenchMenu;
import io.github.coffeecatrailway.plus.data.gen.PlusDataGenHelper;
import io.github.coffeecatrailway.plus.data.gen.PlusDataGenHelperCommon;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 27/09/2021
 */
public class PlusMenuTypes
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final PollinatedRegistry<MenuType<?>> MENUS = PollinatedRegistry.create(Registry.MENU, Plus.MOD_ID);

    public static final Supplier<MenuType<SawBenchMenu>> SAW_BENCH = register("saw_bench", () -> new MenuType<>(SawBenchMenu::new));

    private static <T extends MenuType<?>> Supplier<T> register(String id, Supplier<T> menu)
    {
        Supplier<T> object = MENUS.register(id, menu);
        PlusDataGenHelper.languageExtra("container." + Plus.MOD_ID + "." + id, PlusDataGenHelperCommon.capitalize(id));
        return object;
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        MENUS.register(platform);
    }
}
