package io.github.coffeecatrailway.plus;

import io.github.coffeecatrailway.plus.data.gen.*;
import io.github.coffeecatrailway.plus.registry.*;
import io.github.coffeecatrailway.plus.util.PlusDamageSource;
import io.github.ocelot.sonar.Sonar;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PlusMod.MOD_ID)
public class PlusMod
{
    public static final String MOD_ID = "plus";

    public PlusMod()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Sonar.init(bus);
        PlusConfig.Server.init(ModLoadingContext.get());
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(ClientEvents::init));
        bus.addListener(CommonEvents::init);
        bus.addListener(this::onGatherData);

        MinecraftForge.EVENT_BUS.register(this);

        PlusBlocks.load(bus);
        PlusItems.load(bus);
        PlusEnchantments.load(bus);
        PlusLootModifiers.load(bus);
        PlusMenuTypes.load(bus);
        PlusRecipes.load(bus);

        bus.addGenericListener(StatType.class, PlusStats::register);
        PlusDamageSource.load();
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

    public static ResourceLocation getLocation(String path)
    {
        return new ResourceLocation(PlusMod.MOD_ID, path);
    }
}
