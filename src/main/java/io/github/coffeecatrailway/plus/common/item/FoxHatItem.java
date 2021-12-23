package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.client.PlusModelLayers;
import io.github.coffeecatrailway.plus.client.entity.FoxHatModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @author CoffeeCatRailway
 * Created: 26/09/2021
 */
public class FoxHatItem extends ArmorItem
{
    public FoxHatItem(ArmorMaterial material, Properties properties)
    {
        super(material, EquipmentSlot.HEAD, properties.tab(CreativeModeTab.TAB_COMBAT));
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type)
    {
        if (slot != EquipmentSlot.HEAD)
            return null;
        if (this.material == PlusArmorMaterials.FOX_HAT)
            return Plus.MOD_ID + ":textures/models/armor/fox_hat.png";
        else if (this.material == PlusArmorMaterials.SNOW_FOX_HAT)
            return Plus.MOD_ID + ":textures/models/armor/snow_fox_hat.png";
        else
            return null;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer)
    {
        consumer.accept(new IItemRenderProperties()
        {
            @Override
            public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlot armorSlot, A parent)
            {
                if (!stack.isEmpty())
                {
                    FoxHatModel hatModel = PlusModelLayers.FOX_HAT_MODEL;
                    hatModel.setAllVisible(true);
                    hatModel.crouching = parent.crouching;
                    hatModel.young = parent.young;
                    return (A) hatModel;
                }
                return null;
            }
        });
    }
}
