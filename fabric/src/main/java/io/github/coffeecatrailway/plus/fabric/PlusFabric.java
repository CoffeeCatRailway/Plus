package io.github.coffeecatrailway.plus.fabric;

import io.github.coffeecatrailway.plus.Plus;
import net.fabricmc.api.ModInitializer;

public class PlusFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        Plus.PLATFORM.setup();
    }
}
