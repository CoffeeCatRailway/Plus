package io.github.coffeecatrailway.plus.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import io.github.coffeecatrailway.plus.common.entity.PlayingCardEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

/**
 * @author CoffeeCatRailway
 * Created: 26/08/2022
 */
@Environment(EnvType.CLIENT)
public class PlayingCardEntityRenderer extends EntityRenderer<PlayingCardEntity>
{
    private final ItemRenderer itemRenderer;

    public PlayingCardEntityRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(PlayingCardEntity card, float rotation, float delta, PoseStack poseStack, MultiBufferSource buffer, int i)
    {
        if (card.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(card) < 12.25f))
        {
            poseStack.pushPose();
            poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            poseStack.mulPose(Vector3f.XP.rotationDegrees(90f));
            this.itemRenderer.renderStatic(card.getItem(), ItemTransforms.TransformType.GROUND, i, OverlayTexture.NO_OVERLAY, poseStack, buffer, card.getId());
            poseStack.popPose();
            super.render(card, rotation, delta, poseStack, buffer, i);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(PlayingCardEntity entity)
    {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
