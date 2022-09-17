package io.github.coffeecatrailway.plus.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.inventory.CardTableMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author CoffeeCatRailway
 * Created: 27/08/2022
 */
@Environment(EnvType.CLIENT)
public class CardTableScreen extends AbstractContainerScreen<CardTableMenu>
{
    private static final ResourceLocation BG_LOCATION = Plus.getLocation("textures/gui/container/card_table.png");

    public CardTableScreen(CardTableMenu menu, Inventory inventory, Component component)
    {
        super(menu, inventory, component);
        this.imageWidth = 230;
        this.imageHeight = 205;
        this.titleLabelY = 5;
        this.inventoryLabelY = this.imageHeight - 92;
    }

    @Override
    public void render(PoseStack poseStack, int i, int j, float f)
    {
        this.renderBackground(poseStack);
        super.render(poseStack, i, j, f);
        this.renderTooltip(poseStack, i, j);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float f, int i, int j)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, BG_LOCATION);
        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }
}