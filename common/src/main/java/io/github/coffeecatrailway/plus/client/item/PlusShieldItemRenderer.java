package io.github.coffeecatrailway.plus.client.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import gg.moonflower.pollen.api.client.render.DynamicItemRenderer;
import io.github.coffeecatrailway.plus.client.PlusModelLayers;
import io.github.coffeecatrailway.plus.common.item.PlusShieldItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 1/04/2021
 */
public class PlusShieldItemRenderer implements DynamicItemRenderer
{
    public static final PlusShieldItemRenderer INSTANCE = new PlusShieldItemRenderer();

    private ShieldModel model;

    public ShieldModel getModel()
    {
        if (this.model == null)
            this.model = new ShieldModel(Minecraft.getInstance().getEntityModels().bakeLayer(PlusModelLayers.SHIELD));
        return this.model;
    }

    @Override
    public void render(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack matrixStack, MultiBufferSource multiBufferSource, int packedLight, int combinedOverlay)
    {
        if (stack.getItem() instanceof PlusShieldItem shieldItem)
        {
            matrixStack.pushPose();
            boolean hasPattern = BlockItem.getBlockEntityData(stack) != null;
            matrixStack.scale(1f, -1f, -1f);
            Material material = hasPattern ? shieldItem.getPattern() : shieldItem.getNoPattern();
            VertexConsumer vertexConsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(multiBufferSource, this.getModel().renderType(material.atlasLocation()), true, stack.hasFoil()));
            this.getModel().handle().render(matrixStack, vertexConsumer, packedLight, combinedOverlay, 1f, 1f, 1f, 1f);
            if (hasPattern)
            {
                List<Pair<BannerPattern, DyeColor>> list = BannerBlockEntity.createPatterns(ShieldItem.getColor(stack), BannerBlockEntity.getItemPatterns(stack));
                BannerRenderer.renderPatterns(matrixStack, multiBufferSource, packedLight, combinedOverlay, this.getModel().plate(), material, false, list, stack.hasFoil());
            } else
                this.getModel().plate().render(matrixStack, vertexConsumer, packedLight, combinedOverlay, 1f, 1f, 1f, 1f);
            matrixStack.popPose();
        }
    }
}
