package io.github.coffeecatrailway.plus.util.fabric;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.Optional;

/**
 * @author CoffeeCatRailway
 * Created: 22/01/2022
 */
public class MixinExpectPlatformsImpl
{
    public static boolean hasWarmthAmulet(Entity entity)
    {
        if (!(entity instanceof LivingEntity))
            return false;
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent((LivingEntity) entity);
        return component.isPresent() && component.get().isEquipped(PlusItems.WARMTH_AMULET.get());
    }
}
