package io.github.coffeecatrailway.plus.common.loot.forge;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 9/01/2022
 */
public class PlusLootModifier extends LootModifier
{
    private final ResourceLocation lootTable;
    protected final ResourceLocation entityId;

    public PlusLootModifier(LootItemCondition[] conditions, ResourceLocation lootTable, EntityType<?> entity)
    {
        this(conditions, lootTable, entity.getRegistryName());
    }

    public PlusLootModifier(LootItemCondition[] conditions, ResourceLocation lootTable, ResourceLocation entityId)
    {
        super(conditions);
        this.lootTable = lootTable;
        this.entityId = entityId;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> list, LootContext context)
    {
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity == null || !entity.getType().getRegistryName().equals(entityId))
            return list;

        context.getLootTable(this.lootTable).getRandomItems(context, list::add);
        return list;
    }

    public static class Serializer extends GlobalLootModifierSerializer<PlusLootModifier>
    {
        public Serializer()
        {
        }

        @Override
        public PlusLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions)
        {
            return new PlusLootModifier(conditions, new ResourceLocation(GsonHelper.getAsString(object, "lootTable")), new ResourceLocation(GsonHelper.getAsString(object, "entity")));
        }

        @Override
        public JsonObject write(PlusLootModifier modifier)
        {
            JsonObject object = this.makeConditions(modifier.conditions);
            object.addProperty("lootTable", modifier.lootTable.toString());
            object.addProperty("entity", modifier.entityId.toString());
            return object;
        }
    }
}
