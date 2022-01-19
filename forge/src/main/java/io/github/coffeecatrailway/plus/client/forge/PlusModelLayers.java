package io.github.coffeecatrailway.plus.client.forge;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.client.entity.AmuletModel;
import io.github.coffeecatrailway.plus.client.entity.FoxHatModel;
import io.github.coffeecatrailway.plus.common.item.FoxHatItem;
import io.github.coffeecatrailway.plus.common.item.WarmthAmuletItem;
import io.github.coffeecatrailway.plus.common.item.forge.FoxHatItemForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Plus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlusModelLayers
{
    @SubscribeEvent
    public static void initLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(FoxHatItemForge.LAYER, FoxHatModel::createBodyLayer);
        event.registerLayerDefinition(WarmthAmuletItem.LAYER, AmuletModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void initModels(EntityRenderersEvent.AddLayers event)
    {
//        FoxHatItemForge.MODEL = new FoxHatModel(event.getEntityModels().bakeLayer(FoxHatItemForge.LAYER));
//        WarmthAmuletItem.MODEL = new AmuletModel(event.getEntityModels().bakeLayer(WarmthAmuletItem.LAYER));
        FoxHatItem.getModel();
        WarmthAmuletItem.getModel();
    }
}
