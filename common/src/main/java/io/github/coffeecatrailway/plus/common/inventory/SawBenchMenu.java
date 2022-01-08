package io.github.coffeecatrailway.plus.common.inventory;

import com.google.common.collect.Lists;
import io.github.coffeecatrailway.plus.common.item.crafting.SawBenchRecipe;
import io.github.coffeecatrailway.plus.registry.PlusBlocks;
import io.github.coffeecatrailway.plus.registry.PlusMenuTypes;
import io.github.coffeecatrailway.plus.registry.PlusRecipes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 27/09/2021
 * <p>Base off {@link StonecutterMenu}</p>
 */
public class SawBenchMenu extends AbstractContainerMenu
{
    private final ContainerLevelAccess access;
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    private final Level level;
    private List<SawBenchRecipe> recipes = Lists.newArrayList();
    private ItemStack input = ItemStack.EMPTY;
    private long lastSoundTime;
    final Slot inputSlot;
    final Slot resultSlot;
    private Runnable slotUpdateListener = () -> {
    };
    public final Container container = new SimpleContainer(1)
    {
        @Override
        public void setChanged()
        {
            super.setChanged();
            SawBenchMenu.this.slotsChanged(this);
            SawBenchMenu.this.slotUpdateListener.run();
        }
    };
    private final ResultContainer resultContainer = new ResultContainer();

    public SawBenchMenu(int id, Inventory inventory)
    {
        this(id, inventory, ContainerLevelAccess.NULL);
    }

    public SawBenchMenu(int id, Inventory inventory, ContainerLevelAccess access)
    {
        super(PlusMenuTypes.SAW_BENCH.get(), id);
        this.access = access;
        this.level = inventory.player.level;
        this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33));
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33)
        {
            @Override
            public boolean mayPlace(ItemStack stack)
            {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack)
            {
                stack.onCraftedBy(player.level, player, stack.getCount());
                SawBenchMenu.this.resultContainer.awardUsedRecipes(player);
                ItemStack less = SawBenchMenu.this.inputSlot.remove(1);
                if (!less.isEmpty())
                    SawBenchMenu.this.setupResultSlot();

                access.execute((level_, pos) -> {
                    long gameTime = level_.getGameTime();
                    if (SawBenchMenu.this.lastSoundTime != gameTime)
                    {
                        level_.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1f, 1f);
                        SawBenchMenu.this.lastSoundTime = gameTime;
                    }
                });
            }
        });

        int i;
        for (i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for (i = 0; i < 9; i++)
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));

        this.addDataSlot(this.selectedRecipeIndex);
    }

    public int getSelectedRecipeIndex()
    {
        return this.selectedRecipeIndex.get();
    }

    public List<SawBenchRecipe> getRecipes()
    {
        return this.recipes;
    }

    public int getNumRecipes()
    {
        return this.recipes.size();
    }

    public boolean hasInputItem()
    {
        return this.inputSlot.hasItem() && !this.recipes.isEmpty();
    }

    @Override
    public boolean stillValid(Player player)
    {
        return stillValid(this.access, player, PlusBlocks.SAW_BENCH.get());
    }

    @Override
    public boolean clickMenuButton(Player player, int index)
    {
        if (this.isValidRecipeIndex(index))
        {
            this.selectedRecipeIndex.set(index);
            this.setupResultSlot();
        }
        return true;
    }

    private boolean isValidRecipeIndex(int index)
    {
        return index >= 0 && index < this.recipes.size();
    }

    @Override
    public void slotsChanged(Container menu)
    {
        ItemStack stack = this.inputSlot.getItem();
        if (stack.getItem() != this.input.getItem())
        {
            this.input = stack.copy();
            this.setupRecipeList(menu, stack);
        }
    }

    private void setupRecipeList(Container menu, ItemStack stack)
    {
        this.recipes.clear();
        this.selectedRecipeIndex.set(-1);
        this.resultSlot.set(ItemStack.EMPTY);
        if (!stack.isEmpty())
            this.recipes = this.level.getRecipeManager().getRecipesFor(PlusRecipes.SAW_BENCH_TYPE.get(), menu, this.level);
    }

    private void setupResultSlot()
    {
        if (!this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get()))
        {
            SawBenchRecipe recipe = this.recipes.get(this.selectedRecipeIndex.get());
            this.resultContainer.setRecipeUsed(recipe);
            this.resultSlot.set(recipe.assemble(this.container));
        } else
            this.resultSlot.set(ItemStack.EMPTY);
        this.broadcastChanges();
    }

    @Override
    public MenuType<?> getType()
    {
        return PlusMenuTypes.SAW_BENCH.get();
    }

    public void registerUpdateListener(Runnable listener)
    {
        this.slotUpdateListener = listener;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot)
    {
        return slot.container != this.resultContainer && super.canTakeItemForPickAll(stack, slot);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem())
        {
            ItemStack slotStack = slot.getItem();
            Item slotItem = slotStack.getItem();
            stack = slotStack.copy();
            if (index == 1)
            {
                slotItem.onCraftedBy(slotStack, player.level, player);
                if (!this.moveItemStackTo(slotStack, 2, 38, true))
                    return ItemStack.EMPTY;

                slot.onQuickCraft(slotStack, stack);
            } else if (index == 0)
            {
                if (!this.moveItemStackTo(slotStack, 2, 38, false))
                    return ItemStack.EMPTY;
            } else if (this.level.getRecipeManager().getRecipeFor(RecipeType.STONECUTTING, new SimpleContainer(slotStack), this.level).isPresent())
            {
                if (!this.moveItemStackTo(slotStack, 0, 1, false))
                    return ItemStack.EMPTY;
            } else if (index >= 2 && index < 29)
            {
                if (!this.moveItemStackTo(slotStack, 29, 38, false))
                    return ItemStack.EMPTY;
            } else if (index >= 29 && index < 38 && !this.moveItemStackTo(slotStack, 2, 29, false))
                return ItemStack.EMPTY;

            if (slotStack.isEmpty())
                slot.set(ItemStack.EMPTY);

            slot.setChanged();
            if (slotStack.getCount() == stack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, slotStack);
            this.broadcastChanges();
        }

        return stack;
    }

    @Override
    public void removed(Player player)
    {
        super.removed(player);
        this.resultContainer.removeItemNoUpdate(1);
        this.access.execute((level, pos) -> this.clearContainer(player, this.container));
    }
}
