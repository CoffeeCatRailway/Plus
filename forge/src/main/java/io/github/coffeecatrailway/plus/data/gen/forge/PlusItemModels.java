package io.github.coffeecatrailway.plus.data.gen.forge;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 17/03/2021
 */
public class PlusItemModels implements DataProvider
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final DataGenerator dataGenerator;
    private BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer;

    public PlusItemModels(DataGenerator dataGenerator)
    {
        this.dataGenerator = dataGenerator;
    }

    @Override
    public void run(HashCache cache)
    {
        Path path = this.dataGenerator.getOutputFolder();
        Map<ResourceLocation, Supplier<JsonElement>> map1 = Maps.newHashMap();
        this.consumer = (location, json) ->
        {
            Supplier<JsonElement> supplier = map1.put(location, json);
            if (supplier != null)
            {
                throw new IllegalStateException("Duplicate model definition for " + location);
            }
        };
        this.register();
        this.saveCollection(cache, path, map1, PlusItemModels::createModelPath);
    }

    private void register()
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

//        this.generateFlatItem(PlusBlocks.GLOW_LANTERN.get().asItem(), Plus.getLocation("item/glow_lantern"), ModelTemplates.FLAT_ITEM);
    }

    private void generateFlatItem(Item item, ModelTemplate modelsUtil)
    {
        modelsUtil.create(ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(item), this.consumer);
    }

    private void generateFlatItem(Item item, ResourceLocation texture, ModelTemplate modelsUtil)
    {
        modelsUtil.create(ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(texture), this.consumer);
    }

    private <T> void saveCollection(HashCache cache, Path dataFolder, Map<T, ? extends Supplier<JsonElement>> models, BiFunction<Path, T, Path> resolver)
    {
        models.forEach((p_240088_3_, p_240088_4_) ->
        {
            Path path = resolver.apply(dataFolder, p_240088_3_);

            try
            {
                DataProvider.save(GSON, cache, p_240088_4_.get(), path);
            } catch (Exception exception)
            {
                LOGGER.error("Couldn't save {}", path, exception);
            }
        });
    }

    private static Path createModelPath(Path dataFolder, ResourceLocation name)
    {
        return dataFolder.resolve("assets/" + name.getNamespace() + "/models/" + name.getPath() + ".json");
    }

    @Override
    public String getName()
    {
        return Plus.MOD_ID + " Block State Definitions";
    }
}
