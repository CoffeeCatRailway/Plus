package io.github.coffeecatrailway.plus.forge;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.item.PlusTiers;
import io.github.coffeecatrailway.plus.data.gen.forge.*;
import io.github.coffeecatrailway.plus.registry.forge.PlusLootModifiers;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.List;

@Mod(Plus.MOD_ID)
public class PlusForge
{
    public PlusForge()
    {
        Plus.PLATFORM.setup();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::onCommonSetup);
        bus.addListener(this::onGatherData);
        PlusLootModifiers.load(bus);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event)
    {
        TierSortingRegistry.registerTier(PlusTiers.ROSE_GOLD, Plus.getLocation("rose_gold"), List.of(Tiers.IRON, Tiers.DIAMOND, Tiers.NETHERITE), List.of());
    }

    private void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        generator.addProvider(new PlusLootTables(generator));
        generator.addProvider(new PlusBlockStates(generator, existingFileHelper));
        generator.addProvider(new PlusLootModifierProvider(generator));
        generator.addProvider(new PlusRecipeProvider(generator));
    }
}
