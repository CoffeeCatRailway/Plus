package io.github.coffeecatrailway.plus.datagen.forge;

import io.github.coffeecatrailway.plus.Plus;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

/**
 * @author CoffeeCatRailway
 * Created: 20/12/2021
 */
public class PlusLootModifierProvider extends GlobalLootModifierProvider
{
    public PlusLootModifierProvider(DataGenerator gen)
    {
        super(gen, Plus.MOD_ID);
    }

    @Override
    protected void start()
    {
//        this.add("fox", PlusLootModifiers.FOX.get(), new FoxLootModifier(new LootItemCondition[0], new IntRange(1, 4), new IntRange(1, 3)));
//        this.add("bat", PlusLootModifiers.COOKABLE.get(), new CookableFoodLootModifier(new LootItemCondition[0], PlusItems.BAT::get, new IntRange(1, 1), false, EntityType.BAT));
//        this.add("squid", PlusLootModifiers.COOKABLE.get(), new CookableFoodLootModifier(new LootItemCondition[0], PlusItems.SQUID::get, new IntRange(2, 6), true, EntityType.SQUID));
//        this.add("strider", PlusLootModifiers.CHANCE.get(), new ChanceLootModifier(new LootItemCondition[0], () -> Items.NETHERITE_SCRAP, .01f, EntityType.STRIDER));
//        this.add("bee", PlusLootModifiers.CHANCE.get(), new ChanceLootModifier(new LootItemCondition[0], PlusItems.STINGER::get, .5f, EntityType.BEE));
//        this.add("turtle", PlusLootModifiers.COOKABLE.get(), new CookableFoodLootModifier(new LootItemCondition[0], PlusItems.TURTLE::get, new IntRange(1, 1), false, EntityType.TURTLE));
    }
}
