package io.github.coffeecatrailway.plus.common.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 25/09/2021
 */
public class CookableFoodLootModifier extends LootModifier
{
    protected final Supplier<ItemLike> cookable;
    protected final IntRangeOld meatRange;

    protected final boolean allowLooting;
    protected final ResourceLocation entityId;

    public CookableFoodLootModifier(LootItemCondition[] conditions, Supplier<ItemLike> food, IntRangeOld meatRange, boolean allowLooting, EntityType<?> entity)
    {
        this(conditions, food, meatRange, allowLooting, entity.getRegistryName());
    }

    public CookableFoodLootModifier(LootItemCondition[] conditions, Supplier<ItemLike> food, IntRangeOld meatRange, boolean allowLooting, ResourceLocation entityId)
    {
        super(conditions);
        this.cookable = food;
        this.meatRange = meatRange;
        this.allowLooting = allowLooting;
        this.entityId = entityId;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> loot, LootContext context)
    {
        Entity entity = getEntity(context, this.entityId);
        if (entity == null)
            return loot;

        Random random = context.getRandom();
        ItemStack meat = new ItemStack(this.cookable.get());
        if (entity.isOnFire())
            meat = SmeltItemFunction.smelted().build().apply(meat, context);

        meat.setCount(this.meatRange.randomValue(random));
        if (this.allowLooting)
            meat.setCount(meat.getCount() + Mth.nextInt(random, 0, context.getLootingModifier()));

        loot.add(meat);
        return loot;
    }

    @Nullable
    protected static Entity getEntity(LootContext context, ResourceLocation entityId)
    {
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity == null || !entity.getType().getRegistryName().equals(entityId))
            return null;
        return entity;
    }

    public static class Serializer extends GlobalLootModifierSerializer<CookableFoodLootModifier>
    {
        @Override
        public CookableFoodLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions)
        {
            IntRangeOld meatRange = new IntRangeOld(object);
            Item cookable = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "item")));
            return new CookableFoodLootModifier(conditions, () -> cookable, meatRange, GsonHelper.getAsBoolean(object, "allowLooting"), new ResourceLocation(GsonHelper.getAsString(object, "entity")));
        }

        @Override
        public JsonObject write(CookableFoodLootModifier modifier)
        {
            JsonObject json = this.makeConditions(modifier.conditions);
            json.addProperty("item", modifier.cookable.get().asItem().getRegistryName().toString());
            json.addProperty("min", modifier.meatRange.getMin());
            json.addProperty("max", modifier.meatRange.getMax());
            json.addProperty("allowLooting", modifier.allowLooting);
            json.addProperty("entity", modifier.entityId.toString());
            return json;
        }
    }
}
