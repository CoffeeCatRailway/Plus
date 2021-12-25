package io.github.coffeecatrailway.plus.data.gen;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 17/03/2021
 */
public class PlusBlockTags extends BlockTagsProvider
{
    public static final Tag.Named<Block> BASALT = BlockTags.createOptional(new ResourceLocation("forge", "basalt"));
    public static final Tag.Named<Block> STORAGE_BLOCK_ROSE_GOLD = BlockTags.createOptional(new ResourceLocation("forge", "storage_blocks/rose_gold"));

    public PlusBlockTags(DataGenerator generator, @Nullable ExistingFileHelper fileHelper)
    {
        super(generator, Plus.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags()
    {
        this.tag(BASALT).add(Blocks.BASALT, PlusBlocks.BRITTLE_BASALT.get());
        this.tag(STORAGE_BLOCK_ROSE_GOLD).add(PlusBlocks.ROSE_GOLD_BLOCK.get());
        this.tag(Tags.Blocks.STORAGE_BLOCKS).addTag(STORAGE_BLOCK_ROSE_GOLD);

        this.tag(BlockTags.BEACON_BASE_BLOCKS).add(PlusBlocks.ROSE_GOLD_BLOCK.get());
        this.tag(BlockTags.GUARDED_BY_PIGLINS).add(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get(), PlusBlocks.ROSE_GOLD_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get(), PlusBlocks.ROSE_GOLD_BLOCK.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get(), PlusBlocks.ROSE_GOLD_BLOCK.get());
    }
}
