package io.github.coffeecatrailway.plus.fabric;

import io.github.coffeecatrailway.plus.Plus;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.world.entity.EntityType;

import java.util.Arrays;

public class PlusFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        Plus.PLATFORM.setup();

        LootTableEvents.MODIFY.register((resourceManager, manager, id, tableBuilder, source) -> {
            if (EntityType.FOX.getDefaultLootTable().equals(id))
                tableBuilder.pools(Arrays.asList(manager.get(Plus.getLocation("modifier/fox")).pools));
            if (EntityType.BAT.getDefaultLootTable().equals(id))
                tableBuilder.pools(Arrays.asList(manager.get(Plus.getLocation("modifier/bat")).pools));
            if (EntityType.SQUID.getDefaultLootTable().equals(id) || EntityType.GLOW_SQUID.getDefaultLootTable().equals(id))
                tableBuilder.pools(Arrays.asList(manager.get(Plus.getLocation("modifier/squid")).pools));
            if (EntityType.BEE.getDefaultLootTable().equals(id))
                tableBuilder.pools(Arrays.asList(manager.get(Plus.getLocation("modifier/bee")).pools));
            if (EntityType.TURTLE.getDefaultLootTable().equals(id))
                tableBuilder.pools(Arrays.asList(manager.get(Plus.getLocation("modifier/turtle")).pools));
            if (EntityType.STRAY.getDefaultLootTable().equals(id))
                tableBuilder.pools(Arrays.asList(manager.get(Plus.getLocation("modifier/stray")).pools));
        });
    }
}
