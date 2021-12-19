package io.github.coffeecatrailway.plus.common.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.IntRange;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 25/09/2021
 */
public class ChanceLootModifier extends LootModifier
{
    private static final Logger LOGGER = LogManager.getLogger();

    protected final Supplier<ItemLike> item;
    protected final float chance;
    protected final ResourceLocation entityId;

    public ChanceLootModifier(LootItemCondition[] conditions, Supplier<ItemLike> item, float chance, EntityType<?> entity)
    {
        this(conditions, item, chance, entity.getRegistryName());
    }

    public ChanceLootModifier(LootItemCondition[] conditions, Supplier<ItemLike> item, float chance, ResourceLocation entityId)
        {
        super(conditions);
        this.item = item;
        this.chance = Mth.clamp(chance, 0f, 1f);
        if (chance < 0f || chance > 1f)
            LOGGER.error("Drop chance was out of bounds '{}'! Corrected to '{}'!", chance, this.chance);
            this.entityId = entityId;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> loot, LootContext context)
    {
        Entity entity = CookableFoodLootModifier.getEntity(context, this.entityId);
        if (entity == null)
            return loot;

        if (Mth.nextFloat(context.getRandom(), 0f, 1f) <= this.chance)
            loot.add(new ItemStack(this.item.get()));
        return loot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<ChanceLootModifier>
    {
        @Override
        public ChanceLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions)
        {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "item")));
            return new ChanceLootModifier(conditions, () -> item, GsonHelper.getAsFloat(object, "chance"), new ResourceLocation(GsonHelper.getAsString(object, "entity")));
        }

        @Override
        public JsonObject write(ChanceLootModifier modifier)
        {
            JsonObject json = this.makeConditions(modifier.conditions);
            json.addProperty("item", modifier.item.get().asItem().getRegistryName().toString());
            json.addProperty("chance", modifier.chance);
            json.addProperty("entity", modifier.entityId.toString());
            return json;
        }
    }
}
