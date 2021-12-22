package io.github.coffeecatrailway.plus.forge;

import io.github.coffeecatrailway.plus.Plus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * @author CoffeeCatRailway
 * Created: 20/12/2021
 */
@Mod.EventBusSubscriber(modid = Plus.MOD_ID)
public class CommonEvents
{
    public static void init(final FMLCommonSetupEvent event)
    {
//        PotionBrewing.addMix(Potions.AWKWARD, PlusItems.STINGER.get(), Potions.POISON);
    }

//    @SubscribeEvent
//    public static void onPlayerTick(LivingEvent.LivingUpdateEvent event)
//    {
//        LivingEntity entity = event.getEntityLiving();
//        BlockState state = entity.level.getBlockState(entity.blockPosition());
//        if (!state.is(Blocks.STONECUTTER))
//            return;
//        entity.hurt(PlusDamageSource.SAW_BLADE, (float) (PlusConfig.SERVER.sawBladeDamage.get() * 1.5f));
//    }
}
