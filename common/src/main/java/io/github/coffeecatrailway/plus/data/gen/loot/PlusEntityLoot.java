package io.github.coffeecatrailway.plus.data.gen.loot;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author CoffeeCatRailway
 * Created: 17/06/2022
 * <p>
 * Based on {@link net.minecraft.data.loot.EntityLoot}
 */
public class PlusEntityLoot implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>
{
    private static final EntityPredicate.Builder ENTITY_ON_FIRE = EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build());

    private static final Set<EntityType<?>> SPECIAL_LOOT_TABLE_TYPES = ImmutableSet.of(EntityType.PLAYER, EntityType.ARMOR_STAND, EntityType.IRON_GOLEM, EntityType.SNOW_GOLEM, EntityType.VILLAGER);
    private final Map<ResourceLocation, LootTable.Builder> map = Maps.newHashMap();

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer)
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

            this.add(Plus.getLocation("modifier/stray"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(LootItem.lootTableItem(PlusItems.WARMTH_CRYSTAL.get())
                                    .when(LootItemRandomChanceCondition.randomChance(.5f)))));

        Set<ResourceLocation> set = Sets.newHashSet();
        Set<ResourceLocation> entityTypeKeys = Registry.ENTITY_TYPE.keySet().stream().filter(entityType -> Plus.MOD_ID.equals(entityType.getNamespace())).collect(Collectors.toSet());
        for (ResourceLocation entityTypeKey : entityTypeKeys)
        {
            EntityType<?> entityType = Registry.ENTITY_TYPE.get(entityTypeKey);
            ResourceLocation resourcelocation = entityType.getDefaultLootTable();
            if (this.isNonLiving(entityType))
            {
                if (resourcelocation != BuiltInLootTables.EMPTY && this.map.remove(resourcelocation) != null)
                    throw new IllegalStateException(String.format("Weird loottable '%s' for '%s', not a LivingEntity so should not have loot", resourcelocation, entityTypeKey));
            } else if (resourcelocation != BuiltInLootTables.EMPTY && set.add(resourcelocation))
            {
                LootTable.Builder loottable$builder = this.map.remove(resourcelocation);
                if (loottable$builder == null)
                    throw new IllegalStateException(String.format("Missing loottable '%s' for '%s'", resourcelocation, entityTypeKey));

                consumer.accept(resourcelocation, loottable$builder);
            }
        }

        this.map.forEach(consumer);
    }

    protected boolean isNonLiving(EntityType<?> entityType)
    {
        return !SPECIAL_LOOT_TABLE_TYPES.contains(entityType) && entityType.getCategory() == MobCategory.MISC;
    }

//    private void add(EntityType<?> entityType, LootTable.Builder builder)
//    {
//        this.add(entityType.getDefaultLootTable(), builder);
//    }

    private void add(ResourceLocation resourceLocation, LootTable.Builder builder)
    {
        this.map.put(resourceLocation, builder);
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
