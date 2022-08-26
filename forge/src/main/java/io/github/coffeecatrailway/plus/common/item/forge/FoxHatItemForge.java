package io.github.coffeecatrailway.plus.common.item.forge;

import io.github.coffeecatrailway.plus.client.entity.model.FoxHatModel;
import io.github.coffeecatrailway.plus.common.item.FoxHatItem;
import io.github.coffeecatrailway.plus.common.item.PlusArmorMaterials;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2022
 */
public class FoxHatItemForge extends FoxHatItem
{
    public FoxHatItemForge(ArmorMaterial material, Properties properties)
    {
        super(material, properties);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type)
    {
        if (slot == EquipmentSlot.HEAD)
        {
            if (this.material == PlusArmorMaterials.FOX_HAT)
                return TEXTURE.toString();
            else if (this.material == PlusArmorMaterials.SNOW_FOX_HAT)
                return SNOW_TEXTURE.toString();
        }
        return null;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer)
    {
        consumer.accept(new IItemRenderProperties()
        {
            @Override
            public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlot armorSlot, HumanoidModel<?> parent)
            {
                if (!stack.isEmpty())
                {
                    FoxHatModel model = FoxHatItem.getModel();
                    model.setAllVisible(true);
                    model.crouching = parent.crouching;
                    model.young = parent.young;
                    return model;
                }
                return null;
            }
        });
    }
}
