package io.github.coffeecatrailway.plus.common.entity;

import io.github.coffeecatrailway.plus.common.item.PlayingCardItem;
import io.github.coffeecatrailway.plus.registry.PlusEntities;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import io.github.coffeecatrailway.plus.registry.PlusParticles;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * @author CoffeeCatRailway
 * Created: 25/08/2022
 */
public class PlayingCardEntity extends ThrowableItemProjectile
{
    private final PlayingCardItem cardItem;

    public PlayingCardEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level)
    {
        super(entityType, level);
        this.cardItem = PlusItems.CARD_JOKER_BLACK.get();
    }

    public PlayingCardEntity(Level level, LivingEntity livingEntity, PlayingCardItem cardItem)
    {
        super(PlusEntities.PLAYING_CARD.get(), livingEntity, level);
        this.cardItem = cardItem;
    }

    @Override
    protected Item getDefaultItem()
    {
        return this.cardItem;
    }

    private ParticleOptions getParticle()
    {
        ItemStack itemStack = this.getItemRaw();
        return itemStack.isEmpty() ? PlusParticles.ITEM_PLAYING_CARD.get() : new ItemParticleOption(ParticleTypes.ITEM, itemStack);
    }

    @Override
    public void handleEntityEvent(byte b)
    {
        if (b == 3)
        {
            if (this.level.isClientSide)
            {
                ParticleOptions particleOptions = this.getParticle();
                for (int i = 0; i < 8; i++)
                    this.level.addParticle(particleOptions, this.getX(), this.getY(), this.getZ(), 0d, 0d, 0d);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult)
    {
        super.onHitEntity(entityHitResult);
        entityHitResult.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), this.random.nextFloat(1f, Mth.clamp(this.cardItem.getNumber().getNumerical(), 1.5f, 8f)));
    }

    @Override
    protected void onHit(HitResult hitResult)
    {
        super.onHit(hitResult);
        if (!this.level.isClientSide)
        {
            this.level.broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }
}
