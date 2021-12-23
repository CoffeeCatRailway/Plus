package io.github.coffeecatrailway.plus;

import io.github.coffeecatrailway.plus.registry.PlusItems;
import io.github.coffeecatrailway.plus.util.PlusDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * @author CoffeeCatRailway
 * Created: 15/04/2021
 */
@Mod.EventBusSubscriber(modid = Plus.MOD_ID)
public class CommonEvents
{
    public static void init(final FMLCommonSetupEvent event)
    {
        PotionBrewing.addMix(Potions.AWKWARD, PlusItems.STINGER.get(), Potions.POISON);
    }

    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingUpdateEvent event)
    {
        LivingEntity entity = event.getEntityLiving();
        BlockState state = entity.level.getBlockState(entity.blockPosition());
        if (!state.is(Blocks.STONECUTTER))
            return;
        entity.hurt(PlusDamageSource.SAW_BLADE, (float) (PlusConfig.SERVER.sawBladeDamage.get() * 1.5f));
    }
}
