package io.github.coffeecatrailway.plus.common.loot;

import com.google.gson.JsonObject;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Fox;
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
 * Created: 25/09/2021
 */
public class FoxLootModifier extends LootModifier
{
    private final int minFur;
    private final int maxFur;

    public FoxLootModifier(LootItemCondition[] conditions, int minFur, int maxFur)
    {
        super(conditions);
        this.minFur = minFur;
        this.maxFur = maxFur;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> loot, LootContext context)
    {
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity instanceof Fox)
        {
            int furAmount = Mth.nextInt(context.getRandom(), this.minFur, this.maxFur + context.getLootingModifier());
            switch (((Fox) entity).getFoxType())
            {
                case RED:
                    loot.add(new ItemStack(PlusItems.FOX_FUR.get(), furAmount));
                    break;
                case SNOW:
                    loot.add(new ItemStack(PlusItems.SNOW_FOX_FUR.get(), furAmount));
                    break;
            }
        }
        return loot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<FoxLootModifier>
    {
        @Override
        public FoxLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions)
        {
            return new FoxLootModifier(conditions, GsonHelper.getAsInt(object, "minFur"), GsonHelper.getAsInt(object, "maxFur"));
        }

        @Override
        public JsonObject write(FoxLootModifier modifier)
        {
            JsonObject json = this.makeConditions(modifier.conditions);
            json.addProperty("minFur", modifier.minFur);
            json.addProperty("maxFur", modifier.maxFur);
            return json;
        }
    }
}
