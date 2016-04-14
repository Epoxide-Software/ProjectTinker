package net.epoxide.tinker.util;

import net.darkhax.ess.DataCompound;

public interface Persistent {
    
    /**
     * Reads important data from a CompoundTag to reinitialize various fields and properties of
     * the Object. Will likely expect to find the same data written using
     * {@link #writeData(CompoundTag)}.
     * 
     * @param tag A CompoundTag to read all the persistent data from.
     */
    void readData (DataCompound tag);
    
    /**
     * Writes all important data from an Object to a CompoundTag so it can later be read using
     * {@link #readData(CompoundTag)}.
     * 
     * @param tag A CompoundTag to write all the persistent data to.
     * @return CompoundTag The data dag that was written to.
     */
    DataCompound writeData (DataCompound tag);
}
