package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.client.entity.AmuletModel;
import io.github.coffeecatrailway.plus.client.entity.FoxHatModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.Objects;

/**
 * @author CoffeeCatRailway
 * Created: 18/01/2022
 */
public class WarmthAmuletItem extends Item
{
    public static final ResourceLocation TEXTURE = Plus.getLocation("textures/models/amulet/warmth.png");
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(Plus.getLocation("amulet"), "main");
    private static AmuletModel model = null;

    public WarmthAmuletItem(Properties properties)
    {
        super(properties.stacksTo(1).rarity(Rarity.UNCOMMON).tab(CreativeModeTab.TAB_COMBAT));
    }

    @Environment(EnvType.CLIENT)
    public static AmuletModel getModel()
    {
        if (model == null)
            model = new AmuletModel(Minecraft.getInstance().getEntityModels().bakeLayer(LAYER));
        return Objects.requireNonNull(model);
    }
}
