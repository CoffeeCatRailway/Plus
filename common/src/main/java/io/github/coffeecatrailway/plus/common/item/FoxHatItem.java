package io.github.coffeecatrailway.plus.common.item;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.client.entity.FoxHatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;

/**
 * @author CoffeeCatRailway
 * Created: 2/01/2022
 */
public class FoxHatItem extends ArmorItem
{
    public static final ResourceLocation TEXTURE = Plus.getLocation("textures/models/armor/fox_hat.png");
    public static final ResourceLocation SNOW_TEXTURE = Plus.getLocation("textures/models/armor/snow_fox_hat.png");
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(Plus.getLocation("fox_hat"), "main");
    public static FoxHatModel MODEL = null;

    public FoxHatItem(ArmorMaterial material, Properties properties)
    {
        super(material, EquipmentSlot.HEAD, properties.tab(CreativeModeTab.TAB_COMBAT));
    }
}
