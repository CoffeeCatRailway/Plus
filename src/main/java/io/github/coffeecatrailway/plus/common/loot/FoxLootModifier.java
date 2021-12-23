package io.github.coffeecatrailway.plus.common.loot;

import com.google.gson.JsonObject;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 25/09/2021
 */
public class FoxLootModifier extends CookableFoodLootModifier
{
    private final IntRangeOld furRange;

    public FoxLootModifier(LootItemCondition[] conditions, IntRangeOld furRange, IntRangeOld meatRange)
    {
        super(conditions, PlusItems.FOX_MEAT::get, meatRange, true, EntityType.FOX);
        this.furRange = furRange;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> loot, LootContext context)
    {
        Entity entity = getEntity(context, this.entityId);
        if (!(entity instanceof Fox))
            return super.doApply(loot, context);

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
        return super.doApply(loot, context);
    }

    public static class Serializer extends GlobalLootModifierSerializer<FoxLootModifier>
    {
        @Override
        public FoxLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions)
        {
            IntRangeOld furRange = new IntRangeOld(object.getAsJsonObject("furRange"));
            IntRangeOld meatRange = new IntRangeOld(object.getAsJsonObject("meatRange"));
            return new FoxLootModifier(conditions, furRange, meatRange);
        }

        @Override
        public JsonObject write(FoxLootModifier modifier)
        {
            JsonObject json = this.makeConditions(modifier.conditions);
            JsonObject meatRange = new JsonObject();
            meatRange.addProperty("min", modifier.meatRange.getMin());
            meatRange.addProperty("max", modifier.meatRange.getMax());
            json.add("meatRange", meatRange);
            JsonObject furRange = new JsonObject();
            furRange.addProperty("min", modifier.furRange.getMin());
            furRange.addProperty("max", modifier.furRange.getMax());
            json.add("furRange", furRange);
            return json;
        }
    }
}
