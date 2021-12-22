package io.github.coffeecatrailway.plus;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

/**
 * @author CoffeeCatRailway
 * Created: 20/12/2021
 */
@Config(name = Plus.MOD_ID + "-server")
public class PlusConfig extends PartitioningSerializer.GlobalData
{
    @ConfigEntry.Category("items")
    @ConfigEntry.Gui.TransitiveObject
    public Items items = new Items();
    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.TransitiveObject
    public Blocks blocks = new Blocks();
    @ConfigEntry.Category("entities")
    @ConfigEntry.Gui.TransitiveObject
    public Entities entities = new Entities();

    @Config(name = "items")
    public static class Items implements ConfigData
    {
        @Comment("Default heat walker enchant level. min(16, level + modifier")
        @ConfigEntry.BoundedDiscrete(max = 16L)
        public double heatWalkerLevel;
        @Comment("Default frost walker enchant level. min(16, level + modifier")
        @ConfigEntry.BoundedDiscrete(max = 16L)
        public double frostWalkerLevel;

        public Items()
        {
            this.heatWalkerLevel = 2d;
            this.frostWalkerLevel = 2d;
        }
    }

    @Config(name = "blocks")
    public static class Blocks implements ConfigData
    {
        @Comment("How much damage is dealt to entities standing on a stonecutter/saw bench")
        @ConfigEntry.BoundedDiscrete(max = Long.MAX_VALUE)
        public double sawBladeDamage;

        public Blocks()
        {
            this.sawBladeDamage = 2d;
        }
    }

    @Config(name = "entities")
    public static class Entities implements ConfigData
    {
        @Comment("How many scute(s) are dropped from turtles becoming adults, Vanilla value: 1")
        @ConfigEntry.BoundedDiscrete(min = 1L, max = Long.MAX_VALUE)
        public int turtleScuteDrop;

        public Entities()
        {
            this.turtleScuteDrop = 2;
        }
    }
}
