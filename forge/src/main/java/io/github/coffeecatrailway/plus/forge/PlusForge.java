package io.github.coffeecatrailway.plus.forge;

import io.github.coffeecatrailway.plus.Plus;
import net.minecraftforge.fml.common.Mod;

@Mod(Plus.MOD_ID)
public class PlusForge
{
    public PlusForge()
    {
        Plus.PLATFORM.setup();
    }
}
