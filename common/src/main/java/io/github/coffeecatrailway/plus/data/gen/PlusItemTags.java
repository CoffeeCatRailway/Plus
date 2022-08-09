package io.github.coffeecatrailway.plus.data.gen;

import gg.moonflower.pollen.api.datagen.provider.tags.PollinatedItemTagsProvider;
import gg.moonflower.pollen.api.registry.resource.TagRegistry;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

/**
 * @author CoffeeCatRailway
 * Created: 17/03/2021
 */
public class PlusItemTags extends PollinatedItemTagsProvider
{
    private static final TagKey<Item> ROSE_GOLD_BLOCKS_FORGE = TagRegistry.bindItem(new ResourceLocation("forge", "storage_blocks/rose_gold"));
    private static final TagKey<Item> INGOTS_ROSE_GOLD_FORGE = TagRegistry.bindItem(new ResourceLocation("forge", "ingots/rose_gold"));
    private static final TagKey<Item> NUGGETS_ROSE_GOLD_FORGE = TagRegistry.bindItem(new ResourceLocation("forge", "nuggets/rose_gold"));

    public static final TagKey<Item> ROSE_GOLD_BLOCKS_COMMON = TagRegistry.bindItem(new ResourceLocation("c", "rose_gold_blocks"));
    public static final TagKey<Item> INGOTS_ROSE_GOLD_COMMON = TagRegistry.bindItem(new ResourceLocation("c", "rose_gold_ingots"));
    public static final TagKey<Item> NUGGETS_ROSE_GOLD_COMMON = TagRegistry.bindItem(new ResourceLocation("c", "rose_gold_nuggets"));

    private static final TagKey<Item> INGOTS_FORGE = TagRegistry.bindItem(new ResourceLocation("forge", "ingots"));
    private static final TagKey<Item> NUGGETS_FORGE = TagRegistry.bindItem(new ResourceLocation("forge", "nuggets"));

    private static final TagKey<Item> INGOTS_GOLD = TagRegistry.bindItem(new ResourceLocation("forge", "ingots/gold"));
    public static final TagKey<Item> INGOTS_GOLD_COMMON = TagRegistry.bindItem(new ResourceLocation("c", "gold_ingots"));

    private static final TagKey<Item> INGOTS_IRON = TagRegistry.bindItem(new ResourceLocation("forge", "ingots/iron"));
    private static final TagKey<Item> NUGGETS_IRON = TagRegistry.bindItem(new ResourceLocation("forge", "nuggets/iron"));
    public static final TagKey<Item> INGOTS_IRON_COMMON = TagRegistry.bindItem(new ResourceLocation("c", "iron_ingots"));
    public static final TagKey<Item> NUGGETS_IRON_COMMON = TagRegistry.bindItem(new ResourceLocation("c", "iron_nuggets"));

    private static final TagKey<Item> GEMS_DIAMOND = TagRegistry.bindItem(new ResourceLocation("forge", "gems/diamond"));
    public static final TagKey<Item> GEMS_DIAMOND_COMMON = TagRegistry.bindItem(new ResourceLocation("c", "diamonds"));

    private static final TagKey<Item> RODS_WOODEN = TagRegistry.bindItem(new ResourceLocation("forge", "rods/wooden"));
    public static final TagKey<Item> STICKS_COMMON = TagRegistry.bindItem(new ResourceLocation("c", "wood_sticks"));

    private static final TagKey<Item> COBBLESTONE = TagRegistry.bindItem(new ResourceLocation("forge", "cobblestone"));
    private static final TagKey<Item> STONE = TagRegistry.bindItem(new ResourceLocation("forge", "stone"));
    private static final TagKey<Item> COBBLESTONES = TagRegistry.bindItem(new ResourceLocation("c", "cobblestones"));
    private static final TagKey<Item> COBBLESTONE_COMMON = TagRegistry.bindItem(new ResourceLocation("c", "cobblestone"));
    public static final TagKey<Item> STONE_COMMON = TagRegistry.bindItem(new ResourceLocation("c", "stones"));

    public static final TagKey<Item> NECKLACE_CURIOS = TagRegistry.bindItem(new ResourceLocation("curios", "necklace"));
    public static final TagKey<Item> NECKLACE_TRINKETS = TagRegistry.bindItem(new ResourceLocation("trinkets", "chest/necklace"));

    public PlusItemTags(DataGenerator generator, PollinatedModContainer container, BlockTagsProvider blockTags)
    {
        super(generator, container, blockTags);
    }

    @Override
    protected void addTags()
    {
        this.tag(ItemTags.BEACON_PAYMENT_ITEMS).add(PlusItems.ROSE_GOLD_INGOT.get());

        this.copy(PlusBlockTags.ROSE_GOLD_BLOCKS, ROSE_GOLD_BLOCKS_FORGE);
        this.tag(INGOTS_ROSE_GOLD_FORGE).add(PlusItems.ROSE_GOLD_INGOT.get());
        this.tag(NUGGETS_ROSE_GOLD_FORGE).add(PlusItems.ROSE_GOLD_NUGGET.get());

        this.tag(ROSE_GOLD_BLOCKS_COMMON).addTag(ROSE_GOLD_BLOCKS_FORGE);
        this.tag(INGOTS_ROSE_GOLD_COMMON).addTag(INGOTS_ROSE_GOLD_FORGE);
        this.tag(NUGGETS_ROSE_GOLD_COMMON).addTag(NUGGETS_ROSE_GOLD_FORGE);

        this.tag(ItemTags.PIGLIN_LOVED).add(PlusBlocks.ROSE_GOLD_BLOCK.get().asItem(), PlusBlocks.ROSE_GOLD_BLOCK.get().asItem(), PlusItems.RAW_ROSE_GOLD.get(), PlusItems.ROSE_GOLD_INGOT.get(), PlusItems.ROSE_GOLD_NUGGET.get(), PlusItems.ROSE_GOLD_SWORD.get(), PlusItems.ROSE_GOLD_SHOVEL.get(), PlusItems.ROSE_GOLD_PICKAXE.get(), PlusItems.ROSE_GOLD_AXE.get(), PlusItems.ROSE_GOLD_HOE.get());
        this.tag(ItemTags.CLUSTER_MAX_HARVESTABLES).add(PlusItems.ROSE_GOLD_PICKAXE.get());

        this.tag(INGOTS_GOLD).add(Items.GOLD_INGOT);
        this.tag(INGOTS_GOLD_COMMON).addTag(INGOTS_GOLD);

        this.tag(INGOTS_IRON).add(Items.IRON_INGOT);
        this.tag(INGOTS_IRON_COMMON).addTag(INGOTS_IRON);
        this.tag(NUGGETS_IRON).add(Items.IRON_NUGGET);
        this.tag(NUGGETS_IRON_COMMON).addTag(NUGGETS_IRON);

        this.tag(INGOTS_FORGE).addTag(INGOTS_ROSE_GOLD_COMMON, INGOTS_IRON_COMMON, INGOTS_GOLD_COMMON);
        this.tag(NUGGETS_FORGE).addTag(NUGGETS_ROSE_GOLD_COMMON, NUGGETS_IRON_COMMON);

        this.tag(GEMS_DIAMOND).add(Items.DIAMOND);
        this.tag(GEMS_DIAMOND_COMMON).addTag(GEMS_DIAMOND);

        this.tag(RODS_WOODEN).add(Items.STICK);
        this.tag(STICKS_COMMON).addTag(RODS_WOODEN);

        this.tag(COBBLESTONE).add(Blocks.COBBLESTONE.asItem(), Blocks.INFESTED_COBBLESTONE.asItem());
        this.tag(COBBLESTONES).addTag(COBBLESTONE);
        this.tag(COBBLESTONE_COMMON).addTag(COBBLESTONES);
        this.tag(STONE).add(Blocks.STONE.asItem(), Blocks.DIORITE.asItem(), Blocks.GRANITE.asItem(), Blocks.ANDESITE.asItem(), Blocks.INFESTED_STONE.asItem());
        this.tag(STONE_COMMON).addTag(STONE, COBBLESTONE_COMMON);

        this.tag(NECKLACE_CURIOS).add(PlusItems.WARMTH_AMULET.get());
        this.tag(NECKLACE_TRINKETS).addTag(NECKLACE_CURIOS);
    }
}
