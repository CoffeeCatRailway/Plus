package io.github.coffeecatrailway.plus.registry.forge;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.loot.forge.PlusLootModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 25/09/2021
 */
public class PlusLootModifiers
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, Plus.MOD_ID);

    public static final RegistryObject<PlusLootModifier.Serializer> POOL_LOOT = LOOT_MODIFIERS.register("pool_loot", PlusLootModifier.Serializer::new);

    public static void load(IEventBus bus)
    {
        LOGGER.debug("Loaded");
        LOOT_MODIFIERS.register(bus);
    }
}
