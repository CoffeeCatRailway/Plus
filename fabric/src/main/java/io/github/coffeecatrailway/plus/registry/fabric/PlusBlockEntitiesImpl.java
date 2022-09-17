package io.github.coffeecatrailway.plus.registry.fabric;

import io.github.coffeecatrailway.plus.common.block.entity.CardTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * @author CoffeeCatRailway
 * Created: 23/05/2022
 */
public class PlusBlockEntitiesImpl
{
    public static BlockEntityType.BlockEntitySupplier<CardTableBlockEntity> getCardTable()
    {
        return CardTableBlockEntity::new;
    }
}
