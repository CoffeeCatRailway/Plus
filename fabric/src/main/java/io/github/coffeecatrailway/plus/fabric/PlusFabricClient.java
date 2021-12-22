package io.github.coffeecatrailway.plus.fabric;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.PlusConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * @author CoffeeCatRailway
 * Created: 19/12/2021
 */
@Environment(EnvType.CLIENT)
public class PlusFabricClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        Plus.initClient();
    }
}
