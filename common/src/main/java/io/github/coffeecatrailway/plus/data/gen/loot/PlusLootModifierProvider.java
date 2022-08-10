package io.github.coffeecatrailway.plus.data.gen.loot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.coffeecatrailway.plus.Plus;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author CoffeeCatRailway
 * Created: 10/08/2022
 */
public class PlusLootModifierProvider implements DataProvider
{
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final DataGenerator generator;
    private final Map<String, JsonObject> toSerialize = new HashMap<>();

    public PlusLootModifierProvider(DataGenerator generator)
    {
        this.generator = generator;
    }
    
    private void start()
    {
        this.add("fox", Plus.getLocation("modifier/fox"), EntityType.FOX);
        this.add("bat", Plus.getLocation("modifier/bat"), EntityType.BAT);
        this.add("squid", Plus.getLocation("modifier/squid"), EntityType.SQUID);
        this.add("glow_squid", Plus.getLocation("modifier/squid"), EntityType.GLOW_SQUID);
        this.add("bee", Plus.getLocation("modifier/bee"), EntityType.BEE);
        this.add("turtle", Plus.getLocation("modifier/turtle"), EntityType.TURTLE);
        this.add("stray", Plus.getLocation("modifier/stray"), EntityType.STRAY);
    }
    
    private void add(String id, ResourceLocation loottable, EntityType<?> entityType)
    {
        JsonObject json = new JsonObject();
        json.add("conditions", new JsonArray());
        json.addProperty("lootTable", loottable.toString());
        json.addProperty("entity", Registry.ENTITY_TYPE.getKey(entityType).toString());
        json.addProperty("type", "plus:pool_loot");
        this.toSerialize.put(id, json);
    }

    @Override
    public void run(HashCache cache) throws IOException
    {
        this.start();
        Path forgePath = this.generator.getOutputFolder().resolve("data/forge/loot_modifiers/global_loot_modifiers.json");
        String modPath = "data/" + Plus.MOD_ID + "/loot_modifiers/";
        List<ResourceLocation> entries = new ArrayList<>();
        this.toSerialize.forEach((name, json) -> {
            entries.add(new ResourceLocation(Plus.MOD_ID, name));
            Path modifierPath = this.generator.getOutputFolder().resolve(modPath + name + ".json");
            try
            {
                DataProvider.save(GSON, cache, json, modifierPath);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        });
        JsonObject forgeJson = new JsonObject();
        forgeJson.addProperty("replace", false);
        forgeJson.add("entries", GSON.toJsonTree(entries.stream().map(ResourceLocation::toString).collect(Collectors.toList())));
        DataProvider.save(GSON, cache, forgeJson, forgePath);
    }

    @Override
    public String getName()
    {
        return "Plus Forge Loot Modifier";
    }
}
