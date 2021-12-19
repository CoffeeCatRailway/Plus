package io.github.coffeecatrailway.plus.data.gen;

import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 17/03/2021
 */
public class PlusBlockTags extends BlockTagsProvider
{
    public static final Tag.Named<Block> BASALT = BlockTags.createOptional(new ResourceLocation("forge", "basalt"));

    public PlusBlockTags(DataGenerator generator, @Nullable ExistingFileHelper fileHelper)
    {
        super(generator, PlusMod.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags()
    {
        this.tag(BASALT).add(Blocks.BASALT, PlusBlocks.BRITTLE_BASALT.get());
    }
}
