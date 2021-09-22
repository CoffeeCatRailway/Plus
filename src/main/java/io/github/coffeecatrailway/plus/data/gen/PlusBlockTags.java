package io.github.coffeecatrailway.plus.data.gen;

import io.github.coffeecatrailway.plus.PlusMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 17/03/2021
 */
public class PlusBlockTags extends BlockTagsProvider
{
    public PlusBlockTags(DataGenerator generator, @Nullable ExistingFileHelper fileHelper)
    {
        super(generator, PlusMod.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags()
    {
    }
}
