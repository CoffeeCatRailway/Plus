package io.github.coffeecatrailway.plus.data.gen.forge;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author CoffeeCatRailway
 * Created: 17/03/2021
 */
public class PlusLootTables extends LootTableProvider
{
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> tables = ImmutableList.of(
            Pair.of(ChestProvider::new, LootContextParamSets.CHEST),
            Pair.of(EntityProvider::new, LootContextParamSets.ENTITY),
            Pair.of(BlockProvider::new, LootContextParamSets.BLOCK));

    public PlusLootTables(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables()
    {
        return this.tables;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker)
    {
    }

    private static class ChestProvider extends ChestLoot
    {
        @Override
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> registry)
        {
        }
    }

    private static class EntityProvider extends EntityLoot
    {
        @Override
        protected void addTables()
        {
            CompoundTag redFoxTag = new CompoundTag();
            redFoxTag.putString("Type", "red");
            CompoundTag snowFoxTag = new CompoundTag();
            snowFoxTag.putString("Type", "snow");
            this.add(Plus.getLocation("modifier/fox"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1f, 2f))
                            .add(LootItem.lootTableItem(PlusItems.FOX_FUR.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0f, 1f)))
                                    .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().nbt(new NbtPredicate(redFoxTag)))))
                            .add(LootItem.lootTableItem(PlusItems.SNOW_FOX_FUR.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0f, 1f)))
                                    .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().nbt(new NbtPredicate(snowFoxTag))))))
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(cookableLootItem(PlusItems.FOX_MEAT, UniformGenerator.between(1f, 2f), false))));

            this.add(Plus.getLocation("modifier/bat"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(cookableLootItem(PlusItems.BAT, ConstantValue.exactly(1f), false))));

            this.add(Plus.getLocation("modifier/squid"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(cookableLootItem(PlusItems.SQUID, UniformGenerator.between(2f, 6f), true))));

            this.add(Plus.getLocation("modifier/bee"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(LootItem.lootTableItem(PlusItems.STINGER.get())
                                    .when(LootItemRandomChanceCondition.randomChance(.5f)))));

            this.add(Plus.getLocation("modifier/turtle"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(cookableLootItem(PlusItems.TURTLE, ConstantValue.exactly(1f), false))));
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities()
        {
            return ForgeRegistries.ENTITIES.getValues().stream().filter(entityType -> entityType.getRegistryName() != null && Plus.MOD_ID.equals(entityType.getRegistryName().getNamespace())).collect(Collectors.toSet());
        }

        private static LootPoolSingletonContainer.Builder<?> cookableLootItem(Supplier<Item> item, NumberProvider provider, boolean allowLooting)
        {
            LootPoolSingletonContainer.Builder<?> builder = LootItem.lootTableItem(item.get())
                    .apply(SetItemCountFunction.setCount(provider))
                    .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)));
            if (allowLooting)
                builder = builder.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0f, 1f)));
            return builder;
        }
    }

    private static class BlockProvider extends BlockLoot
    {
        @Override
        protected void addTables()
        {
            this.add(PlusBlocks.BRITTLE_BASALT.get(), noDrop());
            this.dropSelf(PlusBlocks.SAW_BENCH.get());
            this.dropSelf(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get());
            this.dropSelf(PlusBlocks.ROSE_GOLD_BLOCK.get());
            this.dropSelf(PlusBlocks.GLOW_LANTERN.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks()
        {
            return ForgeRegistries.BLOCKS.getValues().stream().filter(entityType -> entityType.getRegistryName() != null && Plus.MOD_ID.equals(entityType.getRegistryName().getNamespace())).collect(Collectors.toSet());
        }
    }
}
