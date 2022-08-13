package io.github.coffeecatrailway.plus.common.entity.ai.goal;

import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;

/**
 * @author CoffeeCatRailway
 * Created: 12/08/2022
 */
public class FindGlowLanternGoal extends MoveToBlockGoal
{
    public FindGlowLanternGoal(GlowSquid squid)
    {
        super(squid, 1.2f, 12, 6);
    }

    @Override
    protected boolean isValidTarget(LevelReader levelReader, BlockPos pos)
    {
        return levelReader.getBlockState(pos).is(PlusBlocks.GLOW_LANTERN.get());
    }
}
