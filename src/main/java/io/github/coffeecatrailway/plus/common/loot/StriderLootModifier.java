package io.github.coffeecatrailway.plus.common.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
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
public class StriderLootModifier extends LootModifier
{
    private static final Logger LOGGER = LogManager.getLogger();

    protected final Supplier<ItemLike> item;
    protected final float chance;

    public StriderLootModifier(LootItemCondition[] conditions, Supplier<ItemLike> item, float chance)
    {
        super(conditions);
        this.item = item;
        this.chance = Mth.clamp(chance, 0f, 100f);
        if (chance < 0f || chance > 100f)
            LOGGER.error("Strider drop chance was out of bounds '{}'! Corrected to '{}'!", chance, this.chance);
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> loot, LootContext context)
    {
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity instanceof Strider && Mth.nextFloat(context.getRandom(), 0f, 100f) <= this.chance)
            loot.add(new ItemStack(this.item.get()));
        return loot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<StriderLootModifier>
    {
        @Override
        public StriderLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions)
        {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "item")));
            return new StriderLootModifier(conditions, () -> item, GsonHelper.getAsFloat(object, "chance"));
        }

        @Override
        public JsonObject write(StriderLootModifier modifier)
        {
            JsonObject json = this.makeConditions(modifier.conditions);
            json.addProperty("item", modifier.item.get().asItem().getRegistryName().toString());
            json.addProperty("chance", modifier.chance);
            return json;
        }
    }
}
