package io.github.coffeecatrailway.plus.client.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;

/**
 * @author CoffeeCatRailway
 * Created: 13/01/2022
 * Made with Blockbench 3.9.3
 */
public class AmuletModel extends HumanoidModel<LivingEntity>
{
    private LivingEntity currentEntity;

    public AmuletModel(ModelPart part)
    {
        super(part);
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
        PartDefinition partdefinition = meshdefinition.getRoot();

        // frame
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5f, -3f, -3f, 3f, 1f, 1f, new CubeDeformation(0f))
                .texOffs(0, 6).addBox(-1.5f, -7f, -3f, 3f, 1f, 1f, new CubeDeformation(0f))
                .texOffs(0, 2).addBox(-2.5f, -6f, -3f, 1f, 3f, 1f, new CubeDeformation(0f))
                .texOffs(4, 2).addBox(1.5f, -6f, -3f, 1f, 3f, 1f, new CubeDeformation(0f))
                .texOffs(8, 2).addBox(-1.5f, -6f, -3.25f, 3f, 3f, 1f, new CubeDeformation(0f)) // gem
                .texOffs(0, 8).addBox(-4f, -8f, -2f, 8f, 3f, 4f, new CubeDeformation(0f)), PartPose.offset(0f, 24f, 0f)); // chain

        return LayerDefinition.create(meshdefinition, 32, 16);
    }

    public void setCurrentEntity(LivingEntity entityLiving)
    {
        this.currentEntity = entityLiving;
    }

    @Override
    protected Iterable<ModelPart> headParts()
    {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts()
    {
        return ImmutableList.of(this.body);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        this.setAllVisible(true);
        poseStack.pushPose();
        if (this.currentEntity != null)
        {
            if (this.currentEntity.isCrouching())
                poseStack.translate(0d, .43d, .235d);
            else
                poseStack.translate(0d, .49d, 0d);
            this.followBodyRotations(this.currentEntity, this);
        }
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.popPose();
    }

    private void followBodyRotations(LivingEntity livingEntity, HumanoidModel<LivingEntity> model)
    {
        EntityRenderer<? super LivingEntity> render = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(livingEntity);
        if (render instanceof LivingEntityRenderer)
        {
            LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>> livingRenderer = (LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) render;
            EntityModel<LivingEntity> entityModel = livingRenderer.getModel();

            if (entityModel instanceof HumanoidModel)
            {
                HumanoidModel<LivingEntity> bipedModel = (HumanoidModel<LivingEntity>) entityModel;
                bipedModel.copyPropertiesTo(model);
            }
        }
    }
}