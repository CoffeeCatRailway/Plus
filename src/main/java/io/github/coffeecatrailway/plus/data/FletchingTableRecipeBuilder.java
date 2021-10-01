package io.github.coffeecatrailway.plus.data;

import com.google.gson.JsonObject;
import io.github.coffeecatrailway.plus.registry.PlusRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @author CoffeeCatRailway
 * Created: 1/10/2021
 */
public class FletchingTableRecipeBuilder
{
    private static final Logger LOGGER = LogManager.getLogger();
    private final int count;
    private final Potion potion;
    private Ingredient feather;
    private Ingredient rod;
    private Ingredient tip;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public FletchingTableRecipeBuilder(int count, Potion potion)
    {
        this.count = count;
        this.potion = potion;
    }

    public static FletchingTableRecipeBuilder arrow(Potion potion)
    {
        return arrow(potion, 1);
    }

    public static FletchingTableRecipeBuilder arrow(Potion potion, int count)
    {
        return new FletchingTableRecipeBuilder(count, potion);
    }

    public FletchingTableRecipeBuilder feather(Ingredient feather)
    {
        this.feather = feather;
        return this;
    }

    public FletchingTableRecipeBuilder rod(Ingredient rod)
    {
        this.rod = rod;
        return this;
    }

    public FletchingTableRecipeBuilder tip(Ingredient tip)
    {
        this.tip = tip;
        return this;
    }

    public FletchingTableRecipeBuilder unlockedBy(String string, CriterionTriggerInstance criterion) {
        this.advancement.addCriterion(string, criterion);
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer) {
        ResourceLocation location = ForgeRegistries.POTION_TYPES.getKey(this.potion);
        if (this.advancement.getCriteria().isEmpty())
            throw new IllegalStateException("No way of obtaining recipe " + location);
        this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location)).rewards(AdvancementRewards.Builder.recipe(location)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(location, this.count, this.potion, this.feather, this.rod, this.tip, this.advancement, new ResourceLocation(location.getNamespace(), "recipes/potions/" + location.getPath())));
    }

    public class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final int count;
        private final Potion potion;
        private final Ingredient feather;
        private final Ingredient rod;
        private final Ingredient tip;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, int count, Potion potion, Ingredient feather, Ingredient rod, Ingredient tip, Advancement.Builder advancement, ResourceLocation advancementId)
        {
            this.id = id;
            this.count = count;
            this.potion = potion;
            this.feather = feather;
            this.rod = rod;
            this.tip = tip;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            json.add("feather", this.feather.toJson());
            json.add("rod", this.rod.toJson());
            json.add("tip", this.tip.toJson());
            json.addProperty("potion", this.potion.getRegistryName().toString());
            json.addProperty("count", this.count);
        }

        @Override
        public ResourceLocation getId()
        {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType()
        {
            return PlusRecipes.FLETCHING_TABLE_SERIALIZER.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement()
        {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId()
        {
            return this.advancementId;
        }
    }
}
