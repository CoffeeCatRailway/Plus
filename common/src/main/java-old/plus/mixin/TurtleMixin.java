package io.github.coffeecatrailway.plus.mixin;

import io.github.coffeecatrailway.plus.PlusConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author CoffeeCatRailway
 * Created: 30/09/2021
 */
@Mixin(Turtle.class)
public abstract class TurtleMixin extends Animal
{
    public TurtleMixin(EntityType<? extends Animal> type, Level level)
    {
        super(type, level);
    }

    @Inject(method = "ageBoundaryReached()V", at = @At(value = "TAIL"))
    protected void ageBoundaryReached(CallbackInfo callback)
    {
        int amount = PlusConfig.SERVER.turtleScuteDrop.get() - 1;
        if (amount > 0 && !this.isBaby() && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
            this.spawnAtLocation(Items.SCUTE, amount);
    }
}
