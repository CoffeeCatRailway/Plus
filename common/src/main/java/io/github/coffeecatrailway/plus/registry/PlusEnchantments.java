package io.github.coffeecatrailway.plus.registry;

import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.enchantment.HeatWalkerEnchantment;
import io.github.coffeecatrailway.plus.data.gen.PlusDataGenHelper;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 6/01/2022
 */
public class PlusEnchantments
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final PollinatedRegistry<Enchantment> ENCHANTMENTS = PollinatedRegistry.create(Registry.ENCHANTMENT, Plus.MOD_ID);

    public static final Supplier<HeatWalkerEnchantment> HEAT_WALKER = register("heat_walker", () -> new HeatWalkerEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.FEET));

    private static <T extends Enchantment> Supplier<T> register(String id, Supplier<T> enchantment)
    {
        Supplier<T> object = ENCHANTMENTS.register(id, enchantment);
        PlusDataGenHelper.languageEnchantment(object, id);
        return object;
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        ENCHANTMENTS.register(platform);
    }
}
