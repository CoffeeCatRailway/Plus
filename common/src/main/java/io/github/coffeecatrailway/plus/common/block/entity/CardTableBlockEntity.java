package io.github.coffeecatrailway.plus.common.block.entity;

import io.github.coffeecatrailway.plus.Plus;
import io.github.coffeecatrailway.plus.common.inventory.CardTableMenu;
import io.github.coffeecatrailway.plus.registry.PlusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author CoffeeCatRailway
 * Created: 28/08/2022
 */
public class CardTableBlockEntity extends BaseContainerBlockEntity
{
    private final NonNullList<ItemStack> inventory;

    public CardTableBlockEntity(BlockPos pos, BlockState state)
    {
        super(PlusBlockEntities.CARD_TABLE.get(), pos, state);
        this.inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    }

    @Override
    protected Component getDefaultName()
    {
        return new TranslatableComponent("container." + Plus.MOD_ID + ".card_table");
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CardTableBlockEntity blockEntity)
    {

    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory)
    {
        return new CardTableMenu(id, inventory, this);
    }

    public NonNullList<ItemStack> getInventory()
    {
        return inventory;
    }

    @Override
    public int getContainerSize()
    {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty()
    {
        for (int i = 0; i < this.getContainerSize(); i++)
            if (!this.inventory.get(i).isEmpty())
                return false;
        return true;
    }

    @Override
    public ItemStack getItem(int index)
    {
        return this.inventory.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int amount)
    {
        this.markUpdated();
        return index >= 0 && index < this.getContainerSize() && !this.getItem(index).isEmpty() && amount > 0 ? this.getItem(index).split(amount) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index)
    {
        if (index >= 0 && index < this.getContainerSize())
        {
            ItemStack stack = this.getItem(index).copy();
            this.inventory.set(index, ItemStack.EMPTY);
            return stack;
        } else
            return ItemStack.EMPTY;
    }

    @Override
    public void setItem(int index, ItemStack stack)
    {
        this.inventory.set(index, stack);
        if (stack.getCount() > this.getMaxStackSize())
            stack.setCount(this.getMaxStackSize());
        this.markUpdated();
    }

    @Override
    public boolean stillValid(Player player)
    {
        return this.hasLevel() && this.getLevel().getBlockEntity(this.getBlockPos()) == this && player.distanceToSqr((double) this.getBlockPos().getX() + .5d, (double) this.getBlockPos().getY() + .5d, (double) this.getBlockPos().getZ() + .5d) <= 64d;
    }

    @Override
    public void clearContent()
    {
        for (int i = 0; i < this.getContainerSize(); i++)
            this.setItem(i, ItemStack.EMPTY);
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        ContainerHelper.loadAllItems(tag, this.inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, this.inventory);
    }

    public void markUpdated()
    {
        this.setChanged();
        if (this.hasLevel())
            this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    @Override
    public CompoundTag getUpdateTag()
    {
        return this.saveWithoutMetadata();
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
