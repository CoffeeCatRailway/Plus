package io.github.coffeecatrailway.plus.registry;

import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.common.loot.CookableFoodLootModifier;
import io.github.coffeecatrailway.plus.common.loot.FoxLootModifier;
import io.github.coffeecatrailway.plus.common.loot.StriderLootModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 25/09/2021
 */
public class PlusLootModifiers
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, PlusMod.MOD_ID);

    public static final RegistryObject<FoxLootModifier.Serializer> FOX = LOOT_MODIFIERS.register("fox", FoxLootModifier.Serializer::new);
    public static final RegistryObject<CookableFoodLootModifier.Serializer> COOKABLE = LOOT_MODIFIERS.register("cookable", CookableFoodLootModifier.Serializer::new);
    public static final RegistryObject<StriderLootModifier.Serializer> STRIDER = LOOT_MODIFIERS.register("strider", StriderLootModifier.Serializer::new);

    public static void load(IEventBus bus)
    {
        LOGGER.debug("Loaded");
        LOOT_MODIFIERS.register(bus);
    }
}
