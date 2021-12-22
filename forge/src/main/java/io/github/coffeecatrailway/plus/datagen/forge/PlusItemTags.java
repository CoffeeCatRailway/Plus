package io.github.coffeecatrailway.plus.datagen.forge;

import io.github.coffeecatrailway.plus.Plus;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import org.jetbrains.annotations.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 20/12/2021
 */
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
