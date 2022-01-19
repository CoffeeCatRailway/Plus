package io.github.coffeecatrailway.plus.client.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

/**
 * @author CoffeeCatRailway
 * Created: 13/01/2022
 * Made with Blockbench 3.9.3
 */
public class AmuletModel extends HumanoidModel<LivingEntity>
{
	public AmuletModel(ModelPart part) {
        super(part);
	}

	public static LayerDefinition createBodyLayer() {
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

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body);
    }

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.setAllVisible(true);
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}