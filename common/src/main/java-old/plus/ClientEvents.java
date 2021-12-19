package io.github.coffeecatrailway.plus;

import io.github.coffeecatrailway.plus.client.gui.SawBenchScreen;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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
        event.enqueueWork(() -> {
            RenderType cutoutMipped = RenderType.cutoutMipped();

            ItemBlockRenderTypes.setRenderLayer(PlusBlocks.SAW_BENCH.get(), cutoutMipped);
        });
        MenuScreens.register(PlusMenuTypes.SAW_BENCH.get(), SawBenchScreen::new);
    }
}
