package io.github.coffeecatrailway.plus.forge;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.item.PlusTiers;
import io.github.coffeecatrailway.plus.data.gen.forge.PlusBlockStates;
import io.github.coffeecatrailway.plus.data.gen.forge.PlusLootModifierProvider;
import io.github.coffeecatrailway.plus.data.gen.forge.PlusLootTables;
import io.github.coffeecatrailway.plus.data.gen.forge.PlusRecipeProvider;
import io.github.coffeecatrailway.plus.registry.forge.PlusLootModifiers;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import top.theillusivec4.curios.Curios;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;

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
        bus.addListener(this::onModEnqueue);
        bus.addListener(this::onGatherData);
        PlusLootModifiers.load(bus);
    }

    private void onClientSetup(final FMLClientSetupEvent event)
    {
    }

    private void onCommonSetup(final FMLCommonSetupEvent event)
    {
        TierSortingRegistry.registerTier(PlusTiers.ROSE_GOLD, Plus.getLocation("rose_gold"), List.of(Tiers.IRON, Tiers.DIAMOND, Tiers.NETHERITE), List.of());
    }

    private void onModEnqueue(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo(Curios.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
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
