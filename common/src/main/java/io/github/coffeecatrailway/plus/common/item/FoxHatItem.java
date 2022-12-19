package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.client.PlusClient;
import io.github.coffeecatrailway.plus.client.entity.model.FoxHatModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
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
    private static FoxHatModel MODEL = null;

    public FoxHatItem(ArmorMaterial material, Properties properties)
    {
        super(material, EquipmentSlot.HEAD, properties.tab(CreativeModeTab.TAB_COMBAT));
    }

    @Environment(EnvType.CLIENT)
    public static FoxHatModel getModel()
    {
        if (MODEL == null)
            MODEL = new FoxHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(PlusClient.FOX_HAT));
        return Objects.requireNonNull(MODEL);
    }
}
