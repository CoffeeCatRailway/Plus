package io.github.coffeecatrailway.plus.registry;

import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.common.item.crafting.SawBenchRecipe;
import io.github.coffeecatrailway.plus.common.item.crafting.SingleItemSerializer;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 27/09/2021
 */
public class PlusRecipes
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PlusMod.MOD_ID);

    public static RecipeType<SawBenchRecipe> SAW_BENCH_TYPE;

    public static final RegistryObject<SingleItemRecipe.Serializer<SawBenchRecipe>> SAW_BENCH_SERIALIZER = SERIALIZERS.register("saw_bench", () -> new SingleItemSerializer<>(SawBenchRecipe::new));

    private static <R extends Recipe<?>> RecipeType<R> register(String id)
    {
        return Registry.register(Registry.RECIPE_TYPE, PlusMod.getLocation(id), new RecipeType<R>()
        {
            @Override
            public String toString()
            {
                return Registry.RECIPE_TYPE.getKey(this).toString();
            }
        });
    }

    public static void load(IEventBus bus)
    {
        LOGGER.debug("Loaded");

        SAW_BENCH_TYPE = register("saw_bench");

        SERIALIZERS.register(bus);
    }
}
