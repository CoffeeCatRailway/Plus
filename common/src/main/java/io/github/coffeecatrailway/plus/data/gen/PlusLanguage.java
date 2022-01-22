package io.github.coffeecatrailway.plus.data.gen;

import gg.moonflower.pollen.api.datagen.provider.PollinatedLanguageProvider;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import io.github.coffeecatrailway.plus.Plus;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 17/03/2021
 */
public class PlusLanguage extends PollinatedLanguageProvider
{
    public static final Map<String, String> EXTRA = new HashMap<>();
    public static final Map<Supplier<? extends Item>, String> ITEMS = new HashMap<>();
    public static final Map<Supplier<? extends Block>, String> BLOCKS = new HashMap<>();
    public static final Map<Supplier<? extends Enchantment>, String> ENCHANTMENTS = new HashMap<>();

    public PlusLanguage(DataGenerator generator, PollinatedModContainer container)
    {
        super(generator, container, "en_us");
    }

    @Override
    protected void registerTranslations()
    {
        this.add("gui." + Plus.MOD_ID + ".jei.category.saw_bench", "Saw Bench");
        this.add("category." + Plus.MOD_ID + ".rei.saw_bench", "Saw Bench");
        this.add("item." + Plus.MOD_ID + ".warmth_amulet.use1", "Prevents freezing when in Powdered Snow");
        this.add("item." + Plus.MOD_ID + ".warmth_amulet.use2", "Melts ice within %s blocks");
        EXTRA.forEach(this::add);
        ITEMS.forEach(this::addItem);
        BLOCKS.forEach(this::addBlock);
        ENCHANTMENTS.forEach(this::addEnchantment);
    }

    public static String capitalize(String id)
    {
        String[] names = id.split("_");
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String name : names)
        {
            builder.append(name.substring(0, 1).toUpperCase()).append(name.substring(1));
            i++;
            if (i != names.length)
                builder.append(" ");
        }
        return builder.toString();
    }
}
