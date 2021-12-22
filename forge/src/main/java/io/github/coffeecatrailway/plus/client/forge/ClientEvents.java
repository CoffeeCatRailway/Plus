package io.github.coffeecatrailway.plus.client.forge;

import io.github.coffeecatrailway.plus.Plus;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author CoffeeCatRailway
 * Created: 20/12/2021
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Plus.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents
{
    public static void init(final FMLClientSetupEvent event)
    {
        Plus.initClient();
    }
}
