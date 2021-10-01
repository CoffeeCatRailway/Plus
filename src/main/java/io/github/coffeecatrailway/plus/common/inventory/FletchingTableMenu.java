package io.github.coffeecatrailway.plus.common.inventory;

import io.github.coffeecatrailway.plus.common.item.crafting.FletchingTableRecipe;
import io.github.coffeecatrailway.plus.registry.PlusMenuTypes;
import io.github.coffeecatrailway.plus.registry.PlusRecipes;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Optional;

/**
 * @author CoffeeCatRailway
 * Created: 1/10/2021
 */
public class FletchingTableMenu extends RecipeBookMenu<CraftingContainer>
{
    private final ContainerLevelAccess access;
//    private long lastSoundTime;
//    private Runnable slotUpdateListener = () -> {};
    public final CraftingContainer container = new CraftingContainer(this, 2, 2);
//    {
//        @Override
//        public void setChanged()
//        {
//            super.setChanged();
//            FletchingTableMenu.this.slotsChanged(this);
//            FletchingTableMenu.this.slotUpdateListener.run();
//        }
//    };
    private final ResultContainer resultContainer = new ResultContainer();
    private final Player player;

    public FletchingTableMenu(int id, Inventory inventory)
    {
        this(id, inventory, ContainerLevelAccess.NULL);
    }

    public FletchingTableMenu(int id, Inventory inventory, ContainerLevelAccess access)
    {
        super(PlusMenuTypes.FLETCHING_TABLE.get(), id);
        this.access = access;
        this.player = inventory.player;

        this.addSlot(new Slot(this.container, 0, 24, 24));
        this.addSlot(new Slot(this.container, 1, 24, 45));
        this.addSlot(new Slot(this.container, 2, 76, 24));
        this.addSlot(new Slot(this.container, 3, 76, 45));
        this.addSlot(new ResultSlot(this.player, this.container, this.resultContainer, 4, 132, 34));
//        {
//            @Override
//            public boolean mayPlace(ItemStack stack)
//            {
//                return false;
//            }
//
//            @Override
//            public ItemStack onTake(Player player, ItemStack stack)
//            {
//                stack.onCraftedBy(player.level, player, stack.getCount());
//                FletchingTableMenu.this.resultContainer.awardUsedRecipes(player);
//
//                return super.onTake(player, stack);
//            }
//        });

        int i;
        for (i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for (i = 0; i < 9; ++i)
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
    }

    @Override
    public void slotsChanged(Container container)
    {
        this.access.execute((level, pos) -> {
            if (!level.isClientSide())
            {
                ServerPlayer player = (ServerPlayer) this.player;
                ItemStack result = ItemStack.EMPTY;
                Optional<FletchingTableRecipe> optional = level.getServer().getRecipeManager().getRecipeFor(PlusRecipes.FLETCHING_TABLE_TYPE, this.container, level);
                if (optional.isPresent())
                {
                    FletchingTableRecipe recipe = optional.get();
                    if (this.resultContainer.setRecipeUsed(level, player, recipe))
                        result = recipe.assemble(this.container);
                }

                this.resultContainer.setItem(4, result);
                player.connection.send(new ClientboundContainerSetSlotPacket(this.containerId, 4, result));
            }
        });
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents contents)
    {
        this.container.fillStackedContents(contents);
    }

    @Override
    public void clearCraftingContent()
    {
        this.container.clearContent();
        this.resultContainer.clearContent();
    }

    @Override
    public boolean recipeMatches(Recipe<? super CraftingContainer> recipe)
    {
        return recipe.matches(this.container, this.player.level);
    }

    @Override
    public void removed(Player player)
    {
        super.removed(player);
        this.access.execute((level, pos) -> this.clearContainer(player, level, this.container));
    }

    @Override
    public boolean stillValid(Player player)
    {
        return stillValid(this.access, player, Blocks.FLETCHING_TABLE);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        return super.quickMoveStack(player, index);
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot)
    {
        return slot.container != this.resultContainer && super.canTakeItemForPickAll(stack, slot);
    }

    @Override
    public int getResultSlotIndex()
    {
        return 4;
    }

    @Override
    public int getGridWidth()
    {
        return this.container.getWidth();
    }

    @Override
    public int getGridHeight()
    {
        return this.container.getHeight();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getSize()
    {
        return 5;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public RecipeBookType getRecipeBookType()
    {
        return RecipeBookType.CRAFTING;
    }
}
