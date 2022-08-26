package io.github.coffeecatrailway.plus.registry;

import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.Plus;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 25/08/2022
 */
public class PlusParticles
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final PollinatedRegistry<ParticleType<?>> PARTICLES = PollinatedRegistry.create(Registry.PARTICLE_TYPE, Plus.MOD_ID);

    public static final Supplier<SimpleParticleType> ITEM_PLAYING_CARD = PARTICLES.register("item_playing_card", () -> new SimpleParticleType(false));

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        PARTICLES.register(platform);
    }
}
