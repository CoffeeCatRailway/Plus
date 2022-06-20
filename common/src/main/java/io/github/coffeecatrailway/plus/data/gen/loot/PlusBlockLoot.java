package io.github.coffeecatrailway.plus.data.gen.loot;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.ConditionUserBuilder;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author CoffeeCatRailway
 * Created: 17/06/2022
 */
public class PlusBlockLoot implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>
{
    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(ImmutableSet.toImmutableSet());
    private final Map<ResourceLocation, LootTable.Builder> map = Maps.newHashMap();

    private static <T> T applyExplosionCondition(ItemLike itemLike, ConditionUserBuilder<T> conditionUserBuilder)
    {
        return !EXPLOSION_RESISTANT.contains(itemLike.asItem()) ? conditionUserBuilder.when(ExplosionCondition.survivesExplosion()) : conditionUserBuilder.unwrap();
    }

    private static LootTable.Builder createSingleItemTable(ItemLike itemLike)
    {
        return LootTable.lootTable().withPool(applyExplosionCondition(itemLike, LootPool.lootPool().setRolls(ConstantValue.exactly(1f)).add(LootItem.lootTableItem(itemLike))));
    }

    public static LootTable.Builder noDrop()
    {
        return LootTable.lootTable();
    }

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer)
    {
        this.add(PlusBlocks.BRITTLE_BASALT.get(), noDrop());
        this.dropSelf(PlusBlocks.SAW_BENCH.get());
        this.dropSelf(PlusBlocks.RAW_ROSE_GOLD_BLOCK.get());
        this.dropSelf(PlusBlocks.ROSE_GOLD_BLOCK.get());
        this.dropSelf(PlusBlocks.GLOW_LANTERN.get());

        Set<ResourceLocation> set = Sets.newHashSet();
        Set<ResourceLocation> blockKeys = Registry.BLOCK.keySet().stream().filter(entityType -> Plus.MOD_ID.equals(entityType.getNamespace())).collect(Collectors.toSet());
        for (ResourceLocation blockKey : blockKeys)
        {
            Block block = Registry.BLOCK.get(blockKey);
            ResourceLocation resourcelocation = block.getLootTable();
            if (resourcelocation != BuiltInLootTables.EMPTY && set.add(resourcelocation))
            {
                LootTable.Builder builder = this.map.remove(resourcelocation);
                if (builder == null)
                    throw new IllegalStateException(String.format("Missing loottable '%s' for '%s'", resourcelocation, blockKey));

                consumer.accept(resourcelocation, builder);
            }
        }

        if (!this.map.isEmpty())
            throw new IllegalStateException("Created block loot tables for non-blocks: " + this.map.keySet());
    }

    public void dropOther(Block block, ItemLike itemLike)
    {
        this.add(block, createSingleItemTable(itemLike));
    }

    public void dropSelf(Block block)
    {
        this.dropOther(block, block);
    }

    private void add(Block block, Function<Block, LootTable.Builder> function)
    {
        this.add(block, function.apply(block));
    }

    private void add(Block block, LootTable.Builder builder)
    {
        this.map.put(block.getLootTable(), builder);
    }
}
