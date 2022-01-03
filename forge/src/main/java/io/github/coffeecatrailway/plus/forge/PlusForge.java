package io.github.coffeecatrailway.plus.forge;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.data.gen.forge.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(Plus.MOD_ID)
public class PlusForge
{
    public PlusForge()
    {
        Plus.PLATFORM.setup();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onGatherData);
    }

    private void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new PlusItemModels(generator));
        generator.addProvider(new PlusLanguage(generator));
        generator.addProvider(new PlusRecipeProvider(generator));
    }
}
