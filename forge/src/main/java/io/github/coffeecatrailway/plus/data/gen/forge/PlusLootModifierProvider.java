package io.github.coffeecatrailway.plus.data.gen.forge;

import io.github.coffeecatrailway.plus.Plus;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

/**
 * @author CoffeeCatRailway
 * Created: 25/09/2021
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
//        this.add("fox", PlusLootModifiers.FOX.get(), new FoxLootModifier(new LootItemCondition[0], new IntRangeOld(1, 4), new IntRangeOld(1, 3)));
//        this.add("bat", PlusLootModifiers.COOKABLE.get(), new CookableFoodLootModifier(new LootItemCondition[0], PlusItems.BAT::get, new IntRangeOld(1, 1), false, EntityType.BAT));
//        this.add("squid", PlusLootModifiers.COOKABLE.get(), new CookableFoodLootModifier(new LootItemCondition[0], PlusItems.SQUID::get, new IntRangeOld(2, 6), true, EntityType.SQUID));
//        this.add("glow_squid", PlusLootModifiers.COOKABLE.get(), new CookableFoodLootModifier(new LootItemCondition[0], PlusItems.SQUID::get, new IntRangeOld(2, 6), true, EntityType.GLOW_SQUID));
//        this.add("strider", PlusLootModifiers.CHANCE.get(), new ChanceLootModifier(new LootItemCondition[0], () -> Items.NETHERITE_SCRAP, .01f, EntityType.STRIDER));
//        this.add("bee", PlusLootModifiers.CHANCE.get(), new ChanceLootModifier(new LootItemCondition[0], PlusItems.STINGER::get, .5f, EntityType.BEE));
//        this.add("turtle", PlusLootModifiers.COOKABLE.get(), new CookableFoodLootModifier(new LootItemCondition[0], PlusItems.TURTLE::get, new IntRangeOld(1, 1), false, EntityType.TURTLE));
    }
}
