package io.github.coffeecatrailway.plus.data.gen;

import io.github.coffeecatrailway.plus.Plus;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 17/03/2021
 */
@SuppressWarnings("unchecked")
public class PlusItemTags extends ItemTagsProvider
{
    public PlusItemTags(DataGenerator generator, BlockTagsProvider provider, @Nullable ExistingFileHelper fileHelper)
    {
        super(generator, provider, Plus.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags()
    {
    }
}
