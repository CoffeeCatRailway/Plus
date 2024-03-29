package io.github.coffeecatrailway.plus.data.gen;

import gg.moonflower.pollen.api.datagen.provider.PollinatedRecipeProvider;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import io.github.coffeecatrailway.plus.registry.PlusRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;

import java.util.function.Consumer;

/**
 * @author CoffeeCatRailway
 * Created: 26/09/2021
 */
public class PlusRecipeProvider extends PollinatedRecipeProvider
{
    public PlusRecipeProvider(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(PlusItems.FOX_HAT.get()).define('f', PlusItems.FOX_FUR.get()).pattern("fff").pattern("f f").pattern(" f ").unlockedBy("has_fur", has(PlusItems.FOX_FUR.get())).save(consumer);
        ShapedRecipeBuilder.shaped(PlusItems.SNOW_FOX_HAT.get()).define('f', PlusItems.SNOW_FOX_FUR.get()).pattern("fff").pattern("f f").pattern(" f ").unlockedBy("has_fur", has(PlusItems.SNOW_FOX_FUR.get())).save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PlusItems.FOX_MEAT.get()), PlusItems.COOKED_FOX_MEAT.get(), .35f, 200).unlockedBy("has_fox_meat", has(PlusItems.FOX_MEAT.get())).save(consumer);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.FOX_MEAT.get()), PlusItems.COOKED_FOX_MEAT.get(), .35f, 100, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_fox_meat", has(PlusItems.FOX_MEAT.get())).save(consumer, Plus.getLocation("cooked_fox_meat_from_smoking"));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.FOX_MEAT.get()), PlusItems.COOKED_FOX_MEAT.get(), .35f, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_fox_meat", has(PlusItems.FOX_MEAT.get())).save(consumer, Plus.getLocation("cooked_fox_meat_from_campfire"));

        ShapedRecipeBuilder.shaped(PlusBlocks.SAW_BENCH.get()).define('i', PlusItemTags.INGOTS_IRON_COMMON).define('p', ItemTags.PLANKS).pattern(" i ").pattern("ppp").unlockedBy("has_iron", has(PlusItemTags.INGOTS_IRON_COMMON)).unlockedBy("has_planks", has(ItemTags.PLANKS)).save(consumer);
        sawBench(Ingredient.of(ItemTags.PLANKS), Items.STICK, 2).unlockedBy("has_planks", has(ItemTags.PLANKS)).save(consumer, Plus.getLocation("sticks_from_saw_bench"));
        sawBench(Ingredient.of(ItemTags.LOGS), Items.STICK, 12).unlockedBy("has_logs", has(ItemTags.LOGS)).save(consumer, Plus.getLocation("sticks_logs_from_saw_bench"));

        sawBenchRecipes(consumer, "oak", Blocks.OAK_LOG, Blocks.OAK_WOOD, ItemTags.OAK_LOGS, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_WOOD, Blocks.OAK_PLANKS, Blocks.OAK_STAIRS, Blocks.OAK_SLAB,
                Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Blocks.OAK_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.OAK_DOOR, Blocks.OAK_TRAPDOOR, Blocks.OAK_SIGN);
        sawBenchRecipes(consumer, "birch", Blocks.BIRCH_LOG, Blocks.BIRCH_WOOD, ItemTags.BIRCH_LOGS, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_WOOD, Blocks.BIRCH_PLANKS, Blocks.BIRCH_STAIRS, Blocks.BIRCH_SLAB,
                Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE, Blocks.BIRCH_BUTTON, Blocks.BIRCH_PRESSURE_PLATE, Blocks.BIRCH_DOOR, Blocks.BIRCH_TRAPDOOR, Blocks.BIRCH_SIGN);
        sawBenchRecipes(consumer, "spruce", Blocks.SPRUCE_LOG, Blocks.SPRUCE_WOOD, ItemTags.SPRUCE_LOGS, Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_WOOD, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_SLAB,
                Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Blocks.SPRUCE_BUTTON, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.SPRUCE_DOOR, Blocks.SPRUCE_TRAPDOOR, Blocks.SPRUCE_SIGN);
        sawBenchRecipes(consumer, "jungle", Blocks.JUNGLE_LOG, Blocks.JUNGLE_WOOD, ItemTags.JUNGLE_LOGS, Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_SLAB,
                Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE, Blocks.JUNGLE_BUTTON, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.JUNGLE_DOOR, Blocks.JUNGLE_TRAPDOOR, Blocks.JUNGLE_SIGN);
        sawBenchRecipes(consumer, "acacia", Blocks.ACACIA_LOG, Blocks.ACACIA_WOOD, ItemTags.ACACIA_LOGS, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_WOOD, Blocks.ACACIA_PLANKS, Blocks.ACACIA_STAIRS, Blocks.ACACIA_SLAB,
                Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.ACACIA_BUTTON, Blocks.ACACIA_PRESSURE_PLATE, Blocks.ACACIA_DOOR, Blocks.ACACIA_TRAPDOOR, Blocks.ACACIA_SIGN);
        sawBenchRecipes(consumer, "dark_oak", Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_WOOD, ItemTags.DARK_OAK_LOGS, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_SLAB,
                Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Blocks.DARK_OAK_BUTTON, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_TRAPDOOR, Blocks.DARK_OAK_SIGN);
        sawBenchRecipes(consumer, "warped", Blocks.WARPED_STEM, Blocks.WARPED_HYPHAE, ItemTags.WARPED_STEMS, Blocks.STRIPPED_WARPED_STEM, Blocks.STRIPPED_WARPED_HYPHAE, Blocks.WARPED_PLANKS, Blocks.WARPED_STAIRS, Blocks.WARPED_SLAB,
                Blocks.WARPED_FENCE, Blocks.WARPED_FENCE_GATE, Blocks.WARPED_BUTTON, Blocks.WARPED_PRESSURE_PLATE, Blocks.WARPED_DOOR, Blocks.WARPED_TRAPDOOR, Blocks.WARPED_SIGN);
        sawBenchRecipes(consumer, "crimson", Blocks.CRIMSON_STEM, Blocks.CRIMSON_HYPHAE, ItemTags.CRIMSON_STEMS, Blocks.STRIPPED_CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_SLAB,
                Blocks.CRIMSON_FENCE, Blocks.CRIMSON_FENCE_GATE, Blocks.CRIMSON_BUTTON, Blocks.CRIMSON_PRESSURE_PLATE, Blocks.CRIMSON_DOOR, Blocks.CRIMSON_TRAPDOOR, Blocks.CRIMSON_SIGN);

        ShapedRecipeBuilder.shaped(PlusItems.CHAIN_LINK.get(), 3).define('i', PlusItemTags.INGOTS_IRON_COMMON).define('n', PlusItemTags.NUGGETS_IRON_COMMON).pattern("i").pattern("n").unlockedBy("has_iron", has(PlusItemTags.INGOTS_IRON_COMMON)).unlockedBy("has_nugget", has(PlusItemTags.NUGGETS_IRON_COMMON)).save(consumer);
        ShapedRecipeBuilder.shaped(Items.CHAIN).define('l', PlusItems.CHAIN_LINK.get()).pattern("l").pattern("l").pattern("l").unlockedBy("has_link", has(PlusItems.CHAIN_LINK.get())).save(consumer, new ResourceLocation("chain"));
        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_HELMET).define('l', PlusItems.CHAIN_LINK.get()).pattern("lll").pattern("l l").unlockedBy("has_link", has(PlusItems.CHAIN_LINK.get())).save(consumer);
        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_CHESTPLATE).define('l', PlusItems.CHAIN_LINK.get()).pattern("l l").pattern("lll").pattern("lll").unlockedBy("has_link", has(PlusItems.CHAIN_LINK.get())).save(consumer);
        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_LEGGINGS).define('l', PlusItems.CHAIN_LINK.get()).pattern("lll").pattern("l l").pattern("l l").unlockedBy("has_link", has(PlusItems.CHAIN_LINK.get())).save(consumer);
        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_BOOTS).define('l', PlusItems.CHAIN_LINK.get()).pattern("l l").pattern("l l").unlockedBy("has_link", has(PlusItems.CHAIN_LINK.get())).save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PlusItems.BAT.get()), PlusItems.COOKED_BAT.get(), .35f, 200).unlockedBy("has_bat", has(PlusItems.BAT.get())).save(consumer);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.BAT.get()), PlusItems.COOKED_BAT.get(), .35f, 100, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_bat", has(PlusItems.BAT.get())).save(consumer, Plus.getLocation("cooked_bat_from_smoking"));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.BAT.get()), PlusItems.COOKED_BAT.get(), .35f, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_bat", has(PlusItems.BAT.get())).save(consumer, Plus.getLocation("cooked_bat_from_campfire"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PlusItems.SQUID.get()), PlusItems.CALAMARI.get(), .35f, 200).unlockedBy("has_squid", has(PlusItems.SQUID.get())).save(consumer);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.SQUID.get()), PlusItems.CALAMARI.get(), .35f, 100, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_squid", has(PlusItems.SQUID.get())).save(consumer, Plus.getLocation("calamari_from_smoking"));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.SQUID.get()), PlusItems.CALAMARI.get(), .35f, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_squid", has(PlusItems.SQUID.get())).save(consumer, Plus.getLocation("calamari_from_campfire"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PlusItems.TURTLE.get()), PlusItems.COOKED_TURTLE.get(), .35f, 200).unlockedBy("has_turtle", has(PlusItems.TURTLE.get())).save(consumer);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.TURTLE.get()), PlusItems.COOKED_TURTLE.get(), .35f, 100, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_turtle", has(PlusItems.TURTLE.get())).save(consumer, Plus.getLocation("cooked_turtle_from_smoking"));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(PlusItems.TURTLE.get()), PlusItems.COOKED_TURTLE.get(), .35f, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_turtle", has(PlusItems.TURTLE.get())).save(consumer, Plus.getLocation("cooked_turtle_from_campfire"));

        ShapelessRecipeBuilder.shapeless(PlusItems.RAW_ROSE_GOLD.get(), 2).requires(Items.RAW_GOLD).requires(Items.RAW_COPPER).unlockedBy("has_raw_gold", has(Items.RAW_GOLD)).unlockedBy("has_raw_copper", has(Items.RAW_COPPER)).save(consumer, Plus.getLocation("raw_rose_gold_from_crafting"));
        ShapelessRecipeBuilder.shapeless(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get(), 2).requires(Items.RAW_GOLD_BLOCK).requires(Items.RAW_COPPER_BLOCK).unlockedBy("has_raw_gold", has(Items.RAW_GOLD_BLOCK)).unlockedBy("has_raw_copper", has(Items.RAW_COPPER_BLOCK)).save(consumer, Plus.getLocation("raw_rose_gold_block_from_crafting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PlusItems.RAW_ROSE_GOLD.get()), PlusItems.ROSE_GOLD_INGOT.get(), 1f, 200).unlockedBy("has_raw_rose_gold", has(PlusItems.RAW_ROSE_GOLD.get())).save(consumer, Plus.getLocation("rose_gold_ingot_from_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(PlusItems.RAW_ROSE_GOLD.get()), PlusItems.ROSE_GOLD_INGOT.get(), .35f, 100).unlockedBy("has_raw_rose_gold", has(PlusItems.RAW_ROSE_GOLD.get())).save(consumer, Plus.getLocation("rose_gold_ingot_from_blasting"));
        this.storageRecipe(consumer, PlusItems.RAW_ROSE_GOLD.get(), PlusBlocks.RAW_ROSE_GOLD_BLOCK.get(), true);
        this.storageRecipe(consumer, PlusItems.ROSE_GOLD_INGOT.get(), PlusBlocks.ROSE_GOLD_BLOCK.get(), true);
        this.storageRecipe(consumer, PlusItems.ROSE_GOLD_NUGGET.get(), PlusItems.ROSE_GOLD_INGOT.get(), true);
        ShapedRecipeBuilder.shaped(PlusItems.ROSE_GOLD_SWORD.get())
                .define('#', Items.STICK)
                .define('X', PlusItemTags.INGOTS_ROSE_GOLD_COMMON)
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .unlockedBy("has_rose_gold_ingot", has(PlusItemTags.INGOTS_ROSE_GOLD_COMMON))
                .save(consumer);
        ShapedRecipeBuilder.shaped(PlusItems.ROSE_GOLD_SHOVEL.get())
                .define('#', Items.STICK)
                .define('X', PlusItemTags.INGOTS_ROSE_GOLD_COMMON)
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_rose_gold_ingot", has(PlusItemTags.INGOTS_ROSE_GOLD_COMMON))
                .save(consumer);
        ShapedRecipeBuilder.shaped(PlusItems.ROSE_GOLD_PICKAXE.get())
                .define('#', Items.STICK)
                .define('X', PlusItemTags.INGOTS_ROSE_GOLD_COMMON)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .unlockedBy("has_rose_gold_ingot", has(PlusItemTags.INGOTS_ROSE_GOLD_COMMON))
                .save(consumer);
        ShapedRecipeBuilder.shaped(PlusItems.ROSE_GOLD_AXE.get())
                .define('#', Items.STICK)
                .define('X', PlusItemTags.INGOTS_ROSE_GOLD_COMMON)
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .unlockedBy("has_rose_gold_ingot", has(PlusItemTags.INGOTS_ROSE_GOLD_COMMON))
                .save(consumer);
        ShapedRecipeBuilder.shaped(PlusItems.ROSE_GOLD_HOE.get())
                .define('#', Items.STICK)
                .define('X', PlusItemTags.INGOTS_ROSE_GOLD_COMMON)
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .unlockedBy("has_rose_gold_ingot", has(PlusItemTags.INGOTS_ROSE_GOLD_COMMON))
                .save(consumer);

        ShapedRecipeBuilder.shaped(PlusBlocks.GLOW_LANTERN.get())
                .define('#', Items.GLOW_INK_SAC)
                .define('X', Items.IRON_NUGGET)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_glow_ink_sac", has(Items.GLOW_INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(PlusItems.WARMTH_AMULET.get())
                .define('w', PlusItems.WARMTH_CRYSTAL.get())
                .define('l', PlusItems.CHAIN_LINK.get())
                .pattern(" l ")
                .pattern("l l")
                .pattern(" w ")
                .unlockedBy("has_warmth_crystal", has(PlusItems.WARMTH_CRYSTAL.get()))
                .unlockedBy("has_chain_link", has(PlusItems.CHAIN_LINK.get()))
                .save(consumer);

        this.shieldRecipe(consumer, PlusItems.WOODEN_SHIELD.get(), PlusItemTags.STICKS_COMMON);
        this.shieldRecipe(consumer, PlusItems.STONE_SHIELD.get(), PlusItemTags.STONE_COMMON);
        this.shieldRecipe(consumer, PlusItems.GOLD_SHIELD.get(), PlusItemTags.INGOTS_GOLD_COMMON);
        ShapedRecipeBuilder.shaped(PlusItems.DIAMOND_SHIELD.get()).define('p', ItemTags.PLANKS).define('d', PlusItemTags.GEMS_DIAMOND_COMMON).define('i', PlusItemTags.INGOTS_IRON_COMMON)
                .pattern("pdp").pattern("ipi").pattern(" p ")
                .unlockedBy("has_planks", has(ItemTags.PLANKS)).unlockedBy("has_diamond", has(PlusItemTags.GEMS_DIAMOND_COMMON)).unlockedBy("has_iron", has(PlusItemTags.INGOTS_IRON_COMMON)).save(consumer);
        UpgradeRecipeBuilder.smithing(Ingredient.of(PlusItems.DIAMOND_SHIELD.get()), Ingredient.of(Items.NETHERITE_INGOT), PlusItems.NETHERITE_SHIELD.get())
                .unlocks("has_netherite", has(Items.NETHERITE_INGOT)).unlocks("has_diamond_shield", has(PlusItems.NETHERITE_SHIELD.get())).save(consumer, Plus.getLocation("netherite_shield"));
        this.shieldRecipe(consumer, PlusItems.ROSE_GOLD_SHIELD.get(), PlusItemTags.INGOTS_ROSE_GOLD_COMMON);

        this.campfire(consumer, PlusBlocks.CAMPFIRE_BIRCH.get(), ItemTags.BIRCH_LOGS, ItemTags.COALS);
        this.campfire(consumer, PlusBlocks.CAMPFIRE_SPRUCE.get(), ItemTags.SPRUCE_LOGS, ItemTags.COALS);
        this.campfire(consumer, PlusBlocks.CAMPFIRE_JUNGLE.get(), ItemTags.JUNGLE_LOGS, ItemTags.COALS);
        this.campfire(consumer, PlusBlocks.CAMPFIRE_ACACIA.get(), ItemTags.ACACIA_LOGS, ItemTags.COALS);
        this.campfire(consumer, PlusBlocks.CAMPFIRE_DARK_OAK.get(), ItemTags.DARK_OAK_LOGS, ItemTags.COALS);

        this.campfire(consumer, PlusBlocks.SOUL_CAMPFIRE_BIRCH.get(), ItemTags.BIRCH_LOGS, ItemTags.SOUL_FIRE_BASE_BLOCKS);
        this.campfire(consumer, PlusBlocks.SOUL_CAMPFIRE_SPRUCE.get(), ItemTags.SPRUCE_LOGS, ItemTags.SOUL_FIRE_BASE_BLOCKS);
        this.campfire(consumer, PlusBlocks.SOUL_CAMPFIRE_JUNGLE.get(), ItemTags.JUNGLE_LOGS, ItemTags.SOUL_FIRE_BASE_BLOCKS);
        this.campfire(consumer, PlusBlocks.SOUL_CAMPFIRE_ACACIA.get(), ItemTags.ACACIA_LOGS, ItemTags.SOUL_FIRE_BASE_BLOCKS);
        this.campfire(consumer, PlusBlocks.SOUL_CAMPFIRE_DARK_OAK.get(), ItemTags.DARK_OAK_LOGS, ItemTags.SOUL_FIRE_BASE_BLOCKS);

        this.campfire(consumer, PlusBlocks.CAMPFIRE_CRIMSON.get(), ItemTags.CRIMSON_STEMS, ItemTags.SOUL_FIRE_BASE_BLOCKS);
        this.campfire(consumer, PlusBlocks.CAMPFIRE_WARPED.get(), ItemTags.WARPED_STEMS, ItemTags.SOUL_FIRE_BASE_BLOCKS);

        this.campfire(consumer, PlusBlocks.CAMPFIRE_MAPLE.get(), PlusItemTags.HNC_MAPLE_LOGS, ItemTags.COALS);
        this.campfire(consumer, PlusBlocks.SOUL_CAMPFIRE_MAPLE.get(), PlusItemTags.HNC_MAPLE_LOGS, ItemTags.SOUL_FIRE_BASE_BLOCKS);
    }

    private void campfire(Consumer<FinishedRecipe> consumer, CampfireBlock campfireBlock, TagKey<Item> log, TagKey<Item> fireBase)
    {
        ShapedRecipeBuilder.shaped(campfireBlock)
                .define('L', log)
                .define('S', PlusItemTags.STICKS_COMMON)
                .define('#', fireBase)
                .pattern(" S ")
                .pattern("S#S")
                .pattern("LLL")
                .unlockedBy("has_log", has(log))
                .unlockedBy("has_stick", has(PlusItemTags.STICKS_COMMON))
                .unlockedBy("has_soul_sand", has(fireBase))
                .save(consumer);
    }

    private void shieldRecipe(Consumer<FinishedRecipe> consumer, Item item, TagKey<Item> material)
    {
        ShapedRecipeBuilder.shaped(item).define('p', ItemTags.PLANKS).define('m', material)
                .pattern("pmp").pattern("ppp").pattern(" p ")
                .unlockedBy("has_planks", has(ItemTags.PLANKS)).unlockedBy("has_material", has(material)).save(consumer);
    }

    private void storageRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingot, ItemLike block, boolean big)
    {
        ShapelessRecipeBuilder.shapeless(ingot, big ? 9 : 4).requires(block).unlockedBy(getHasName(block), has(block)).save(consumer);
        if (big)
            ShapedRecipeBuilder.shaped(block).define('#', ingot).pattern("###").pattern("###").pattern("###")
                    .unlockedBy(getHasName(ingot), has(ingot)).save(consumer, Plus.getLocation(getItemName(block) + "_from_" + getItemName(ingot)));
        else
            ShapedRecipeBuilder.shaped(block).define('#', ingot).pattern("##").pattern("##")
                    .unlockedBy(getHasName(ingot), has(ingot)).save(consumer, Plus.getLocation(getItemName(block) + "_from_" + getItemName(ingot)));
    }

    private static void sawBenchRecipes(Consumer<FinishedRecipe> consumer, String woodName, Block log, Block wood, TagKey<Item> logTag, Block strippedLog, Block strippedWood, Block planks, Block stairs, Block slab,
                                        Block fence, Block fenceGate, Block button, Block pressurePlate, Block door, Block trapDoor, Block sign)
    {
        Ingredient planksIngredient = Ingredient.of(planks);
        Ingredient slabIngredient = Ingredient.of(slab);
        sawBench(Ingredient.of(log), strippedLog).unlockedBy("has_log", has(log)).save(consumer, Plus.getLocation("stripped_" + woodName + "_log_from_saw_bench"));
        sawBench(Ingredient.of(wood), strippedWood).unlockedBy("has_wood", has(wood)).save(consumer, Plus.getLocation("stripped_" + woodName + "_wood_from_saw_bench"));
        sawBench(Ingredient.of(logTag), planks, 6).unlockedBy("has_logs", has(logTag)).save(consumer, Plus.getLocation(woodName + "_planks_from_saw_bench"));
        sawBench(planksIngredient, stairs).unlockedBy("has_planks", has(planks)).save(consumer, Plus.getLocation(woodName + "_stairs_from_saw_bench"));
        sawBench(planksIngredient, slab, 2).unlockedBy("has_planks", has(planks)).save(consumer, Plus.getLocation(woodName + "_slab_from_saw_bench"));
        sawBench(planksIngredient, fence, 2).unlockedBy("has_planks", has(planks)).save(consumer, Plus.getLocation(woodName + "_fence_from_saw_bench"));
        sawBench(planksIngredient, fenceGate, 2).unlockedBy("has_planks", has(planks)).save(consumer, Plus.getLocation(woodName + "_fence_gate_from_saw_bench"));
        sawBench(planksIngredient, button, 3).unlockedBy("has_planks", has(planks)).save(consumer, Plus.getLocation(woodName + "_button_from_saw_bench"));
        sawBench(slabIngredient, pressurePlate, 3).unlockedBy("has_slab", has(slab)).save(consumer, Plus.getLocation(woodName + "_pressure_plate_from_saw_bench"));
        sawBench(planksIngredient, door, 2).unlockedBy("has_planks", has(planks)).save(consumer, Plus.getLocation(woodName + "_door_from_saw_bench"));
        sawBench(slabIngredient, trapDoor, 2).unlockedBy("has_slab", has(slab)).save(consumer, Plus.getLocation(woodName + "_trapdoor_from_saw_bench"));
        sawBench(planksIngredient, sign, 2).unlockedBy("has_planks", has(planks)).save(consumer, Plus.getLocation(woodName + "_sign_from_saw_bench"));
    }

    private static SingleItemRecipeBuilder sawBench(Ingredient ingredient, ItemLike result)
    {
        return sawBench(ingredient, result, 1);
    }

    private static SingleItemRecipeBuilder sawBench(Ingredient ingredient, ItemLike result, int count)
    {
        return new SingleItemRecipeBuilder(PlusRecipes.SAW_BENCH_SERIALIZER.get(), ingredient, result, count);
    }
}
