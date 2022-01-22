package io.github.coffeecatrailway.plus.fabric;

import io.github.coffeecatrailway.plus.Plus;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class PlusFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        Plus.PLATFORM.setup();

        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            if (EntityType.FOX.getDefaultLootTable().equals(id))
                supplier.copyFrom(manager.get(Plus.getLocation("modifier/fox")));
            if (EntityType.BAT.getDefaultLootTable().equals(id))
                supplier.copyFrom(manager.get(Plus.getLocation("modifier/bat")));
            if (EntityType.SQUID.getDefaultLootTable().equals(id) || EntityType.GLOW_SQUID.getDefaultLootTable().equals(id))
                supplier.copyFrom(manager.get(Plus.getLocation("modifier/squid")));
            if (EntityType.BEE.getDefaultLootTable().equals(id))
                supplier.copyFrom(manager.get(Plus.getLocation("modifier/bee")));
            if (EntityType.TURTLE.getDefaultLootTable().equals(id))
                supplier.copyFrom(manager.get(Plus.getLocation("modifier/turtle")));
            if (EntityType.STRAY.getDefaultLootTable().equals(id))
                supplier.copyFrom(manager.get(Plus.getLocation("modifier/stray")));
        });
    }
}
