package io.github.coffeecatrailway.plus.data.gen.fabric;

import io.github.coffeecatrailway.plus.Plus;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * @author CoffeeCatRailway
 * Created: 17/06/2022
 */
public class PlusDataEntryPoint implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator)
    {
        Plus.PLATFORM.dataSetup(generator);
    }
}
