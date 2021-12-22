package io.github.coffeecatrailway.plus.datagen.forge;

import io.github.coffeecatrailway.plus.Plus;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

import org.jetbrains.annotations.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 20/12/2021
 */
public class PlusBlockTags extends BlockTagsProvider
{
    public static final Tag.Named<Block> BASALT = BlockTags.createOptional(Plus.getLocation("basalt"));

    public PlusBlockTags(DataGenerator generator, @Nullable ExistingFileHelper fileHelper)
    {
        super(generator, Plus.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags()
    {
        this.tag(BASALT).add(Blocks.BASALT); // PlusBlocks.BRITTLE_BASALT.get()
    }
}
