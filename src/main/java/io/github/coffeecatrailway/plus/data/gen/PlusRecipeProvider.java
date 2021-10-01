package io.github.coffeecatrailway.plus.data.gen;

import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.data.FletchingTableRecipeBuilder;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import io.github.coffeecatrailway.plus.registry.PlusRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

/**
 * @author CoffeeCatRailway
 * Created: 26/09/2021
 */
public class PlusRecipeProvider extends RecipeProvider
{
    public PlusRecipeProvider(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(PlusItems.FOX_HAT.get()).define('f', PlusItems.FOX_FUR.get()).pattern("fff").pattern("f f").pattern(" f ").unlockedBy("has_fur", has(PlusItems.FOX_FUR.get())).save(consumer);
        ShapedRecipeBuilder.shaped(PlusItems.SNOW_FOX_HAT.get()).define('f', PlusItems.SNOW_FOX_FUR.get()).pattern("fff").pattern("f f").pattern(" f ").unlockedBy("has_fur", has(PlusItems.SNOW_FOX_FUR.get())).save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PlusItems.FOX_MEAT.get()), PlusItems.COOKED_FOX_MEAT.get(), .35f, 200).unlockedBy("has_fox_meat", has(PlusItems.FOX_MEAT.get())).save(consumer);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.FOX_MEAT.get()), PlusItems.COOKED_FOX_MEAT.get(), .35f, 100, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_fox_meat", has(PlusItems.FOX_MEAT.get())).save(consumer, PlusMod.getLocation("cooked_fox_meat_from_smoking"));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.FOX_MEAT.get()), PlusItems.COOKED_FOX_MEAT.get(), .35f, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_fox_meat", has(PlusItems.FOX_MEAT.get())).save(consumer, PlusMod.getLocation("cooked_fox_meat_from_campfire"));

        ShapedRecipeBuilder.shaped(PlusBlocks.SAW_BENCH.get()).define('i', Tags.Items.INGOTS_IRON).define('p', ItemTags.PLANKS).pattern(" i ").pattern("ppp").unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON)).unlockedBy("has_planks", has(ItemTags.PLANKS)).save(consumer);
        this.sawBench(Ingredient.of(ItemTags.PLANKS), Items.STICK, 2).unlocks("has_planks", has(ItemTags.PLANKS)).save(consumer, PlusMod.getLocation("sticks_from_saw_bench"));
        this.sawBench(Ingredient.of(ItemTags.LOGS), Items.STICK, 12).unlocks("has_logs", has(ItemTags.LOGS)).save(consumer, PlusMod.getLocation("sticks_logs_from_saw_bench"));

        this.sawBenchRecipes(consumer, "oak", Blocks.OAK_LOG, Blocks.OAK_WOOD, ItemTags.OAK_LOGS, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_WOOD, Blocks.OAK_PLANKS, Blocks.OAK_STAIRS, Blocks.OAK_SLAB,
                Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Blocks.OAK_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.OAK_DOOR, Blocks.OAK_TRAPDOOR, Blocks.OAK_SIGN);
        this.sawBenchRecipes(consumer, "birch", Blocks.BIRCH_LOG, Blocks.BIRCH_WOOD, ItemTags.BIRCH_LOGS, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_WOOD, Blocks.BIRCH_PLANKS, Blocks.BIRCH_STAIRS, Blocks.BIRCH_SLAB,
                Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE, Blocks.BIRCH_BUTTON, Blocks.BIRCH_PRESSURE_PLATE, Blocks.BIRCH_DOOR, Blocks.BIRCH_TRAPDOOR, Blocks.BIRCH_SIGN);
        this.sawBenchRecipes(consumer, "spruce", Blocks.SPRUCE_LOG, Blocks.SPRUCE_WOOD, ItemTags.SPRUCE_LOGS, Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_WOOD, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_SLAB,
                Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Blocks.SPRUCE_BUTTON, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.SPRUCE_DOOR, Blocks.SPRUCE_TRAPDOOR, Blocks.SPRUCE_SIGN);
        this.sawBenchRecipes(consumer, "jungle", Blocks.JUNGLE_LOG, Blocks.JUNGLE_WOOD, ItemTags.JUNGLE_LOGS, Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_SLAB,
                Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE, Blocks.JUNGLE_BUTTON, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.JUNGLE_DOOR, Blocks.JUNGLE_TRAPDOOR, Blocks.JUNGLE_SIGN);
        this.sawBenchRecipes(consumer, "acacia", Blocks.ACACIA_LOG, Blocks.ACACIA_WOOD, ItemTags.ACACIA_LOGS, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_WOOD, Blocks.ACACIA_PLANKS, Blocks.ACACIA_STAIRS, Blocks.ACACIA_SLAB,
                Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.ACACIA_BUTTON, Blocks.ACACIA_PRESSURE_PLATE, Blocks.ACACIA_DOOR, Blocks.ACACIA_TRAPDOOR, Blocks.ACACIA_SIGN);
        this.sawBenchRecipes(consumer, "dark_oak", Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_WOOD, ItemTags.DARK_OAK_LOGS, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_SLAB,
                Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Blocks.DARK_OAK_BUTTON, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_TRAPDOOR, Blocks.DARK_OAK_SIGN);
        this.sawBenchRecipes(consumer, "warped", Blocks.WARPED_STEM, Blocks.WARPED_HYPHAE, ItemTags.WARPED_STEMS, Blocks.STRIPPED_WARPED_STEM, Blocks.STRIPPED_WARPED_HYPHAE, Blocks.WARPED_PLANKS, Blocks.WARPED_STAIRS, Blocks.WARPED_SLAB,
                Blocks.WARPED_FENCE, Blocks.WARPED_FENCE_GATE, Blocks.WARPED_BUTTON, Blocks.WARPED_PRESSURE_PLATE, Blocks.WARPED_DOOR, Blocks.WARPED_TRAPDOOR, Blocks.WARPED_SIGN);
        this.sawBenchRecipes(consumer, "crimson", Blocks.CRIMSON_STEM, Blocks.CRIMSON_HYPHAE, ItemTags.CRIMSON_STEMS, Blocks.STRIPPED_CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_SLAB,
                Blocks.CRIMSON_FENCE, Blocks.CRIMSON_FENCE_GATE, Blocks.CRIMSON_BUTTON, Blocks.CRIMSON_PRESSURE_PLATE, Blocks.CRIMSON_DOOR, Blocks.CRIMSON_TRAPDOOR, Blocks.CRIMSON_SIGN);

        ShapedRecipeBuilder.shaped(PlusItems.CHAIN_LINK.get(), 3).define('i', Tags.Items.INGOTS_IRON).define('n', Tags.Items.NUGGETS_IRON).pattern("i").pattern("n").unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON)).unlockedBy("has_nugget", has(Tags.Items.NUGGETS_IRON)).save(consumer);
        ShapedRecipeBuilder.shaped(Items.CHAIN).define('l', PlusItems.CHAIN_LINK.get()).pattern("l").pattern("l").pattern("l").unlockedBy("has_link", has(PlusItems.CHAIN_LINK.get())).save(consumer, new ResourceLocation("chain"));
        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_HELMET).define('l', PlusItems.CHAIN_LINK.get()).pattern("lll").pattern("l l").unlockedBy("has_link", has(PlusItems.CHAIN_LINK.get())).save(consumer);
        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_CHESTPLATE).define('l', PlusItems.CHAIN_LINK.get()).pattern("l l").pattern("lll").pattern("lll").unlockedBy("has_link", has(PlusItems.CHAIN_LINK.get())).save(consumer);
        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_LEGGINGS).define('l', PlusItems.CHAIN_LINK.get()).pattern("lll").pattern("l l").pattern("l l").unlockedBy("has_link", has(PlusItems.CHAIN_LINK.get())).save(consumer);
        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_BOOTS).define('l', PlusItems.CHAIN_LINK.get()).pattern("l l").pattern("l l").unlockedBy("has_link", has(PlusItems.CHAIN_LINK.get())).save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PlusItems.BAT.get()), PlusItems.COOKED_BAT.get(), .35f, 200).unlockedBy("has_bat", has(PlusItems.BAT.get())).save(consumer);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.BAT.get()), PlusItems.COOKED_BAT.get(), .35f, 100, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_bat", has(PlusItems.BAT.get())).save(consumer, PlusMod.getLocation("cooked_bat_from_smoking"));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.BAT.get()), PlusItems.COOKED_BAT.get(), .35f, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_bat", has(PlusItems.BAT.get())).save(consumer, PlusMod.getLocation("cooked_bat_from_campfire"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PlusItems.SQUID.get()), PlusItems.CALAMARI.get(), .35f, 200).unlockedBy("has_squid", has(PlusItems.SQUID.get())).save(consumer);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.SQUID.get()), PlusItems.CALAMARI.get(), .35f, 100, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_squid", has(PlusItems.SQUID.get())).save(consumer, PlusMod.getLocation("calamari_from_smoking"));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.SQUID.get()), PlusItems.CALAMARI.get(), .35f, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_squid", has(PlusItems.SQUID.get())).save(consumer, PlusMod.getLocation("calamari_from_campfire"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PlusItems.TURTLE.get()), PlusItems.COOKED_TURTLE.get(), .35f, 200).unlockedBy("has_turtle", has(PlusItems.TURTLE.get())).save(consumer);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.TURTLE.get()), PlusItems.COOKED_TURTLE.get(), .35f, 100, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_turtle", has(PlusItems.TURTLE.get())).save(consumer, PlusMod.getLocation("cooked_turtle_from_smoking"));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.TURTLE.get()), PlusItems.COOKED_TURTLE.get(), .35f, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_turtle", has(PlusItems.TURTLE.get())).save(consumer, PlusMod.getLocation("cooked_turtle_from_campfire"));

        FletchingTableRecipeBuilder.arrow(Potions.POISON).feather(Ingredient.of(Tags.Items.FEATHERS)).rod(Ingredient.of(Tags.Items.RODS_WOODEN)).tip(Ingredient.of(Items.FLINT))
                .unlockedBy("has_feathers", has(Tags.Items.FEATHERS)).unlockedBy("has_sticks", has(Tags.Items.RODS_WOODEN)).unlockedBy("has_flint", has(Items.FLINT)).save(consumer);
    }

    private void sawBenchRecipes(Consumer<FinishedRecipe> consumer, String woodName, Block log, Block wood, Tag.Named<Item> logTag, Block strippedLog, Block strippedWood, Block planks, Block stairs, Block slab,
                                 Block fence, Block fenceGate, Block button, Block pressurePlate, Block door, Block trapDoor, Block sign)
    {
        Ingredient planksIngredient = Ingredient.of(planks);
        this.sawBench(Ingredient.of(log), strippedLog).unlocks("has_log", has(log)).save(consumer, PlusMod.getLocation("stripped_" + woodName + "_log_from_saw_bench"));
        this.sawBench(Ingredient.of(wood), strippedWood).unlocks("has_wood", has(wood)).save(consumer, PlusMod.getLocation("stripped_" + woodName + "_wood_from_saw_bench"));
        this.sawBench(Ingredient.of(logTag), planks, 6).unlocks("has_logs", has(logTag)).save(consumer, PlusMod.getLocation(woodName + "_planks_from_saw_bench"));
        this.sawBench(planksIngredient, stairs).unlocks("has_planks", has(planks)).save(consumer, PlusMod.getLocation(woodName + "_stairs_from_saw_bench"));
        this.sawBench(planksIngredient, slab, 2).unlocks("has_planks", has(planks)).save(consumer, PlusMod.getLocation(woodName + "_slab_from_saw_bench"));
        this.sawBench(planksIngredient, fence).unlocks("has_planks", has(planks)).save(consumer, PlusMod.getLocation(woodName + "_fence_from_saw_bench"));
        this.sawBench(planksIngredient, fenceGate).unlocks("has_planks", has(planks)).save(consumer, PlusMod.getLocation(woodName + "_fence_gate_from_saw_bench"));
        this.sawBench(planksIngredient, button, 2).unlocks("has_planks", has(planks)).save(consumer, PlusMod.getLocation(woodName + "_button_from_saw_bench"));
        this.sawBench(planksIngredient, pressurePlate).unlocks("has_planks", has(planks)).save(consumer, PlusMod.getLocation(woodName + "_pressure_plate_from_saw_bench"));
        this.sawBench(planksIngredient, door, 2).unlocks("has_planks", has(planks)).save(consumer, PlusMod.getLocation(woodName + "_door_from_saw_bench"));
        this.sawBench(planksIngredient, trapDoor, 2).unlocks("has_planks", has(planks)).save(consumer, PlusMod.getLocation(woodName + "_trapdoor_from_saw_bench"));
        this.sawBench(planksIngredient, sign, 2).unlocks("has_planks", has(planks)).save(consumer, PlusMod.getLocation(woodName + "_sign_from_saw_bench"));
    }

    private SingleItemRecipeBuilder sawBench(Ingredient ingredient, ItemLike result)
    {
        return sawBench(ingredient, result, 1);
    }

    private SingleItemRecipeBuilder sawBench(Ingredient ingredient, ItemLike result, int count)
    {
        return new SingleItemRecipeBuilder(PlusRecipes.SAW_BENCH_SERIALIZER.get(), ingredient, result, count);
    }
}
