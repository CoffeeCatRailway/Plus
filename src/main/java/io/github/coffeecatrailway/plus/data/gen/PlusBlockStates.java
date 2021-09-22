package io.github.coffeecatrailway.plus.data.gen;

import io.github.coffeecatrailway.plus.PlusMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author CoffeeCatRailway
 * Created: 7/04/2021
 */
public class PlusBlockStates extends BlockStateProvider
{
    public PlusBlockStates(DataGenerator gen, ExistingFileHelper existingFileHelper)
    {
        super(gen, PlusMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
    }

    private void toItem(Block block)
    {
        this.toItem(block, PlusMod.getLocation("block/" + block.getRegistryName().getPath()));
    }

    private void toItem(Block block, ResourceLocation model)
    {
        String path = block.getRegistryName().getPath();
        this.itemModels().withExistingParent(path, model);
    }
}
