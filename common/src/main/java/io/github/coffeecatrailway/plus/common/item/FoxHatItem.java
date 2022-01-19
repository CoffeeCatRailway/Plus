package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.client.entity.FoxHatModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;

import java.util.Objects;

/**
 * @author CoffeeCatRailway
 * Created: 2/01/2022
 */
public class FoxHatItem extends ArmorItem
{
    public static final ResourceLocation TEXTURE = Plus.getLocation("textures/models/armor/fox_hat.png");
    public static final ResourceLocation SNOW_TEXTURE = Plus.getLocation("textures/models/armor/snow_fox_hat.png");
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(Plus.getLocation("fox_hat"), "main");
    private static FoxHatModel model = null;

    public FoxHatItem(ArmorMaterial material, Properties properties)
    {
        super(material, EquipmentSlot.HEAD, properties.tab(CreativeModeTab.TAB_COMBAT));
    }

    @Environment(EnvType.CLIENT)
    public static FoxHatModel getModel()
    {
        if (model == null)
            model = new FoxHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(LAYER));
        return Objects.requireNonNull(model);
    }
}
