package io.github.coffeecatrailway.plus.client.particle;

import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BreakingItemParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 14/09/2022
 */
@Environment(EnvType.CLIENT)
public class PlayingCardParticleProvider implements ParticleProvider<SimpleParticleType>
{
    @Nullable
    @Override
    public Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i)
    {
        return new BreakingItemParticle(clientLevel, d, e, f, new ItemStack(PlusItems.CARD_JOKER_BLACK.get()));
    }
}
