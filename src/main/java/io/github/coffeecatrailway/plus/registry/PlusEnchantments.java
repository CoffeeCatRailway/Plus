package io.github.coffeecatrailway.plus.registry;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.enchantment.HeatWalkerEnchantment;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 1/02/2021
 */
public class PlusEnchantments
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Plus.MOD_ID);

    public static final RegistryObject<HeatWalkerEnchantment> HEAT_WALKER = register("heat_walker", () -> new HeatWalkerEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.FEET));

    private static <T extends Enchantment> RegistryObject<T> register(String id, Supplier<T> enchantment)
    {
        RegistryObject<T> object = ENCHANTMENTS.register(id, enchantment);
        PlusLanguage.ENCHANTMENTS.put(object, PlusLanguage.capitalize(id));
        return object;
    }

    public static void load(IEventBus bus)
    {
        LOGGER.debug("Loaded");
        ENCHANTMENTS.register(bus);
    }
}
