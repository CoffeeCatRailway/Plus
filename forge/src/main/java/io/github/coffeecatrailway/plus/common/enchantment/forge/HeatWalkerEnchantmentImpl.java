package io.github.coffeecatrailway.plus.common.enchantment.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;

/**
 * @author CoffeeCatRailway
 * Created: 6/01/2022
 */
public class HeatWalkerEnchantmentImpl
{
    public static boolean onBlockPlace(LivingEntity entity, Level level, BlockPos pos)
    {
        return !ForgeEventFactory.onBlockPlace(entity, BlockSnapshot.create(level.dimension(), level, pos), Direction.UP);
    }
}
