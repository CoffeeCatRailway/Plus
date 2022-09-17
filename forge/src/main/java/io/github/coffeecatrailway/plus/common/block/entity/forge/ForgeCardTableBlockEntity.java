package io.github.coffeecatrailway.plus.common.block.entity.forge;

import io.github.coffeecatrailway.plus.common.block.entity.CardTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 28/08/2022
 */
public class ForgeCardTableBlockEntity extends CardTableBlockEntity
{
    private LazyOptional<IItemHandlerModifiable> handler;

    public ForgeCardTableBlockEntity(BlockPos pos, BlockState state)
    {
        super(pos, state);
    }

    @Override
    public void setBlockState(BlockState state)
    {
        super.setBlockState(state);
        if (this.handler != null)
        {
            LazyOptional<?> oldHandler = this.handler;
            this.handler = null;
            oldHandler.invalidate();
        }
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if (!this.remove && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            if (this.handler == null)
                this.handler = LazyOptional.of(() -> new InvWrapper(this));
            return this.handler.cast();
        } else return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();
        if (this.handler != null)
        {
            this.handler.invalidate();
            this.handler = null;
        }
    }
}
