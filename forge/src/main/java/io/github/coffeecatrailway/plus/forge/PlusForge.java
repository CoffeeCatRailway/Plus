package io.github.coffeecatrailway.plus.forge;

import gg.moonflower.pollen.api.platform.Platform;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.item.PlusTiers;
import io.github.coffeecatrailway.plus.registry.forge.PlusLootModifiers;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

@Mod(Plus.MOD_ID)
public class PlusForge
{
    public PlusForge()
    {
        Plus.PLATFORM.setup();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::onClientSetup);
        bus.addListener(this::onCommonSetup);
        if (Platform.isModLoaded("curios"))
            bus.addListener(CuriosEvents::onModEnqueue);
        PlusLootModifiers.load(bus);
    }

    private void onClientSetup(final FMLClientSetupEvent event)
    {
        if (Platform.isModLoaded("curios"))
            CuriosEvents.onClientSetup(event);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event)
    {
        TierSortingRegistry.registerTier(PlusTiers.ROSE_GOLD, Plus.getLocation("rose_gold"), List.of(Tiers.IRON, Tiers.DIAMOND, Tiers.NETHERITE), List.of());
    }
}
