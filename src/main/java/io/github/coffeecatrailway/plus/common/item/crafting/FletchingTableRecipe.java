package io.github.coffeecatrailway.plus.common.item.crafting;

import com.google.gson.JsonObject;
import io.github.coffeecatrailway.plus.registry.PlusRecipes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 1/10/2021
 */
public class FletchingTableRecipe implements Recipe<CraftingContainer>
{
    private final ResourceLocation id;
    private final Ingredient feather;
    private final Ingredient rod;
    private final Ingredient tip;
    private final Potion potion;
    private final int count;

    public FletchingTableRecipe(ResourceLocation id, Ingredient feather, Ingredient rod, Ingredient tip, Potion potion, int count)
    {
        this.id = id;
        this.feather = feather;
        this.rod = rod;
        this.tip = tip;
        this.potion = potion;
        this.count = count;
    }

    @Override
    public boolean matches(CraftingContainer container, Level level)
    {
        if (!this.feather.test(container.getItem(0)) || !this.rod.test(container.getItem(1)) || !this.tip.test(container.getItem(2)))
            return false;
        ItemStack potionStack = container.getItem(3);
        return PotionUtils.getPotion(potionStack).equals(this.potion);
    }

    @Override
    public ItemStack assemble(CraftingContainer container)
    {
        return this.getResultItem();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return true;
    }

    @Override
    public ItemStack getResultItem()
    {
        return PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW, this.count), this.potion);
    }

    @Override
    public ResourceLocation getId()
    {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return PlusRecipes.FLETCHING_TABLE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType()
    {
        return PlusRecipes.FLETCHING_TABLE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<FletchingTableRecipe>
    {
        @Override
        public FletchingTableRecipe fromJson(ResourceLocation id, JsonObject object)
        {
            Ingredient feather = Ingredient.fromJson(GsonHelper.getAsJsonObject(object, "feather"));
            Ingredient rod = Ingredient.fromJson(GsonHelper.getAsJsonObject(object, "rod"));
            Ingredient tip = Ingredient.fromJson(GsonHelper.getAsJsonObject(object, "tip"));
            Potion potion = ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation(GsonHelper.getAsString(object, "potion")));
            return new FletchingTableRecipe(id, feather, rod, tip, potion, GsonHelper.getAsInt(object, "count"));
        }

        @Nullable
        @Override
        public FletchingTableRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer)
        {
            Ingredient feather = Ingredient.fromNetwork(buffer);
            Ingredient rod = Ingredient.fromNetwork(buffer);
            Ingredient tip = Ingredient.fromNetwork(buffer);
            Potion potion = ForgeRegistries.POTION_TYPES.getValue(buffer.readResourceLocation());
            int count = buffer.readVarInt();
            return  new FletchingTableRecipe(id, feather, rod, tip, potion, count);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FletchingTableRecipe recipe)
        {
            recipe.feather.toNetwork(buffer);
            recipe.rod.toNetwork(buffer);
            recipe.tip.toNetwork(buffer);
            buffer.writeResourceLocation(recipe.potion.getRegistryName());
            buffer.writeVarInt(recipe.count);
        }
    }
}
