package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.client.PlusClient;
import io.github.coffeecatrailway.plus.client.entity.model.AmuletModel;
import io.github.coffeecatrailway.plus.common.enchantment.HeatWalkerEnchantment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * @author CoffeeCatRailway
 * Created: 18/01/2022
 */
public class WarmthAmuletItem extends ArmorItem
{
    public static final ResourceLocation TEXTURE = Plus.getLocation("textures/models/amulet/warmth.png");
    private static AmuletModel MODEL = null;

    public WarmthAmuletItem(Properties properties)
    {
        super(PlusArmorMaterials.COSMETIC, EquipmentSlot.CHEST, properties.stacksTo(1).rarity(Rarity.UNCOMMON).tab(CreativeModeTab.TAB_TOOLS));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag)
    {
        list.add(new TranslatableComponent("item." + Plus.MOD_ID + ".warmth_amulet.use1").withStyle(ChatFormatting.GRAY));
        list.add(new TranslatableComponent("item." + Plus.MOD_ID + ".warmth_amulet.use2", Plus.CONFIG_SERVER.warmthAmuletSize.get()).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int index, boolean selected)
    {
        if (entity instanceof Player player && player.getInventory().armor.get(2).getItem() == this)
            this.tick((LivingEntity) entity);
    }

    protected void tick(LivingEntity entity)
    {
        if (!entity.isOnGround() || entity.isCrouching())
            return;
        BlockState iceState = Blocks.FROSTED_ICE.defaultBlockState();
        double size = Plus.CONFIG_SERVER.warmthAmuletSize.get();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        Level level = entity.level;
        BlockPos entityPos = entity.blockPosition();

        for (BlockPos pos : BlockPos.betweenClosed(entityPos.offset(-size, -1d, -size), entityPos.offset(size, -1d, size)))
        {
            if (pos.closerToCenterThan(entity.position(), size))
            {
                mutablePos.set(pos.getX(), pos.getY() + 1, pos.getZ());
                BlockState state = level.getBlockState(mutablePos);
                if (state.isAir())
                {
                    state = level.getBlockState(pos);
                    if (state.getBlock() == Blocks.ICE && iceState.canSurvive(level, pos) && level.isUnobstructed(iceState, pos, CollisionContext.empty()) && HeatWalkerEnchantment.onBlockPlace(entity, level, pos))
                    {
                        level.setBlockAndUpdate(pos, iceState);
                        level.scheduleTick(pos, Blocks.FROSTED_ICE, Mth.nextInt(entity.getRandom(), 30, 60));
                    }
                }
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static AmuletModel getModel()
    {
        if (MODEL == null)
            MODEL = new AmuletModel(Minecraft.getInstance().getEntityModels().bakeLayer(PlusClient.AMULET));
        return Objects.requireNonNull(MODEL);
    }
}
