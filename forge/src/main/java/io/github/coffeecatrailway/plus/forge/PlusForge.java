package io.github.coffeecatrailway.plus.forge;

import io.github.coffeecatrailway.plus.Plus;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * @author CoffeeCatRailway
 * Created: 19/12/2021
 */
@Mod(Plus.MOD_ID)
public class PlusForge
{
    public PlusForge()
    {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener(this::setupClient);
        modBus.addListener(this::setupCommon);

        MinecraftForge.EVENT_BUS.register(this);
        Plus.init();
    }

    private void setupClient(final FMLClientSetupEvent event)
    {

    }

    private void setupCommon(final FMLCommonSetupEvent event)
    {

    }
}
