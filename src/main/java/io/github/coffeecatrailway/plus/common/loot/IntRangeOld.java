package io.github.coffeecatrailway.plus.common.loot;

import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;

import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 23/12/2021
 */
public class IntRangeOld
{
    final int min;
    final int max;

    public IntRangeOld(int min, int max)
    {
        this.min = min;
        this.max = max;
    }

    public IntRangeOld(JsonObject object)
    {
        this.min = GsonHelper.getAsInt(object, "min");
        this.max = GsonHelper.getAsInt(object, "max");
    }

    public int randomValue(Random random)
    {
        return Mth.nextInt(random, this.min, this.max);
    }

    public int getMin()
    {
        return min;
    }

    public int getMax()
    {
        return max;
    }
}
