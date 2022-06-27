package io.github.coffeecatrailway.plus.client.forge;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.item.FoxHatItem;
import io.github.coffeecatrailway.plus.common.item.WarmthAmuletItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Plus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlusModelLayersForge
{
    @SubscribeEvent
    public static void initModels(EntityRenderersEvent.AddLayers event)
    {
//        FoxHatItemForge.MODEL = new FoxHatModel(event.getEntityModels().bakeLayer(FoxHatItemForge.LAYER));
//        WarmthAmuletItem.MODEL = new AmuletModel(event.getEntityModels().bakeLayer(WarmthAmuletItem.LAYER));
        FoxHatItem.getModel();
        WarmthAmuletItem.getModel();
    }
}
