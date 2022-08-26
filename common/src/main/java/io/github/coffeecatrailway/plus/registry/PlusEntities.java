package io.github.coffeecatrailway.plus.registry;

import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedEntityRegistry;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.entity.PlayingCardEntity;
import io.github.coffeecatrailway.plus.data.gen.PlusLanguage;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 25/08/2022
 */
public class PlusEntities
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected static final PollinatedEntityRegistry ENTITIES = PollinatedRegistry.createEntity(Plus.MOD_ID);

    public static final Supplier<EntityType<PlayingCardEntity>> PLAYING_CARD = register("playing_card", PlayingCardEntity::new, MobCategory.MISC, builder -> builder.sized(.25f, .25f).clientTrackingRange(4).updateInterval(10));

    private static <T extends Entity> Supplier<EntityType<T>> register(String id, BiFunction<EntityType<T>, Level, T> entityFactory, MobCategory category, Function<EntityType.Builder<T>, EntityType.Builder<T>> factory)
    {
        Supplier<EntityType<T>> object = ENTITIES.register(id, () -> factory.apply(EntityType.Builder.of(entityFactory::apply, category)).build(Plus.getLocation(id).toString()));
        PlusLanguage.ENTITIES.put(object, PlusLanguage.capitalize(id));
        return object;
    }

    public static void load(Platform platform)
    {
        LOGGER.debug("Loaded");
        ENTITIES.register(platform);
    }
}
