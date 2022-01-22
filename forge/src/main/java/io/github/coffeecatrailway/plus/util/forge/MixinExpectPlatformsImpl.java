package io.github.coffeecatrailway.plus.util.forge;

import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import top.theillusivec4.curios.api.CuriosApi;

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
        return CuriosApi.getCuriosHelper().findCurios((LivingEntity) entity, PlusItems.WARMTH_AMULET.get()).stream().anyMatch(slotResult -> slotResult.stack().getItem() == PlusItems.WARMTH_AMULET.get());
    }
}
