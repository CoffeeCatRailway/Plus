package io.github.coffeecatrailway.plus.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * @author CoffeeCatRailway
 * Created: 19/12/2021
 */
@Environment(EnvType.CLIENT)
public class PlusModMenu implements ModMenuApi
{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory()
    {
        return ModMenuApi.super.getModConfigScreenFactory();
    }
}
