package io.github.coffeecatrailway.plus;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author CoffeeCatRailway
 * Created: 7/04/2021
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = PlusMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents
{
    public static void init(final FMLClientSetupEvent event)
    {
//        event.enqueueWork(() -> {
//
//        });
    }
}
