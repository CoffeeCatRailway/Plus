package io.github.coffeecatrailway.plus.common.enchantment;

import io.github.coffeecatrailway.plus.PlusConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;

/**
 * @author CoffeeCatRailway
 * Created: 22/09/2021
 */
public class HeatWalkerEnchantment extends Enchantment
{
    public HeatWalkerEnchantment(Rarity rarity, EquipmentSlot... slots)
    {
        super(rarity, EnchantmentCategory.ARMOR_FEET, slots);
    }

    @Override
    public int getMinCost(int cost)
    {
        return cost * 10;
    }

    @Override
    public int getMaxCost(int cost)
    {
        return super.getMinCost(cost) + 15;
    }

    @Override
    public boolean isTreasureOnly()
    {
        return true;
    }

    @Override
    public int getMaxLevel()
    {
        return 2;
    }

    public static void onEntityMoved(LivingEntity entity, Level level, BlockPos entityPos, int enchantmentLevel)
    {
        if (!entity.isOnGround())
            return;
        BlockState basalt = Blocks.BASALT.defaultBlockState(); // TODO: Custom block
        double size = Math.min(16d, PlusConfig.SERVER.heatWalkerLevel.get() + enchantmentLevel);
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (BlockPos pos : BlockPos.betweenClosed(entityPos.offset(-size, -1d, -size), entityPos.offset(size, -1d, size)))
        {
            if (pos.closerThan(entity.position(), size))
            {
                mutablePos.set(pos.getX(), pos.getY() + 1, pos.getZ());
                BlockState state = level.getBlockState(mutablePos);
                if (state.isAir(level, mutablePos))
                {
                    state = level.getBlockState(pos);
                    boolean isFull = state.getBlock() == Blocks.LAVA && state.getValue(LiquidBlock.LEVEL) == 0;
                    if (state.getMaterial() == Material.LAVA && isFull && basalt.canSurvive(level, pos) && level.isUnobstructed(basalt, pos, CollisionContext.empty()) && !ForgeEventFactory.onBlockPlace(entity, BlockSnapshot.create(level.dimension(), level, pos), Direction.UP))
                    {
                        level.setBlockAndUpdate(pos, basalt);
                        level.getBlockTicks().scheduleTick(pos, Blocks.BASALT, Mth.nextInt(entity.getRandom(), 60, 120));
                    }
                }
            }
        }
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment)
    {
        return super.checkCompatibility(enchantment) && enchantment != Enchantments.DEPTH_STRIDER && enchantment != Enchantments.FROST_WALKER;
    }
}
