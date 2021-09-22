package io.github.coffeecatrailway.plus;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        public Server(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Server Configurable Settings");
//            this.crackedEggSpawnChance = builder.comment("The chance of a cracked egg dropping from a thrown egg").translation(CONFIG + "item.crackedEggSpawnChance")
//                    .defineInRange("crackedEggSpawnChance", .25d, 0d, 1d);
//            builder.pop();
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
