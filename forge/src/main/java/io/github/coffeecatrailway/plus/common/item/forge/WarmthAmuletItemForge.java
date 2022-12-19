package io.github.coffeecatrailway.plus.common.item.forge;

import io.github.coffeecatrailway.plus.client.entity.model.AmuletModel;
import io.github.coffeecatrailway.plus.common.item.WarmthAmuletItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @author CoffeeCatRailway
 * Created: 15/08/2022
 */
public class WarmthAmuletItemForge extends WarmthAmuletItem
{
    public WarmthAmuletItemForge(Properties properties)
    {
        super(properties);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type)
    {
        if (slot == EquipmentSlot.CHEST)
            return TEXTURE.toString();
        return null;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer)
    {
        consumer.accept(new IItemRenderProperties()
        {
            @Nullable
            @Override
            public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlot armorSlot, HumanoidModel<?> parent)
            {
                if (!stack.isEmpty())
                {
                    AmuletModel model = getModel();
                    model.setCurrentEntity(entityLiving);
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
