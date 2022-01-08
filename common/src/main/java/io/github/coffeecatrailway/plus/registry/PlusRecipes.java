package io.github.coffeecatrailway.plus.registry;

import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.item.crafting.SawBenchRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 6/01/2022
 */
public class PlusRecipes
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final PollinatedRegistry<RecipeType<?>> TYPES = PollinatedRegistry.create(Registry.RECIPE_TYPE, Plus.MOD_ID);
    protected static final PollinatedRegistry<RecipeSerializer<?>> SERIALIZERS = PollinatedRegistry.create(Registry.RECIPE_SERIALIZER, Plus.MOD_ID);

    public static final Supplier<RecipeType<SawBenchRecipe>> SAW_BENCH_TYPE = register("saw_bench");
    public static final Supplier<RecipeSerializer<SawBenchRecipe>> SAW_BENCH_SERIALIZER = SERIALIZERS.register("saw_bench", () -> new SawBenchRecipe.Serializer<>(SawBenchRecipe::new));

    private static <R extends Recipe<?>> Supplier<RecipeType<R>> register(String id)
    {
        return TYPES.register(id, () -> new RecipeType<>()
        {
            @Override
            public String toString()
            {
                return Registry.RECIPE_TYPE.getKey(this).toString();
            }
        });
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        TYPES.register(platform);
        SERIALIZERS.register(platform);
    }
}
