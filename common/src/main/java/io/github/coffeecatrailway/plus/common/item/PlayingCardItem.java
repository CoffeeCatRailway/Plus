package io.github.coffeecatrailway.plus.common.item;

import io.github.coffeecatrailway.plus.common.entity.PlayingCardEntity;
import io.github.coffeecatrailway.plus.registry.PlusItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Locale;

public class PlayingCardItem extends Item
{
    @Nullable
    private final Suit suit;
    private final Number number;

    public PlayingCardItem(Properties properties, @Nullable Suit suit, Number number)
    {
        super(properties);
        this.suit = suit;
        this.number = number;
    }

    @Nullable
    public Suit getSuit()
    {
        return this.suit;
    }

    public Number getNumber()
    {
        return this.number;
    }

    public boolean isRed()
    {
        if (this.suit != null)
            return this.suit.equals(Suit.DIAMONDS) || this.suit.equals(Suit.HEARTS);
        return this.number.equals(Number.JOKER) && this == PlusItems.CARD_JOKER_RED.get();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, .5f, .4f / (level.getRandom().nextFloat() * .4f + .8f));
        if (!level.isClientSide)
        {
            PlayingCardEntity cardEntity = new PlayingCardEntity(level, player, (PlayingCardItem) stack.getItem());
            cardEntity.setItem(stack);
            cardEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0f, 1.5f, 1f);
            level.addFreshEntity(cardEntity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild)
            stack.shrink(1);

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    public enum Suit
    {
        SPADES, DIAMONDS, CLUBS, HEARTS;

        public String getName()
        {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }

    public enum Number
    {
        JOKER(0),
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13);

        private final int numerical;

        Number(int numerical)
        {
            this.numerical = numerical;
        }

        public String getName()
        {
            return this.name().toLowerCase(Locale.ROOT);
        }

        public int getNumerical()
        {
            return this.numerical;
        }
    }
}
