/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://github.com/Minecraft-GoodTime>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.mcgoodtime.gti.common.tiles;

import ic2.api.energy.tile.IKineticSource;
import ic2.core.util.StackUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import org.apache.commons.lang3.mutable.MutableObject;

/**
 * The BlockFluidKineticGenerator tile.
 *
 * @author liach
 */
public class TileFluidKineticGenerator extends TileContainer implements IKineticSource, IFluidHandler {

    public final int kuOutput = 32;
    public FluidTank fluidTank = new FluidTank(10000);

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.fluidTank.readFromNBT(nbt.getCompoundTag("fluidTank"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagCompound fluidTag = new NBTTagCompound();
        this.fluidTank.writeToNBT(fluidTag);
        nbt.setTag("fluidTank", fluidTag);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!this.worldObj.isRemote) {
            boolean needUpdate = false;

            if (this.fluidTank.getFluidAmount() <= this.fluidTank.getCapacity()) {

            }

            if (needUpdate) {
                this.markDirty();
            }
        }
    }

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public String getInventoryName() {
        return "FluidKineticGenerator";
    }

    /*
	 *  Return max kinetic energy transmission peer Tick (only theoretical bandwidth not available amount)
	 */
    @Override
    public int maxrequestkineticenergyTick(ForgeDirection directionFrom) {
        return directionFrom.ordinal() != this.facing ? 0 : this.kuOutput;
    }

    /*
	 * @param requested amount of kinetic energy to transfer
	 * @return transmitted amount of kineticenergy
	 *
	 * example: You Request 100 units of kinetic energy but the Source have only 50 units left
	 * requestkineticenergy(100) : return 50 : so 50 units of kinetic energy remove from KineticSource
	 */
    @Override
    public int requestkineticenergy(ForgeDirection directionFrom, int requestKineticenergy) {
        return directionFrom.ordinal() != this.facing ? 0 : Math.min(this.kuOutput, requestKineticenergy);
    }

    /**
     * Fills fluid into internal tanks, distribution is left entirely to the IFluidHandler.
     *
     * @param from     Orientation the Fluid is pumped in from.
     * @param resource FluidStack representing the Fluid and maximum amount of fluid to be filled.
     * @param doFill   If false, fill will only be simulated.
     * @return Amount of resource that was (or would have been, if simulated) filled.
     */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return this.canFill(from, resource.getFluid()) ? this.fluidTank.fill(resource, doFill) : 0;
    }

    /**
     * Drains fluid out of internal tanks, distribution is left entirely to the IFluidHandler.
     *
     * @param from     Orientation the Fluid is drained to.
     * @param resource FluidStack representing the Fluid and maximum amount of fluid to be drained.
     * @param doDrain  If false, drain will only be simulated.
     * @return FluidStack representing the Fluid and amount that was (or would have been, if
     * simulated) drained.
     */
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return resource != null && resource.isFluidEqual(this.fluidTank.getFluid()) ? (!this.canDrain(from, resource.getFluid()) ? null : this.fluidTank.drain(resource.amount, doDrain)) : null;
    }

    /**
     * Drains fluid out of internal tanks, distribution is left entirely to the IFluidHandler.
     * <p/>
     * This method is not Fluid-sensitive.
     *
     * @param from     Orientation the fluid is drained to.
     * @param maxDrain Maximum amount of fluid to drain.
     * @param doDrain  If false, drain will only be simulated.
     * @return FluidStack representing the Fluid and amount that was (or would have been, if
     * simulated) drained.
     */
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return this.fluidTank.drain(maxDrain, doDrain);
    }

    /**
     * Returns true if the given fluid can be inserted into the given direction.
     * <p/>
     * More formally, this should return true if fluid is able to enter from the given direction.
     */
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return true;
    }

    /**
     * Returns true if the given fluid can be extracted from the given direction.
     * <p/>
     * More formally, this should return true if fluid is able to leave from the given direction.
     */
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    /**
     * Returns an array of objects which represent the internal tanks. These objects cannot be used
     * to manipulate the internal tanks. See {@link FluidTankInfo}.
     *
     * @param from Orientation determining which tanks should be queried.
     * @return Info for the relevant internal tanks.
     */
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] {new FluidTankInfo(this.fluidTank.getFluid(), this.fluidTank.getCapacity())};
    }
}