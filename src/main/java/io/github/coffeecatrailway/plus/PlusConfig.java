package io.github.coffeecatrailway.plus;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 21/10/2020
 */
public class PlusConfig
{
    public static Server SERVER;
    private static ForgeConfigSpec SERVER_SPEC;

    private static final String CONFIG = "config." + PlusMod.MOD_ID + ".";
    private static final Logger LOGGER = LogManager.getLogger();

    public static class Server
    {
        public final ForgeConfigSpec.DoubleValue heatWalkerLevel;
        public final ForgeConfigSpec.DoubleValue frostWalkerLevel;

        public final ForgeConfigSpec.DoubleValue sawBladeDamage;

        public final ForgeConfigSpec.IntValue turtleScuteDrop;

        public Server(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Server Configurable Settings").push(Lists.newArrayList("item", "enchantment"));
            this.heatWalkerLevel = builder.comment("Default heat walker enchant level. min(16, level + modifier)").translation(CONFIG + "item.enchantment.heatWalkerLevel")
                    .defineInRange("heatWalkerLevel", 2d, 0d, 16d);
            this.frostWalkerLevel = builder.comment("Default frost walker enchant level. min(16, level + modifier)").translation(CONFIG + "item.enchantment.frostWalkerLevel")
                    .defineInRange("frostWalkerLevel", 2d, 0d, 16d);
            builder.pop(2).push("block");
            this.sawBladeDamage = builder.comment("How much damage is dealt to entities standing on a stonecutter/saw bench").translation(CONFIG + "block.sawBladeDamage")
                    .defineInRange("sawBladeDamage", 2d, 0d, Double.MAX_VALUE);
            builder.pop().push("entity");
            this.turtleScuteDrop = builder.comment("How many scute(s) are dropped from turtles becoming adults", "Vanilla value: 1").translation(CONFIG + "entity.turtleScuteDrop")
                            .defineInRange("turtleScuteDrop", 2, 1, Integer.MAX_VALUE);
            builder.pop();
        }

        public static void init(ModLoadingContext context)
        {
            if (SERVER_SPEC == null)
            {
                Pair<Server, ForgeConfigSpec> server = new ForgeConfigSpec.Builder().configure(PlusConfig.Server::new);
                SERVER_SPEC = server.getRight();
                SERVER = server.getLeft();
                LOGGER.info("Register config(s)");
            }
            context.registerConfig(ModConfig.Type.SERVER, SERVER_SPEC);
        }
    }
}
