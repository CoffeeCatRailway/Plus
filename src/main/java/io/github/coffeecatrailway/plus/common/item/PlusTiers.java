package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.TierSortingRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 25/12/2021
 */
public enum PlusTiers implements Tier
{
    ROSE_GOLD(2, 250, 12f, 1f, 18, () -> Ingredient.of(PlusItems.ROSE_GOLD_INGOT.get()));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    PlusTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient)
    {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    @Override
    public int getUses()
    {
        return this.uses;
    }

    @Override
    public float getSpeed()
    {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus()
    {
        return this.damage;
    }

    @Override
    public int getLevel()
    {
        return this.level;
    }

    @Override
    public int getEnchantmentValue()
    {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }

    @Nullable
    @Override
    public Tag<Block> getTag()
    {
        return switch (this)
                {
                    case ROSE_GOLD -> BlockTags.NEEDS_IRON_TOOL;
                    default -> throw new IncompatibleClassChangeError();
                };
    }

    public static void init()
    {
        TierSortingRegistry.registerTier(ROSE_GOLD, Plus.getLocation("rose_gold"), List.of(Tiers.IRON, Tiers.DIAMOND, Tiers.NETHERITE), List.of());
    }
}
