package io.github.coffeecatrailway.plus.common.item;

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
    private final Material pattern;
    private final Material noPattern;

    public PlusShieldItem(Properties properties, Tier tier, Material pattern, Material noPattern)
    {
        super(properties.durability(tier.getUses()).tab(CreativeModeTab.TAB_COMBAT));
        this.pattern = pattern;
        this.noPattern = noPattern;
    }

    public Material getPattern()
    {
        return this.pattern;
    }

    public Material getNoPattern()
    {
        return this.noPattern;
    }
}
