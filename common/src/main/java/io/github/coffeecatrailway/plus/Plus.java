package io.github.coffeecatrailway.plus;

import gg.moonflower.pollen.api.platform.Platform;

public class Plus
{
    public static final String MOD_ID = "plus";
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(Plus::onClientInit)
            .clientPostInit(Plus::onClientPostInit)
            .commonInit(Plus::onCommonInit)
            .commonPostInit(Plus::onCommonPostInit)
            .build();

    public static void onClientInit()
    {
    }

    public static void onClientPostInit(Platform.ModSetupContext ctx)
    {
    }

    public static void onCommonInit()
    {
    }

    public static void onCommonPostInit(Platform.ModSetupContext ctx)
    {
    }
}
