package io.github.coffeecatrailway.plus.data.gen;

import io.github.coffeecatrailway.plus.PlusMod;
import io.github.coffeecatrailway.plus.common.loot.FoxLootModifier;
import io.github.coffeecatrailway.plus.registry.PlusLootModifiers;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

/**
 * @author CoffeeCatRailway
 * Created: 25/09/2021
 */
public class PlusLootModifierProvider extends GlobalLootModifierProvider
{
    public PlusLootModifierProvider(DataGenerator gen)
    {
        super(gen, PlusMod.MOD_ID);
    }

    @Override
    protected void start()
    {
        this.add("fox", PlusLootModifiers.FOX.get(), new FoxLootModifier(new LootItemCondition[0], 3, 5));
    }
}
