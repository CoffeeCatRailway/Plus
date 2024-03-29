package io.github.coffeecatrailway.plus.common.item.forge;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.coffeecatrailway.plus.client.entity.model.AmuletModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

/**
 * @author CoffeeCatRailway
 * Created: 18/01/2022
 */
public class WarmthAmuletItemCurios extends WarmthAmuletItemForge implements ICurioItem, ICurioRenderer
{
    public WarmthAmuletItemCurios(Properties properties)
    {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack)
    {
        super.tick(slotContext.entity());
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack)
    {
        return true;
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        AmuletModel model = getModel();
        model.setCurrentEntity(slotContext.entity());
        VertexConsumer buffer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, model.renderType(TEXTURE), false, false);
        poseStack.scale(1.01f, 1.01f, 1.01f);
        model.renderToBuffer(poseStack, buffer, light, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
    }
}
