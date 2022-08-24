package io.github.coffeecatrailway.plus.forge;

import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.Curios;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;

/**
 * @author CoffeeCatRailway
 * Created: 24/08/2022
 */
public class CuriosEvents
{
    public static void onClientSetup(final FMLClientSetupEvent event)
    {
        CuriosRendererRegistry.register(PlusItems.WARMTH_AMULET.get(), () -> (ICurioRenderer) PlusItems.WARMTH_AMULET.get());
    }

    public static void onModEnqueue(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo(Curios.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
    }
}
