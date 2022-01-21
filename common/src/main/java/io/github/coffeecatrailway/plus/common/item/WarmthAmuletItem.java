package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.client.entity.AmuletModel;
import io.github.coffeecatrailway.plus.common.block.BrittleBasaltBlock;
import io.github.coffeecatrailway.plus.common.enchantment.HeatWalkerEnchantment;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;

import java.util.Objects;

/**
 * @author CoffeeCatRailway
 * Created: 18/01/2022
 */
public class WarmthAmuletItem extends Item
{
    public static final ResourceLocation TEXTURE = Plus.getLocation("textures/models/amulet/warmth.png");
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(Plus.getLocation("amulet"), "main");
    private static AmuletModel model = null;

    public WarmthAmuletItem(Properties properties)
    {
        super(properties.stacksTo(1).rarity(Rarity.UNCOMMON).tab(CreativeModeTab.TAB_COMBAT));
    }

    protected void tick(LivingEntity entity)
    {
        if (!entity.isOnGround() || entity.isCrouching())
            return;
        BlockState iceState = Blocks.FROSTED_ICE.defaultBlockState();
        double size = Plus.CONFIG_SERVER.items.warmthAmuletSize;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        Level level = entity.level;
        BlockPos entityPos = entity.blockPosition();

        for (BlockPos pos : BlockPos.betweenClosed(entityPos.offset(-size, -1d, -size), entityPos.offset(size, -1d, size)))
        {
            if (pos.closerThan(entity.position(), size))
            {
                mutablePos.set(pos.getX(), pos.getY() + 1, pos.getZ());
                BlockState state = level.getBlockState(mutablePos);
                if (state.isAir())
                {
                    state = level.getBlockState(pos);
                    if (state.getBlock() == Blocks.ICE && iceState.canSurvive(level, pos) && level.isUnobstructed(iceState, pos, CollisionContext.empty()) && HeatWalkerEnchantment.onBlockPlace(entity, level, pos))
                    {
                        level.setBlockAndUpdate(pos, iceState);
                        level.scheduleTick(pos, Blocks.FROSTED_ICE, Mth.nextInt(entity.getRandom(), 60, 90));
                    }
                }
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static AmuletModel getModel()
    {
        if (model == null)
            model = new AmuletModel(Minecraft.getInstance().getEntityModels().bakeLayer(LAYER));
        return Objects.requireNonNull(model);
    }
}
