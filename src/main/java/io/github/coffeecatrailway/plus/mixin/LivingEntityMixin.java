package io.github.coffeecatrailway.plus.mixin;

import io.github.coffeecatrailway.plus.common.enchantment.HeatWalkerEnchantment;
import io.github.coffeecatrailway.plus.registry.PlusEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author CoffeeCatRailway
 * Created: 22/09/2021
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements IForgeEntity
{
    public LivingEntityMixin(EntityType<?> entityType, Level level)
    {
        super(entityType, level);
    }

    @Inject(method = "onChangedBlock(Lnet/minecraft/core/BlockPos;)V", at = @At("HEAD"))
    protected void onChangedBlock(BlockPos pos, CallbackInfo callback)
    {
        int level = EnchantmentHelper.getEnchantmentLevel(PlusEnchantments.HEAT_WALKER.get(), (LivingEntity) this.getSelf());
        if (level > 0)
            HeatWalkerEnchantment.onEntityMoved((LivingEntity) this.getSelf(), this.level, pos, level);
    }

    @Unique
    private Entity getSelf()
    {
        return this;
    }
}
