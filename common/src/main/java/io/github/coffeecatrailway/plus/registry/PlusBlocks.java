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
import net.minecraft.world.level.block.CampfireBlock;
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
import java.util.function.ToIntFunction;

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

    public static final Supplier<CampfireBlock> CAMPFIRE_BIRCH = registerWithCustomItem("campfire_birch", () -> new CampfireBlock(true, 1, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(Items.CAMPFIRE, block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<CampfireBlock> SOUL_CAMPFIRE_BIRCH = registerWithCustomItem("soul_campfire_birch", () -> new CampfireBlock(false, 2, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(Items.SOUL_CAMPFIRE, block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<CampfireBlock> CAMPFIRE_SPRUCE = registerWithCustomItem("campfire_spruce", () -> new CampfireBlock(true, 1, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(CAMPFIRE_BIRCH.get().asItem(), block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<CampfireBlock> SOUL_CAMPFIRE_SPRUCE = registerWithCustomItem("soul_campfire_spruce", () -> new CampfireBlock(false, 2, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(SOUL_CAMPFIRE_BIRCH.get().asItem(), block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<CampfireBlock> CAMPFIRE_JUNGLE = registerWithCustomItem("campfire_jungle", () -> new CampfireBlock(true, 1, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(CAMPFIRE_SPRUCE.get().asItem(), block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<CampfireBlock> SOUL_CAMPFIRE_JUNGLE = registerWithCustomItem("soul_campfire_jungle", () -> new CampfireBlock(false, 2, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(SOUL_CAMPFIRE_SPRUCE.get().asItem(), block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<CampfireBlock> CAMPFIRE_ACACIA = registerWithCustomItem("campfire_acacia", () -> new CampfireBlock(true, 1, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(CAMPFIRE_JUNGLE.get().asItem(), block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<CampfireBlock> SOUL_CAMPFIRE_ACACIA = registerWithCustomItem("soul_campfire_acacia", () -> new CampfireBlock(false, 2, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(SOUL_CAMPFIRE_JUNGLE.get().asItem(), block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<CampfireBlock> CAMPFIRE_DARK_OAK = registerWithCustomItem("campfire_dark_oak", () -> new CampfireBlock(true, 1, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(CAMPFIRE_ACACIA.get().asItem(), block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<CampfireBlock> SOUL_CAMPFIRE_DARK_OAK = registerWithCustomItem("soul_campfire_dark_oak", () -> new CampfireBlock(false, 2, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(SOUL_CAMPFIRE_ACACIA.get().asItem(), block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<CampfireBlock> CAMPFIRE_CRIMSON = registerWithCustomItem("campfire_crimson", () -> new CampfireBlock(true, 2, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(SOUL_CAMPFIRE_DARK_OAK.get().asItem(), block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<CampfireBlock> CAMPFIRE_WARPED = registerWithCustomItem("campfire_warped", () -> new CampfireBlock(true, 2, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(CAMPFIRE_CRIMSON.get().asItem(), block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static Supplier<CampfireBlock> CAMPFIRE_MAPLE = null;
    public static Supplier<CampfireBlock> SOUL_CAMPFIRE_MAPLE = null;
    static
    {
        if (Platform.isModLoaded("hamncheese"))
        {
            CAMPFIRE_MAPLE = registerWithCustomItem("campfire_maple", () -> new CampfireBlock(true, 1, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(CAMPFIRE_DARK_OAK.get().asItem(), block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
            SOUL_CAMPFIRE_MAPLE = registerWithCustomItem("soul_campfire_maple", () -> new CampfireBlock(false, 2, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2f).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion()), block -> new TabInsertBlockItem(SOUL_CAMPFIRE_DARK_OAK.get().asItem(), block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        }
    }

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

    private static ToIntFunction<BlockState> litBlockEmission(int i) {
        return state -> state.getValue(BlockStateProperties.LIT) ? i : 0;
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        BLOCKS.register(platform);
    }
}
