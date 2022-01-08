package io.github.coffeecatrailway.plus.data.gen.forge;

import io.github.coffeecatrailway.plus.Plus;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 17/03/2021
 */
public class PlusLanguage extends LanguageProvider
{
    protected static final Map<String, String> EXTRA = new HashMap<>();
    protected static final Map<Supplier<? extends Item>, String> ITEMS = new HashMap<>();
    protected static final Map<Supplier<? extends Block>, String> BLOCKS = new HashMap<>();
    protected static final Map<Supplier<? extends Enchantment>, String> ENCHANTMENTS = new HashMap<>();

    public PlusLanguage(DataGenerator generator)
    {
        super(generator, Plus.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        this.add("gui." + Plus.MOD_ID + ".jei.category.saw_bench", "Saw Bench");
        this.add("category." + Plus.MOD_ID + ".rei.saw_bench", "Saw Bench");
        EXTRA.forEach(this::add);
        ITEMS.forEach((item, name) -> this.add(item.get(), name));
        BLOCKS.forEach((block, name) -> this.add(block.get(), name));
        ENCHANTMENTS.forEach((enchantment, name) -> this.add(enchantment.get(), name));
    }
}
