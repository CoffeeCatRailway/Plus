package io.github.coffeecatrailway.plus.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.common.inventory.SawBenchMenu;
import io.github.coffeecatrailway.plus.common.item.crafting.SawBenchRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 27/09/2021
 * <p>Based off {@link net.minecraft.client.gui.screens.inventory.StonecutterScreen}</p>
 */
@OnlyIn(Dist.CLIENT)
public class SawBenchScreen extends AbstractContainerScreen<SawBenchMenu>
{
    private static final ResourceLocation BG_LOCATION = PlusMod.getLocation("textures/gui/container/saw_bench.png");
    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private boolean displayRecipes;

    public SawBenchScreen(SawBenchMenu menu, Inventory inventory, Component name)
    {
        super(menu, inventory, name);
        menu.registerUpdateListener(this::containerChanged);
        this.titleLabelY--;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
    {
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY)
    {
        this.renderBackground(poseStack);
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bind(BG_LOCATION);
        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        this.blit(poseStack, this.leftPos + 119, this.topPos + 15 + (int) (41f * this.scrollOffs), 176 + (this.isScrollBarActive() ? 0 : 12), 0, 12, 15);
        int n = this.leftPos + 52;
        int o = this.topPos + 14;
        int p = this.startIndex + 12;
        this.renderButtons(poseStack, mouseX, mouseY, n, o, p);
        this.renderRecipes(n, o, p);
    }

    @Override
    protected void renderTooltip(PoseStack poseStack, int mouseX, int mouseY)
    {
        super.renderTooltip(poseStack, mouseX, mouseY);
        if (this.displayRecipes)
        {
            int k = this.leftPos + 52;
            int l = this.topPos + 14;
            int m = this.startIndex + 12;
            List<SawBenchRecipe> recipes = this.menu.getRecipes();
            for (int index = this.startIndex; index < m && index < this.menu.getNumRecipes(); index++)
            {
                int diff = index - this.startIndex;
                int width = k + diff % 4 * 16;
                int height = l + diff / 4 * 18 + 2;
                if (mouseX >= width && mouseX < width + 16 && mouseY >= height && mouseY < height + 18)
                    this.renderTooltip(poseStack, recipes.get(index).getResultItem(), mouseX, mouseY);
            }
        }
    }

    private void renderButtons(PoseStack poseStack, int mouseX, int mouseY, int k, int l, int m)
    {
        for (int index = this.startIndex; index < m && index < this.menu.getNumRecipes(); index++)
        {
            int diff = index - this.startIndex;
            int width = k + diff % 4 * 16;
            int height = l + diff / 4 * 18 + 2;
            int imageHeight = this.imageHeight;
            if (index == this.menu.getSelectedRecipeIndex())
                imageHeight += 18;
            else if (mouseX >= width && mouseY >= height && mouseX < width + 16 && mouseY < height + 18)
                imageHeight += 36;

            this.blit(poseStack, width, height - 1, 0, imageHeight, 16, 18);
        }
    }

    private void renderRecipes(int mouseX, int mouseY, int k)
    {
        List<SawBenchRecipe> recipes = this.menu.getRecipes();
        for (int index = this.startIndex; index < k && index < this.menu.getNumRecipes(); index++)
        {
            int diff = index - this.startIndex;
            int x = mouseX + diff % 4 * 16;
            int y = mouseY + diff / 4 * 18 + 2;
            this.minecraft.getItemRenderer().renderAndDecorateItem(recipes.get(index).getResultItem(), x, y);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        this.scrolling = false;
        if (this.displayRecipes)
        {
            int j = this.leftPos + 52;
            int k = this.topPos + 14;
            int l = this.startIndex + 12;

            for (int index = this.startIndex; index < l; index++)
            {
                int n = index - this.startIndex;
                double f = mouseX - (double) (j + n % 4 * 16);
                double g = mouseY - (double) (k + n / 4 * 18);
                if (f >= 0.0D && g >= 0.0D && f < 16.0D && g < 18.0D && this.menu.clickMenuButton(this.minecraft.player, index))
                {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, index);
                    return true;
                }
            }

            j = this.leftPos + 119;
            k = this.topPos + 9;
            if (mouseX >= (double) j && mouseX < (double) (j + 12) && mouseY >= (double) k && mouseY < (double) (k + 54))
                this.scrolling = true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY)
    {
        if (this.scrolling && this.isScrollBarActive())
        {
            int j = this.topPos + 14;
            int k = j + 54;
            this.scrollOffs = ((float) mouseY - (float) j - 7.5f) / ((float) (k - j) - 15f);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0f, 1f);
            this.startIndex = (int) ((double) (this.scrollOffs * (float) this.getOffscreenRows()) + .5d) * 4;
            return true;
        } else
            return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double scrollX, double scrollY, double scrollDelta)
    {
        if (this.isScrollBarActive())
        {
            int rows = this.getOffscreenRows();
            this.scrollOffs = (float) ((double) this.scrollOffs - scrollDelta / (double) rows);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0f, 1f);
            this.startIndex = (int) ((double) (this.scrollOffs * (float) rows) + .5d) * 4;
        }
        return true;
    }

    private boolean isScrollBarActive()
    {
        return this.displayRecipes && this.menu.getNumRecipes() > 12;
    }

    protected int getOffscreenRows()
    {
        return (this.menu.getNumRecipes() + 4 - 1) / 4 - 3;
    }

    private void containerChanged()
    {
        this.displayRecipes = this.menu.hasInputItem();
        if (!this.displayRecipes)
        {
            this.scrollOffs = 0f;
            this.startIndex = 0;
        }
    }
}
