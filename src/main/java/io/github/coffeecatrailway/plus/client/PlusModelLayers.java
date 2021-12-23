package io.github.coffeecatrailway.plus.client;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.client.entity.FoxHatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author CoffeeCatRailway
 * Created: 23/12/2021
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Plus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlusModelLayers
{
    public static final ModelLayerLocation FOX_HAT_LAYER = new ModelLayerLocation(Plus.getLocation("fox_hat_model"), "main");

    public static FoxHatModel FOX_HAT_MODEL;

    @SubscribeEvent
    public static void initLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(FOX_HAT_LAYER, FoxHatModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void initModels(EntityRenderersEvent.AddLayers event)
    {
        FOX_HAT_MODEL = new FoxHatModel(event.getEntityModels().bakeLayer(FOX_HAT_LAYER));
    }
}
