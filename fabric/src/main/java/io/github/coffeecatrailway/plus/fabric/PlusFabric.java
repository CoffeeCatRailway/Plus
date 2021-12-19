package io.github.coffeecatrailway.plus.fabric;

import io.github.coffeecatrailway.plus.Plus;
import net.fabricmc.api.ModInitializer;

/**
 * @author CoffeeCatRailway
 * Created: 19/12/2021
 */
public class PlusFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        Plus.init();
    }
}
