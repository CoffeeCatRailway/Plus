package io.github.coffeecatrailway.plus.data.gen;

import gg.moonflower.pollen.api.datagen.provider.tags.PollinatedBlockTagsProvider;
import gg.moonflower.pollen.api.registry.resource.TagRegistry;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

/**
 * @author CoffeeCatRailway
 * Created: 17/01/2022
 */
public class PlusBlockTags extends PollinatedBlockTagsProvider
{
    public static final Tag.Named<Block> BASALT = TagRegistry.bindBlock(new ResourceLocation("forge", "basalt"));
    public static final Tag.Named<Block> STORAGE_BLOCKS = TagRegistry.bindBlock(new ResourceLocation("forge", "storage_blocks"));
    public static final Tag.Named<Block> STORAGE_BLOCK_ROSE_GOLD = TagRegistry.bindBlock(new ResourceLocation("forge", "storage_blocks/rose_gold"));

    public static final Tag.Named<Block> BASALT_FABRIC = TagRegistry.bindBlock(new ResourceLocation("c", "basalt"));
    public static final Tag.Named<Block> ROSE_GOLD_BLOCKS_FABRIC = TagRegistry.bindBlock(new ResourceLocation("c", "rose_gold_blocks"));

    public PlusBlockTags(DataGenerator generator, PollinatedModContainer container)
    {
        super(generator, container);
    }

    @Override
    protected void addTags()
    {
        this.tag(BASALT).add(Blocks.BASALT, PlusBlocks.BRITTLE_BASALT.get());
        this.tag(BASALT_FABRIC).addTag(BASALT);

        this.tag(STORAGE_BLOCK_ROSE_GOLD).add(PlusBlocks.ROSE_GOLD_BLOCK.get());
        this.tag(STORAGE_BLOCKS).addTag(STORAGE_BLOCK_ROSE_GOLD);
        this.tag(ROSE_GOLD_BLOCKS_FABRIC).add(PlusBlocks.ROSE_GOLD_BLOCK.get()).addTag(STORAGE_BLOCK_ROSE_GOLD);

        this.tag(BlockTags.BEACON_BASE_BLOCKS).add(PlusBlocks.ROSE_GOLD_BLOCK.get());
        this.tag(BlockTags.GUARDED_BY_PIGLINS).add(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get(), PlusBlocks.ROSE_GOLD_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(PlusBlocks.SAW_BENCH.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get(), PlusBlocks.ROSE_GOLD_BLOCK.get(), PlusBlocks.BRITTLE_BASALT.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get(), PlusBlocks.ROSE_GOLD_BLOCK.get());
    }
}