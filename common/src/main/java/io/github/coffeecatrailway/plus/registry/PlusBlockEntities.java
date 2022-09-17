package io.github.coffeecatrailway.plus.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.block.entity.CardTableBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author CoffeeCatRailway
 * Created: 28/08/2022
 */
public class PlusBlockEntities
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final PollinatedRegistry<BlockEntityType<?>> BLOCK_ENTITIES = PollinatedRegistry.create(Registry.BLOCK_ENTITY_TYPE, Plus.MOD_ID);

    public static final Supplier<BlockEntityType<CardTableBlockEntity>> CARD_TABLE = register("card_table", getCardTable(), PlusBlocks.CARD_TABLE);

    @SafeVarargs
    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String id, BlockEntityType.BlockEntitySupplier<T> blockEntity, Supplier<? extends Block>... blocks)
    {
        return register(id, blockEntity, Arrays.stream(blocks).map(Supplier::get));
    }

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String id, BlockEntityType.BlockEntitySupplier<T> blockEntity, Stream<? extends Block> blocks)
    {
        return BLOCK_ENTITIES.register(id, () -> BlockEntityType.Builder.of(blockEntity, blocks.toArray(Block[]::new)).build(null));
    }

    // Mod loader sided block entities
    @ExpectPlatform
    private static BlockEntityType.BlockEntitySupplier<CardTableBlockEntity> getCardTable()
    {
        return Platform.error();
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        BLOCK_ENTITIES.register(platform);
    }
}
