package io.github.coffeecatrailway.plus.client.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

/**
 * @author CoffeeCatRailway
 * Created: 26/09/2021
 * Made with Blockbench 3.9.3
 */
public class FoxHatModel extends HumanoidModel<LivingEntity>
{
    public FoxHatModel()
    {
        super(1f, 0f, 56, 38);

        ModelPart body = new ModelPart(this);
        body.setPos(0f, 0f, 0f);
        body.texOffs(16, 18).addBox(-4f, 0f, -2f, 8f, 12f, 4f, 0.75f, false);

        ModelPart rightArm = new ModelPart(this);
        rightArm.setPos(0f, 0f, 0f);
        rightArm.texOffs(0, 18).addBox(-3f, -2f, -2f, 4f, 12f, 4f, 0.75f, false);

        ModelPart leftArm = new ModelPart(this);
        leftArm.setPos(0f, 0f, 0f);
        leftArm.texOffs(40, 21).addBox(-1f, -2f, -2f, 4f, 12f, 4f, 0.75f, false);

        ModelPart head = new ModelPart(this);
        head.setPos(-0.5f, -6.5f, 2.5f);
        head.texOffs(0, 0).addBox(-4f, -2f, -7f, 9f, 9f, 9f, 0f, false);
        head.texOffs(36, 0).addBox(-4f, -5f, -6f, 3f, 3f, 4f, 0f, false);
        head.texOffs(36, 7).addBox(2f, -5f, -6f, 3f, 3f, 4f, 0f, false);
        head.texOffs(36, 14).addBox(-1.5f, 3f, -10f, 4f, 4f, 3f, 0f, false);

        this.body = new ModelPart(this);
        this.body.addChild(body);
        this.rightArm = new ModelPart(this);
        this.rightArm.addChild(rightArm);
        this.leftArm = new ModelPart(this);
        this.leftArm.addChild(leftArm);
        this.head = new ModelPart(this);
        this.head.addChild(head);
    }

    @Override
    public void renderToBuffer(PoseStack arg, VertexConsumer arg2, int i, int j, float f, float g, float h, float k)
    {
        this.setAllVisible(true);
        super.renderToBuffer(arg, arg2, i, j, f, g, h, k);
    }

    @Override
    protected Iterable<ModelPart> bodyParts()
    {
        return ImmutableList.<ModelPart>of(this.body, this.rightArm, this.leftArm);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z)
    {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}