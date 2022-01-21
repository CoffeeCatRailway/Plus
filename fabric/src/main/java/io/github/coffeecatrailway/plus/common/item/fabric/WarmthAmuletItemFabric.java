package io.github.coffeecatrailway.plus.common.item.fabric;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.api.client.TrinketRenderer;
import io.github.coffeecatrailway.plus.client.entity.AmuletModel;
import io.github.coffeecatrailway.plus.common.item.WarmthAmuletItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * @author CoffeeCatRailway
 * Created: 18/01/2022
 */
public class WarmthAmuletItemFabric extends WarmthAmuletItem implements Trinket, TrinketRenderer
{
    public WarmthAmuletItemFabric(Properties properties)
    {
        super(properties);
        TrinketsApi.registerTrinket(this, this);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (TrinketItem.equipItem(player, stack))
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        return super.use(level, player, hand);
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack matrices, MultiBufferSource vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch)
    {
        if (contextModel instanceof PlayerModel<? extends LivingEntity>)
        {
            AmuletModel model = getModel();
            if (entity.isCrouching())
                matrices.translate(0f, .43f, .235f);
            else
                matrices.translate(0f, .5f, 0f);
            model.setupAnim(entity, limbAngle, limbDistance, animationProgress, animationProgress, headPitch);
            model.prepareMobModel(entity, limbAngle, limbDistance, tickDelta);
            TrinketRenderer.followBodyRotations(entity, model);

            VertexConsumer buffer = vertexConsumers.getBuffer(model.renderType(TEXTURE));
            matrices.scale(1.01f, 1.01f, 1.01f);
            model.renderToBuffer(matrices, buffer, light, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        }
    }
}
