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
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

/**
 * @author CoffeeCatRailway
 * Created: 17/03/2021
 */
public class PlusItemTags extends PollinatedItemTagsProvider
{
    public static final TagKey<Item> ROSE_GOLD_BLOCKS_FABRIC = TagRegistry.bindItem(new ResourceLocation("c", "rose_gold_blocks"));
    public static final TagKey<Item> INGOTS_ROSE_GOLD_FABRIC = TagRegistry.bindItem(new ResourceLocation("c", "rose_gold_ingots"));
    public static final TagKey<Item> NUGGETS_ROSE_GOLD_FABRIC = TagRegistry.bindItem(new ResourceLocation("c", "rose_gold_nuggets"));

    public static final TagKey<Item> STORAGE_BLOCK_ROSE_GOLD = TagRegistry.bindItem(new ResourceLocation("forge", "storage_blocks/rose_gold"));
    public static final TagKey<Item> INGOTS_ROSE_GOLD_FORGE = TagRegistry.bindItem(new ResourceLocation("forge", "ingots/rose_gold"));
    public static final TagKey<Item> NUGGETS_ROSE_GOLD_FORGE = TagRegistry.bindItem(new ResourceLocation("forge", "nuggets/rose_gold"));

    public static final TagKey<Item> INGOTS_IRON_FABRIC = TagRegistry.bindItem(new ResourceLocation("c", "iron_ingots"));
    public static final TagKey<Item> NUGGETS_IRON_FABRIC = TagRegistry.bindItem(new ResourceLocation("c", "iron_nuggets"));

    public static final TagKey<Item> INGOTS_FORGE = TagRegistry.bindItem(new ResourceLocation("forge", "ingots"));
    public static final TagKey<Item> NUGGETS_FORGE = TagRegistry.bindItem(new ResourceLocation("forge", "nuggets"));
    public static final TagKey<Item> INGOTS_IRON_FORGE = TagRegistry.bindItem(new ResourceLocation("forge", "ingots/iron"));
    public static final TagKey<Item> NUGGETS_IRON_FORGE = TagRegistry.bindItem(new ResourceLocation("forge", "nuggets/iron"));

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

        this.copy(PlusBlockTags.STORAGE_BLOCK_ROSE_GOLD, STORAGE_BLOCK_ROSE_GOLD);
        this.tag(INGOTS_ROSE_GOLD_FORGE).add(PlusItems.ROSE_GOLD_INGOT.get());
        this.tag(INGOTS_FORGE).addTag(INGOTS_ROSE_GOLD_FORGE);
        this.tag(NUGGETS_ROSE_GOLD_FORGE).add(PlusItems.ROSE_GOLD_NUGGET.get());
        this.tag(NUGGETS_FORGE).addTag(NUGGETS_ROSE_GOLD_FORGE);

        this.tag(ItemTags.PIGLIN_LOVED).add(PlusBlocks.ROSE_GOLD_BLOCK.get().asItem(), PlusBlocks.ROSE_GOLD_BLOCK.get().asItem(), PlusItems.RAW_ROSE_GOLD.get(), PlusItems.ROSE_GOLD_INGOT.get(), PlusItems.ROSE_GOLD_NUGGET.get(), PlusItems.ROSE_GOLD_SWORD.get(), PlusItems.ROSE_GOLD_SHOVEL.get(), PlusItems.ROSE_GOLD_PICKAXE.get(), PlusItems.ROSE_GOLD_AXE.get(), PlusItems.ROSE_GOLD_HOE.get());
        this.tag(ItemTags.CLUSTER_MAX_HARVESTABLES).add(PlusItems.ROSE_GOLD_PICKAXE.get());

        this.tag(ROSE_GOLD_BLOCKS_FABRIC).addTag(STORAGE_BLOCK_ROSE_GOLD);
        this.tag(INGOTS_ROSE_GOLD_FABRIC).addTag(INGOTS_ROSE_GOLD_FORGE);
        this.tag(NUGGETS_ROSE_GOLD_FABRIC).addTag(NUGGETS_ROSE_GOLD_FORGE);

        this.tag(INGOTS_IRON_FABRIC).addOptionalTag(INGOTS_IRON_FORGE.location()).add(Items.IRON_INGOT);
        this.tag(NUGGETS_IRON_FABRIC).addOptionalTag(NUGGETS_IRON_FORGE.location()).add(Items.IRON_NUGGET);

        this.tag(NECKLACE_CURIOS).add(PlusItems.WARMTH_AMULET.get());
        this.tag(NECKLACE_TRINKETS).addTag(NECKLACE_CURIOS);
    }
}
