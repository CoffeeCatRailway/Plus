package io.github.coffeecatrailway.plus.registry;

import net.minecraft.world.food.FoodProperties;

/**
 * @author CoffeeCatRailway
 * Created: 26/09/2021
 */
public class PlusFoods
{
    public static final FoodProperties FOX_MEAT = new FoodProperties.Builder().nutrition(3).saturationMod(.3f).build();
    public static final FoodProperties COOKED_FOX_MEAT = new FoodProperties.Builder().nutrition(7).saturationMod(.6f).build();
}
