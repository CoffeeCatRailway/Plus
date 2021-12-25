package io.github.coffeecatrailway.plus.data.gen;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 17/03/2021
 */
@SuppressWarnings("unchecked")
public class PlusItemTags extends ItemTagsProvider
{
    public static final Tag.Named<Item> STORAGE_BLOCK_ROSE_GOLD = ItemTags.createOptional(new ResourceLocation("forge", "storage_blocks/rose_gold"));
    public static final Tag.Named<Item> INGOTS_ROSE_GOLD = ItemTags.createOptional(new ResourceLocation("forge", "ingots/rose_gold"));
    public static final Tag.Named<Item> NUGGETS_ROSE_GOLD = ItemTags.createOptional(new ResourceLocation("forge", "nuggets/rose_gold"));

    public PlusItemTags(DataGenerator generator, BlockTagsProvider provider, @Nullable ExistingFileHelper fileHelper)
    {
        super(generator, provider, Plus.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags()
    {
        this.tag(ItemTags.BEACON_PAYMENT_ITEMS).add(PlusItems.ROSE_GOLD_INGOT.get());
        this.copy(PlusBlockTags.STORAGE_BLOCK_ROSE_GOLD, STORAGE_BLOCK_ROSE_GOLD);
        this.tag(INGOTS_ROSE_GOLD).add(PlusItems.ROSE_GOLD_INGOT.get());
        this.tag(Tags.Items.INGOTS).addTag(INGOTS_ROSE_GOLD);
        this.tag(NUGGETS_ROSE_GOLD).add(PlusItems.ROSE_GOLD_NUGGET.get());
        this.tag(Tags.Items.NUGGETS).addTag(NUGGETS_ROSE_GOLD);
        this.tag(ItemTags.PIGLIN_LOVED).add(PlusBlocks.ROSE_GOLD_BLOCK.get().asItem(), PlusBlocks.ROSE_GOLD_BLOCK.get().asItem(), PlusItems.RAW_ROSE_GOLD.get(), PlusItems.ROSE_GOLD_INGOT.get(), PlusItems.ROSE_GOLD_NUGGET.get(), PlusItems.ROSE_GOLD_SWORD.get(), PlusItems.ROSE_GOLD_SHOVEL.get(), PlusItems.ROSE_GOLD_PICKAXE.get(), PlusItems.ROSE_GOLD_AXE.get(), PlusItems.ROSE_GOLD_HOE.get());
        this.tag(ItemTags.CLUSTER_MAX_HARVESTABLES).add(PlusItems.ROSE_GOLD_PICKAXE.get());
    }
}
