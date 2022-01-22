package io.github.coffeecatrailway.plus.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.Entity;

/**
 * @author CoffeeCatRailway
 * Created: 22/01/2022
 */
public class MixinExpectPlatforms
{
    @ExpectPlatform
    public static boolean hasWarmthAmulet(Entity entity)
    {
        throw new AssertionError();
    }
}
