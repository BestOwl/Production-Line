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

import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by BestOwl on 2015.10.31.0031.
 *
 * Gti electric generator tile.
 */
public abstract class TileElectricGenerator extends TileContainer implements IEnergySource {

    /** energy output/tick */
    public double powerTick;
    public int fuel = 0;
    /** The number of remaining battery */
    public double energy;
    /** The number of that can storage battery */
    public final int maxEnergy;
    /** Determine the tier of this */
    public int tier;

    public TileElectricGenerator(int tier, int maxEnergy) {
        this.tier = tier;
        this.maxEnergy = maxEnergy;
        this.powerTick = EnergyNet.instance.getPowerFromTier(tier);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.powerTick = nbt.getInteger("fuel");
        this.energy = nbt.getDouble("energy");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setInteger("fuel", this.fuel);
        nbt.setDouble("energy", this.energy);
    }

    /**
     * Draw energy from this source's buffer.
     * If the source doesn't have a buffer, this is a no-op.
     *
     * @param amount amount of EU to draw, may be negative
     */
    @Override
    public void drawEnergy(double amount) {
        this.energy -= amount;
    }

    /**
     * Determine the tier of this energy source.
     * 1 = LV, 2 = MV, 3 = HV, 4 = EV etc.
     *
     * @note Modifying the energy net from this method is disallowed.
     * @return tier of this energy source
     */
    @Override
    public int getSourceTier() {
        return this.tier;
    }

    /**
     * Energy output provided by the source this tick.
     * This is typically Math.min(stored energy, max output/tick).
     *
     * @note Modifying the energy net from this method is disallowed.
     * @return Energy offered this tick
     */
    @Override
    public double getOfferedEnergy() {
        return Math.min(this.energy, this.powerTick);
    }

    /**
     * Determine if this emitter can emit energy to an adjacent receiver.
     *
     * The TileEntity in the receiver parameter is what was originally added to the energy net,
     * which may be normal in-world TileEntity, a delegate or an IMetaDelegate.
     *
     * @param receiver receiver, may also be null or an IMetaDelegate
     * @param direction direction the receiver is from the emitter
     * @return Whether energy should be emitted
     */
    @Override
    public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
        return true;
    }
}
