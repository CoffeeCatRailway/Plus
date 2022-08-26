package io.github.coffeecatrailway.plus.data.gen;

import gg.moonflower.pollen.api.datagen.provider.PollinatedLanguageProvider;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import io.github.coffeecatrailway.plus.Plus;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.EntityType;
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
    public static final Map<Supplier<? extends EntityType<?>>, String> ENTITIES = new HashMap<>();

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

        this.add("item." + Plus.MOD_ID + ".wooden_shield.black", "Black Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.red", "Red Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.green", "Green Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.brown", "Brown Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.blue", "Blue Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.purple", "Purple Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.cyan", "Cyan Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.light_gray", "Light Gray Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.gray", "Gray Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.pink", "Pink Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.lime", "Lime Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.yellow", "Yellow Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.light_blue", "Light Blue Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.magenta", "Magenta Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.orange", "Orange Wooden Shield");
        this.add("item." + Plus.MOD_ID + ".wooden_shield.white", "White Wooden Shield");

        this.add("item." + Plus.MOD_ID + ".stone_shield.black", "Black Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.red", "Red Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.green", "Green Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.brown", "Brown Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.blue", "Blue Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.purple", "Purple Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.cyan", "Cyan Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.light_gray", "Light Gray Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.gray", "Gray Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.pink", "Pink Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.lime", "Lime Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.yellow", "Yellow Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.light_blue", "Light Blue Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.magenta", "Magenta Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.orange", "Orange Stone Shield");
        this.add("item." + Plus.MOD_ID + ".stone_shield.white", "White Stone Shield");

        this.add("item." + Plus.MOD_ID + ".gold_shield.black", "Black Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.red", "Red Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.green", "Green Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.brown", "Brown Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.blue", "Blue Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.purple", "Purple Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.cyan", "Cyan Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.light_gray", "Light Gray Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.gray", "Gray Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.pink", "Pink Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.lime", "Lime Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.yellow", "Yellow Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.light_blue", "Light Blue Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.magenta", "Magenta Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.orange", "Orange Gold Shield");
        this.add("item." + Plus.MOD_ID + ".gold_shield.white", "White Gold Shield");

        this.add("item." + Plus.MOD_ID + ".diamond_shield.black", "Black Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.red", "Red Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.green", "Green Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.brown", "Brown Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.blue", "Blue Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.purple", "Purple Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.cyan", "Cyan Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.light_gray", "Light Gray Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.gray", "Gray Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.pink", "Pink Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.lime", "Lime Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.yellow", "Yellow Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.light_blue", "Light Blue Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.magenta", "Magenta Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.orange", "Orange Diamond Shield");
        this.add("item." + Plus.MOD_ID + ".diamond_shield.white", "White Diamond Shield");

        this.add("item." + Plus.MOD_ID + ".netherite_shield.black", "Black Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.red", "Red Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.green", "Green Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.brown", "Brown Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.blue", "Blue Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.purple", "Purple Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.cyan", "Cyan Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.light_gray", "Light Gray Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.gray", "Gray Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.pink", "Pink Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.lime", "Lime Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.yellow", "Yellow Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.light_blue", "Light Blue Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.magenta", "Magenta Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.orange", "Orange Netherite Shield");
        this.add("item." + Plus.MOD_ID + ".netherite_shield.white", "White Netherite Shield");

        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.black", "Black Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.red", "Red Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.green", "Green Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.brown", "Brown Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.blue", "Blue Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.purple", "Purple Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.cyan", "Cyan Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.light_gray", "Light Gray Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.gray", "Gray Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.pink", "Pink Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.lime", "Lime Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.yellow", "Yellow Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.light_blue", "Light Blue Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.magenta", "Magenta Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.orange", "Orange Rose Gold Shield");
        this.add("item." + Plus.MOD_ID + ".rose_gold_shield.white", "White Rose Gold Shield");

        EXTRA.forEach(this::add);
        ITEMS.forEach(this::addItem);
        BLOCKS.forEach(this::addBlock);
        ENCHANTMENTS.forEach(this::addEnchantment);
        ENTITIES.forEach(this::addEntityType);
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
