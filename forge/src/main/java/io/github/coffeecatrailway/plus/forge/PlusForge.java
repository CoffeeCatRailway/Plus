package io.github.coffeecatrailway.plus.forge;

import dev.architectury.platform.forge.EventBuses;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.client.forge.ClientEvents;
import io.github.coffeecatrailway.plus.datagen.forge.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

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
        EventBuses.registerModEventBus(Plus.MOD_ID, modBus);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(ClientEvents::init));
        modBus.addListener(CommonEvents::init);
        modBus.addListener(this::onGatherData);

        MinecraftForge.EVENT_BUS.register(this);
        Plus.init();
    }

    private void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PlusBlockTags blockTags = new PlusBlockTags(generator, existingFileHelper);
        generator.addProvider(new PlusItemTags(generator, blockTags, existingFileHelper));
        generator.addProvider(blockTags);
        generator.addProvider(new PlusLootTables(generator));
        generator.addProvider(new PlusItemModels(generator));
        generator.addProvider(new PlusBlockStates(generator, existingFileHelper));
        generator.addProvider(new PlusLanguage(generator));
        generator.addProvider(new PlusLootModifierProvider(generator));
        generator.addProvider(new PlusRecipeProvider(generator));
    }
}
