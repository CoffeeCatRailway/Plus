package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 26/09/2021
 */
public enum PlusArmorMaterials implements ArmorMaterial
{
    FOX_HAT("fox_hat", 5, new int[]{1, 0, 0, 0}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.of(PlusItems.FOX_FUR.get())),
    SNOW_FOX_HAT("snow_fox_hat", 7, new int[]{1, 0, 0, 0}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.of(PlusItems.SNOW_FOX_FUR.get()));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    PlusArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient)
    {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot arg)
    {
        return HEALTH_PER_SLOT[arg.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot arg)
    {
        return this.slotProtections[arg.getIndex()];
    }

    @Override
    public int getEnchantmentValue()
    {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound()
    {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public float getToughness()
    {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance()
    {
        return this.knockbackResistance;
    }
}
