package net.epoxide.tinker.util;

import net.darkhax.opennbt.tags.CompoundTag;

public interface Persistent {
    
    /**
     * Reads important data from a CompoundTag to reinitialize various fields and properties of
     * the Object. Will likely expect to find the same data written using
     * {@link #writeData(CompoundTag)}.
     * 
     * @param tag A CompoundTag to read all the persistent data from.
     */
    public void readData (CompoundTag tag);
    
    /**
     * Writes all important data from an Object to a CompoundTag so it can later be read using
     * {@link #readData(CompoundTag)}.
     * 
     * @param tag A CompoundTag to write all the persistent data to.
     * @return CompoundTag The data dag that was written to.
     */
    public CompoundTag writeData (CompoundTag tag);
}
