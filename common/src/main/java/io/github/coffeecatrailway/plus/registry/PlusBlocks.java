package io.github.coffeecatrailway.plus.registry;

import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.block.BrittleBasaltBlock;
import io.github.coffeecatrailway.plus.common.block.GlowLanternBlock;
import io.github.coffeecatrailway.plus.data.gen.PlusDataGenHelper;
import io.github.coffeecatrailway.plus.data.gen.PlusDataGenHelperCommon;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 4/01/2022
 */
public class PlusBlocks
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final PollinatedRegistry<Block> BLOCKS = PollinatedRegistry.create(Registry.BLOCK, Plus.MOD_ID);

    public static final Supplier<BrittleBasaltBlock> BRITTLE_BASALT = registerWithItem("brittle_basalt", () -> new BrittleBasaltBlock(BlockBehaviour.Properties.copy(Blocks.BASALT).randomTicks()), null);

    public static final Supplier<Block> RAW_ROSE_GOLD_BLOCK = register("raw_rose_gold_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(5f, 6f).sound(SoundType.STONE)), prop -> prop.tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final Supplier<Block> ROSE_GOLD_BLOCK = register("rose_gold_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(3f, 6f).sound(SoundType.METAL)), prop -> prop.tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final Supplier<GlowLanternBlock> GLOW_LANTERN = register("glowing_lantern", () -> new GlowLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).lightLevel(state -> 10)), prop -> prop.tab(CreativeModeTab.TAB_DECORATIONS));

    public static <T extends Block> Supplier<T> register(String id, Supplier<T> block, Function<Item.Properties, Item.Properties> properties)
    {
        return registerWithItem(id, block, (object, prop) -> new BlockItem(object.get(), properties.apply(prop)));
    }

    private static <T extends Block> Supplier<T> registerWithItem(String id, Supplier<T> block, @Nullable BiFunction<Supplier<T>, Item.Properties, Item> item)
    {
        return registerWithItemAndName(id, block, item, PlusDataGenHelperCommon.capitalize(id));
    }

    private static <T extends Block> Supplier<T> registerWithItemAndName(String id, Supplier<T> block, @Nullable BiFunction<Supplier<T>, Item.Properties, Item> item, @Nullable String name)
    {
        Supplier<T> object = BLOCKS.register(id, block);
        if (item != null)
            PlusItems.ITEMS.register(id, () -> item.apply(object, new Item.Properties()));
        if (name != null)
            PlusDataGenHelper.languageBlock(object, id, name);
        return object;
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        BLOCKS.register(platform);
    }
}
