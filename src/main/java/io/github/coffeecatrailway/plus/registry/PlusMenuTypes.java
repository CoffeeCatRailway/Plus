package io.github.coffeecatrailway.plus.registry;

import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.common.inventory.SawBenchMenu;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
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
    protected static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, PlusMod.MOD_ID);

    public static final RegistryObject<MenuType<SawBenchMenu>> SAW_BENCH = register("saw_bench", () -> new MenuType<>(SawBenchMenu::new));

    private static <T extends MenuType<?>> RegistryObject<T> register(String id, Supplier<T> menu)
    {
        RegistryObject<T> object = MENUS.register(id, menu);
        PlusLanguage.EXTRA.put("container." + PlusMod.MOD_ID + "." + id, PlusLanguage.capitalize(id));
        return object;
    }

    public static void load(IEventBus bus)
    {
        LOGGER.debug("Loaded");
        MENUS.register(bus);
    }
}
