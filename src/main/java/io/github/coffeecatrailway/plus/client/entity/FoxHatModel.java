package io.github.coffeecatrailway.plus.client.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

/**
 * @author CoffeeCatRailway
 * Created: 26/09/2021
 * Made with Blockbench 3.9.3
 */
public class FoxHatModel extends HumanoidModel<LivingEntity>
{
    public FoxHatModel(ModelPart part)
    {
        super(part);
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
        PartDefinition partdefinition = meshdefinition.getRoot();

        float inflate = .2f;
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5f, -8.5f, -4.7f, 9f, 9f, 9f, new CubeDeformation(inflate))
                .texOffs(36, 0).addBox(-4.5f, -11.5f, -3f, 3f, 3f, 4f, new CubeDeformation(inflate))
                .texOffs(36, 7).addBox(1.5f, -11.5f, -3f, 3f, 3f, 4f, new CubeDeformation(inflate))
                .texOffs(36, 14).addBox(-2f, -3.5f, -7f, 4f, 4f, 3f, new CubeDeformation(inflate)), PartPose.offset(-.5f, -6.5f, 2.5f));

        inflate = 1f;
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 18).addBox(-4f, 0f, -2f, 8f, 12f, 4f, new CubeDeformation(inflate)), PartPose.offset(0f, 0f, 0f));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 18).addBox(-3f, -2f, -2f, 4f, 12f, 4f, new CubeDeformation(inflate)), PartPose.offset(-6f, 2f, 0f));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 21).addBox(-1f, -2f, -2f, 4f, 12f, 4f, new CubeDeformation(inflate)), PartPose.offset(6f, 2f, 0f));

        return LayerDefinition.create(meshdefinition, 56, 38);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.<ModelPart>of(this.body, this.rightArm, this.leftArm);
    }

    @Override
    public void renderToBuffer(PoseStack arg, VertexConsumer arg2, int i, int j, float f, float g, float h, float k)
    {
        this.setAllVisible(true);
        super.renderToBuffer(arg, arg2, i, j, f, g, h, k);
    }
}