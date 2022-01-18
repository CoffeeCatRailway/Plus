package io.github.coffeecatrailway.plus.data.gen;

import com.google.gson.JsonElement;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedItemModelGenerator;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedModelProvider;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 18/01/2022
 */
public class PlusItemModels extends PollinatedModelProvider
{
    public PlusItemModels(DataGenerator generator, PollinatedModContainer container)
    {
        super(generator, container);
        this.addGenerator((blockStateOutput, modelOutput, skippedAutoModelsOutput) -> new Generator(modelOutput));
    }

    private static class Generator extends PollinatedItemModelGenerator
    {
        public Generator(BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput)
        {
            super(modelOutput);
        }

        @Override
        public void run()
        {
            this.generateFlatItem(PlusItems.FOX_FUR.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.SNOW_FOX_FUR.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.FOX_HAT.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.SNOW_FOX_HAT.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.FOX_MEAT.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.COOKED_FOX_MEAT.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.CHAIN_LINK.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.BAT.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.COOKED_BAT.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.SQUID.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.CALAMARI.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.TURTLE.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.COOKED_TURTLE.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.STINGER.get(), ModelTemplates.FLAT_ITEM);

            this.generateFlatItem(PlusItems.RAW_ROSE_GOLD.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_INGOT.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_NUGGET.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(PlusItems.ROSE_GOLD_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

            this.generateFlatItem(PlusBlocks.GLOW_LANTERN.get().asItem(), Plus.getLocation("item/glow_lantern"), ModelTemplates.FLAT_ITEM);
        }

        private void generateFlatItem(Item item, ResourceLocation texture, ModelTemplate modelTemplate)
        {
            modelTemplate.create(ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(texture), this.getModelOutput());
        }
    }
}
