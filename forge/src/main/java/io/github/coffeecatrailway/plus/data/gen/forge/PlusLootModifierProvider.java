package io.github.coffeecatrailway.plus.data.gen.forge;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.loot.forge.PlusLootModifier;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import io.github.coffeecatrailway.plus.registry.forge.PlusLootModifiers;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
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
        this.add("fox", PlusLootModifiers.POOL_LOOT.get(), new PlusLootModifier(new LootItemCondition[0], Plus.getLocation("modifier/fox"), EntityType.FOX));
        this.add("bat", PlusLootModifiers.POOL_LOOT.get(), new PlusLootModifier(new LootItemCondition[0], Plus.getLocation("modifier/bat"), EntityType.BAT));
//        this.add("squid", PlusLootModifiers.COOKABLE.get(), new CookableFoodLootModifier(new LootItemCondition[0], PlusItems.SQUID::get, new IntRangeOld(2, 6), true, EntityType.SQUID));
//        this.add("glow_squid", PlusLootModifiers.COOKABLE.get(), new CookableFoodLootModifier(new LootItemCondition[0], PlusItems.SQUID::get, new IntRangeOld(2, 6), true, EntityType.GLOW_SQUID));
//        this.add("strider", PlusLootModifiers.CHANCE.get(), new ChanceLootModifier(new LootItemCondition[0], () -> Items.NETHERITE_SCRAP, .01f, EntityType.STRIDER));
//        this.add("bee", PlusLootModifiers.CHANCE.get(), new ChanceLootModifier(new LootItemCondition[0], PlusItems.STINGER::get, .5f, EntityType.BEE));
//        this.add("turtle", PlusLootModifiers.COOKABLE.get(), new CookableFoodLootModifier(new LootItemCondition[0], PlusItems.TURTLE::get, new IntRangeOld(1, 1), false, EntityType.TURTLE));
    }
}
