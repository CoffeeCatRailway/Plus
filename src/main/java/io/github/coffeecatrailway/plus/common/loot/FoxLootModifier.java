package io.github.coffeecatrailway.plus.common.loot;

import com.google.gson.JsonObject;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.IntRange;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 25/09/2021
 */
public class FoxLootModifier extends LootModifier
{
    private final IntRange furRange;
    private final IntRange meatRange;

    public FoxLootModifier(LootItemCondition[] conditions,  IntRange furRange, IntRange meatRange)
    {
        super(conditions);
        this.furRange = furRange;
        this.meatRange = meatRange;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> loot, LootContext context)
    {
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity instanceof Fox)
        {
            Random random = context.getRandom();
            int furAmount = this.furRange.randomValue(random) + Mth.nextInt(random, 0, context.getLootingModifier());
            switch (((Fox) entity).getFoxType())
            {
                case RED:
                    loot.add(new ItemStack(PlusItems.FOX_FUR.get(), furAmount));
                    break;
                case SNOW:
                    loot.add(new ItemStack(PlusItems.SNOW_FOX_FUR.get(), furAmount));
                    break;
            }

            ItemStack meat = new ItemStack(PlusItems.FOX_MEAT.get());
            if (entity.isOnFire())
                meat = SmeltItemFunction.smelted().build().apply(meat, context);
            meat.setCount(this.meatRange.randomValue(random) + Mth.nextInt(random, 0, context.getLootingModifier()));
            loot.add(meat);
        }
        return loot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<FoxLootModifier>
    {
        @Override
        public FoxLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions)
        {
            IntRange furRange = new IntRange(GsonHelper.getAsInt(object, "minFur"), GsonHelper.getAsInt(object, "maxFur"));
            IntRange meatRange = new IntRange(GsonHelper.getAsInt(object, "minMeat"), GsonHelper.getAsInt(object, "maxMeat"));
            return new FoxLootModifier(conditions, furRange, meatRange);
        }

        @Override
        public JsonObject write(FoxLootModifier modifier)
        {
            JsonObject json = this.makeConditions(modifier.conditions);
            json.addProperty("minFur", modifier.furRange.getMinInclusive());
            json.addProperty("maxFur", modifier.furRange.getMaxInclusive());
            json.addProperty("minMeat", modifier.meatRange.getMinInclusive());
            json.addProperty("maxMeat", modifier.meatRange.getMaxInclusive());
            return json;
        }
    }
}
