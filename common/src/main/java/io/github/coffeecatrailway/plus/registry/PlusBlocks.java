package io.github.coffeecatrailway.plus.registry;

import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedBlockRegistry;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.common.block.BrittleBasaltBlock;
import io.github.coffeecatrailway.plus.common.block.GlowLanternBlock;
import io.github.coffeecatrailway.plus.common.block.SawBenchBlock;
import io.github.coffeecatrailway.plus.common.item.TabInsertBlockItem;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 4/01/2022
 */
public class PlusBlocks
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final PollinatedBlockRegistry BLOCKS = PollinatedRegistry.createBlock(PlusItems.ITEMS);

    public static final Supplier<BrittleBasaltBlock> BRITTLE_BASALT = registerWithOutItem("brittle_basalt", () -> new BrittleBasaltBlock(BlockBehaviour.Properties.copy(Blocks.BASALT).randomTicks()));

    public static final Supplier<SawBenchBlock> SAW_BENCH = registerWithCustomItem("saw_bench", () -> new SawBenchBlock(BlockBehaviour.Properties.of(Material.WOOD).requiresCorrectToolForDrops().strength(3.5f).sound(SoundType.WOOD)), block -> new TabInsertBlockItem(Items.STONECUTTER, block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final Supplier<Block> RAW_ROSE_GOLD_BLOCK = registerWithCustomItem("raw_rose_gold_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(5f, 6f).sound(SoundType.STONE)), block -> new TabInsertBlockItem(Items.RAW_GOLD_BLOCK, block, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final Supplier<Block> ROSE_GOLD_BLOCK = registerWithCustomItem("rose_gold_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(3f, 6f).sound(SoundType.METAL)), block -> new TabInsertBlockItem(Items.GOLD_BLOCK, block, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final Supplier<GlowLanternBlock> GLOW_LANTERN = registerWithCustomItem("glowing_lantern", () -> new GlowLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).lightLevel(state -> 10)), block -> new TabInsertBlockItem(Items.SOUL_LANTERN, block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static <T extends Block> Supplier<T> registerWithItem(String id, Supplier<T> block, Function<Item.Properties, Item.Properties> properties)
    {
        Supplier<T> object = BLOCKS.registerWithItem(id, block, properties.apply(new Item.Properties()));
        PlusLanguage.BLOCKS.put(object, PlusLanguage.capitalize(id));
        return object;
    }

    public static <T extends Block> Supplier<T> registerWithCustomItem(String id, Supplier<T> block, Function<T, BlockItem> properties)
    {
        Supplier<T> object = BLOCKS.registerWithItem(id, block, properties::apply);
        PlusLanguage.BLOCKS.put(object, PlusLanguage.capitalize(id));
        return object;
    }

    public static <T extends Block> Supplier<T> registerWithOutItem(String id, Supplier<T> block)
    {
        Supplier<T> object = BLOCKS.register(id, block);
        PlusLanguage.BLOCKS.put(object, PlusLanguage.capitalize(id));
        return object;
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        BLOCKS.register(platform);
    }
}
