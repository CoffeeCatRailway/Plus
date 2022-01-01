package io.github.coffeecatrailway.plus.registry;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.block.BrittleBasaltBlock;
import io.github.coffeecatrailway.plus.common.block.GlowLanternBlock;
import io.github.coffeecatrailway.plus.common.block.SawBenchBlock;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 6/04/2021
 */
public class PlusBlocks
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Plus.MOD_ID);

    public static final RegistryObject<BrittleBasaltBlock> BRITTLE_BASALT = registerWithItem("brittle_basalt", () -> new BrittleBasaltBlock(BlockBehaviour.Properties.copy(Blocks.BASALT).randomTicks()), null);

    public static final RegistryObject<SawBenchBlock> SAW_BENCH = register("saw_bench", () -> new SawBenchBlock(BlockBehaviour.Properties.of(Material.WOOD).requiresCorrectToolForDrops().strength(3.5f)), prop -> prop.tab(CreativeModeTab.TAB_DECORATIONS));

    public static final RegistryObject<Block> RAW_ROSE_GOLD_BLOCK = register("raw_rose_gold_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(5f, 6f).sound(SoundType.STONE)), prop -> prop.tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Block> ROSE_GOLD_BLOCK = register("rose_gold_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(3f, 6f).sound(SoundType.METAL)), prop -> prop.tab(CreativeModeTab.TAB_BUILDING_BLOCKS));

    public static final RegistryObject<GlowLanternBlock> GLOW_LANTERN = register("glowing_lantern", () -> new GlowLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).lightLevel(state -> 10)), prop -> prop.tab(CreativeModeTab.TAB_DECORATIONS));

    public static <T extends Block> RegistryObject<T> register(String id, Supplier<T> block, Function<Item.Properties, Item.Properties> properties)
    {
        return registerWithItem(id, block, (object, prop) -> new BlockItem(object.get(), properties.apply(prop)));
    }

    private static <T extends Block> RegistryObject<T> registerWithItem(String id, Supplier<T> block, @Nullable BiFunction<RegistryObject<T>, Item.Properties, Item> item)
    {
        return registerWithItemAndName(id, block, item, PlusLanguage.capitalize(id));
    }

    private static <T extends Block> RegistryObject<T> registerWithItemAndName(String id, Supplier<T> block, @Nullable BiFunction<RegistryObject<T>, Item.Properties, Item> item, @Nullable String name)
    {
        RegistryObject<T> object = BLOCKS.register(id, block);
        if (item != null)
            PlusItems.ITEMS.register(id, () -> item.apply(object, new Item.Properties()));
        if (name != null)
            PlusLanguage.BLOCKS.put(object, name);
        return object;
    }

    public static void load(IEventBus bus)
    {
        LOGGER.debug("Loaded");
        BLOCKS.register(bus);
    }
}
