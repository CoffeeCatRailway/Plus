package io.github.coffeecatrailway.plus.fabric;

import io.github.coffeecatrailway.plus.client.entity.FoxHatModel;
import io.github.coffeecatrailway.plus.common.item.FoxHatItem;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.Minecraft;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
@Environment(EnvType.CLIENT)
public class PlusFabricClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        EntityModelLayerRegistry.registerModelLayer(FoxHatItem.LAYER, FoxHatModel::createBodyLayer);
        ArmorRenderer.register((matrices, vertexConsumers, stack, entity, slot, light, contextModel) -> {
            if (FoxHatItem.MODEL == null)
                FoxHatItem.MODEL = new FoxHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(FoxHatItem.LAYER));
            contextModel.copyPropertiesTo(FoxHatItem.MODEL);
            FoxHatItem.MODEL.setAllVisible(true);
            if (stack.getItem().equals(PlusItems.FOX_HAT.get()))
                ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, FoxHatItem.MODEL, FoxHatItem.TEXTURE);
            else if (stack.getItem().equals(PlusItems.SNOW_FOX_HAT.get()))
                ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, FoxHatItem.MODEL, FoxHatItem.SNOW_TEXTURE);
        }, PlusItems.FOX_HAT.get(), PlusItems.SNOW_FOX_HAT.get());
    }
}
