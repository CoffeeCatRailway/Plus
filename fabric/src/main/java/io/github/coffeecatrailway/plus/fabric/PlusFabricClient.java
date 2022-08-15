package io.github.coffeecatrailway.plus.fabric;

import gg.moonflower.pollen.api.platform.Platform;
import io.github.coffeecatrailway.plus.client.entity.AmuletModel;
import io.github.coffeecatrailway.plus.client.entity.FoxHatModel;
import io.github.coffeecatrailway.plus.common.item.FoxHatItem;
import io.github.coffeecatrailway.plus.common.item.WarmthAmuletItem;
import io.github.coffeecatrailway.plus.common.item.fabric.WarmthAmuletItemTrinket;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;

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
        ArmorRenderer.register((matrices, vertexConsumers, stack, entity, slot, light, contextModel) -> {
            FoxHatModel model = FoxHatItem.getModel();
            contextModel.copyPropertiesTo(model);
            model.setAllVisible(true);
            if (stack.getItem().equals(PlusItems.FOX_HAT.get()))
                ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, FoxHatItem.TEXTURE);
            else if (stack.getItem().equals(PlusItems.SNOW_FOX_HAT.get()))
                ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, FoxHatItem.SNOW_TEXTURE);
        }, PlusItems.FOX_HAT.get(), PlusItems.SNOW_FOX_HAT.get());

        ArmorRenderer.register((matrices, vertexConsumers, stack, entity, slot, light, contextModel) -> {
            AmuletModel model = WarmthAmuletItem.getModel();
            model.setCurrentEntity(entity);
            contextModel.copyPropertiesTo(model);
            model.setAllVisible(true);
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, WarmthAmuletItem.TEXTURE);
        }, PlusItems.WARMTH_AMULET.get());
        if (Platform.isModLoaded("trinkets"))
            WarmthAmuletItemTrinket.registerRenderer();
    }
}
