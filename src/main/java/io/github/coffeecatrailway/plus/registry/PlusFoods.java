package io.github.coffeecatrailway.plus.registry;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 26/09/2021
 */
public class PlusFoods
{
    private static final Supplier<MobEffectInstance> HUNGER = () -> new MobEffectInstance(MobEffects.HUNGER, 400, 1);

    public static final FoodProperties FOX_MEAT = new FoodProperties.Builder().nutrition(3).saturationMod(.3f).build();
    public static final FoodProperties COOKED_FOX_MEAT = times(FOX_MEAT, 2.33f, 2f).build();

    public static final FoodProperties BAT = new FoodProperties.Builder().nutrition(1).saturationMod(.15f).effect(HUNGER, 1f).build();
    public static final FoodProperties COOKED_BAT = new FoodProperties.Builder().nutrition(2).saturationMod(.3f).build();

    public static final FoodProperties SQUID = new FoodProperties.Builder().nutrition(3).saturationMod(.3f).build();
    public static final FoodProperties CALAMARI = times(SQUID, 1.66f, 2f).build();

    private static FoodProperties.Builder times(FoodProperties copy, float amount)
    {
        return times(copy, (int) amount, amount);
    }

    private static FoodProperties.Builder times(FoodProperties copy, float nutrition, float saturationMod)
    {
        return copyFood(copy).nutrition(Math.round(copy.getNutrition() * nutrition)).saturationMod(copy.getSaturationModifier() * saturationMod);
    }

    private static FoodProperties.Builder copyFood(FoodProperties copy)
    {
        return copyFood(copy, new FoodProperties.Builder());
    }

    private static FoodProperties.Builder copyFood(FoodProperties copy, FoodProperties.Builder copied)
    {
        FoodProperties copiedTmp = copied.build();
        if (copy.isMeat() && !copiedTmp.isMeat()) copied.meat();
        if (copy.isFastFood() && !copiedTmp.isFastFood()) copied.fast();
        if (copy.canAlwaysEat() && !copiedTmp.canAlwaysEat()) copied.alwaysEat();

        for (Pair<MobEffectInstance, Float> effect : copy.getEffects())
            if (!copiedTmp.getEffects().contains(effect))
                copied.effect(effect::getFirst, effect.getSecond());

        return copied;
    }
}
