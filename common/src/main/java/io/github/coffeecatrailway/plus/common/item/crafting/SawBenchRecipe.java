package io.github.coffeecatrailway.plus.common.item.crafting;

import com.google.gson.JsonObject;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusRecipes;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;

/**
 * @author CoffeeCatRailway
 * Created: 27/09/2021
 */
public class SawBenchRecipe extends SingleItemRecipe
{
    public SawBenchRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result)
    {
        super(PlusRecipes.SAW_BENCH_TYPE.get(), PlusRecipes.SAW_BENCH_SERIALIZER.get(), id, group, ingredient, result);
    }

    @Override
    public boolean matches(Container menu, Level level)
    {
        return this.ingredient.test(menu.getItem(0));
    }

    @Override
    public ItemStack getToastSymbol()
    {
        return new ItemStack(PlusBlocks.SAW_BENCH.get());
    }

    public static class Serializer implements RecipeSerializer<SawBenchRecipe>
    {
        final SingleItemMaker factory;

        public Serializer(SingleItemMaker singleItemMaker)
        {
            this.factory = singleItemMaker;
        }

        public SawBenchRecipe fromJson(ResourceLocation location, JsonObject jsonObject)
        {
            String string = GsonHelper.getAsString(jsonObject, "group", "");
            Ingredient ingredient;
            if (GsonHelper.isArrayNode(jsonObject, "ingredient"))
                ingredient = Ingredient.fromJson(GsonHelper.getAsJsonArray(jsonObject, "ingredient"));
            else
                ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "ingredient"));

            String string2 = GsonHelper.getAsString(jsonObject, "result");
            int i = GsonHelper.getAsInt(jsonObject, "count");
            ItemStack itemStack = new ItemStack(Registry.ITEM.get(new ResourceLocation(string2)), i);
            return this.factory.create(location, string, ingredient, itemStack);
        }

        public SawBenchRecipe fromNetwork(ResourceLocation location, FriendlyByteBuf buffer)
        {
            String string = buffer.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack itemStack = buffer.readItem();
            return this.factory.create(location, string, ingredient, itemStack);
        }

        public void toNetwork(FriendlyByteBuf buffer, SawBenchRecipe recipe)
        {
            buffer.writeUtf(recipe.getGroup());
            recipe.getIngredients().get(0).toNetwork(buffer);
            buffer.writeItem(recipe.getResultItem());
        }

        public interface SingleItemMaker
        {
            SawBenchRecipe create(ResourceLocation resourceLocation, String string, Ingredient ingredient, ItemStack itemStack);
        }
    }
}
