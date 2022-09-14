package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.client.PlusClient;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Tier;

/**
 * @author CoffeeCatRailway
 * Created: 26/06/2022
 */
public class PlusShieldItem extends ShieldItem
{
    public PlusShieldItem(Properties properties, Tier tier)
    {
        super(properties.durability(tier.getUses()).tab(CreativeModeTab.TAB_COMBAT));
    }

    @Environment(EnvType.CLIENT)
    public Material getPattern()
    {
        if (this.equals(PlusItems.STONE_SHIELD.get()))
            return PlusClient.STONE_SHIELD_BASE;
        else if (this.equals(PlusItems.GOLD_SHIELD.get()))
            return PlusClient.GOLD_SHIELD_BASE;
        else if (this.equals(PlusItems.DIAMOND_SHIELD.get()))
            return PlusClient.DIAMOND_SHIELD_BASE;
        else if (this.equals(PlusItems.NETHERITE_SHIELD.get()))
            return PlusClient.NETHERITE_SHIELD_BASE;
        else if (this.equals(PlusItems.ROSE_GOLD_SHIELD.get()))
            return PlusClient.ROSE_GOLD_SHIELD_BASE;
        return PlusClient.WOODEN_SHIELD_BASE;
    }

    @Environment(EnvType.CLIENT)
    public Material getNoPattern()
    {
        if (this.equals(PlusItems.STONE_SHIELD.get()))
            return PlusClient.STONE_SHIELD_NO_PATTERN;
        else if (this.equals(PlusItems.GOLD_SHIELD.get()))
            return PlusClient.GOLD_SHIELD_NO_PATTERN;
        else if (this.equals(PlusItems.DIAMOND_SHIELD.get()))
            return PlusClient.DIAMOND_SHIELD_NO_PATTERN;
        else if (this.equals(PlusItems.NETHERITE_SHIELD.get()))
            return PlusClient.NETHERITE_SHIELD_NO_PATTERN;
        else if (this.equals(PlusItems.ROSE_GOLD_SHIELD.get()))
            return PlusClient.ROSE_GOLD_SHIELD_NO_PATTERN;
        return PlusClient.WOODEN_SHIELD_NO_PATTERN;
    }
}
