package io.github.coffeecatrailway.plus.common.enchantment.fabric;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

/**
 * @author CoffeeCatRailway
 * Created: 6/01/2022
 */
public class HeatWalkerEnchantmentImpl
{
    public static boolean onBlockPlace(LivingEntity entity, Level level, BlockPos pos)
    {
        return true;
    }
}
