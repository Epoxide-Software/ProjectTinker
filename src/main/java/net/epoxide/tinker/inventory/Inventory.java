package net.epoxide.tinker.inventory;

import net.darkhax.opennbt.tags.CompoundTag;

import net.epoxide.tinker.util.ItemObject;

public interface Inventory {
    
    /**
     * Gets an ItemObject in the specified slot. Checks to make sure that the slot is valid for
     * the inventory.
     * 
     * @param pos The slot index to look for.
     * @return ItemObject The ItemObject in the specified slot.
     */
    public ItemObject getItemInPos (int pos);
    
    /**
     * Sets an ItemObject in the specified slot. Checks to make sure that the slot is valid for
     * the inventory.
     * 
     * @param pos The slot index to place the item in.
     * @param item The ItemObject to place in the slot.
     */
    public void setItemInPos (int pos, ItemObject item);
    
    /**
     * Checks if a slot index is in range for the inventory.
     * 
     * @param pos The slot index to look for.
     * @return boolean Whether or not the slot index is valid.
     */
    public boolean isPosValid (int pos);
    
    /**
     * Checks if an ItemObject can be added to a specific position. If this returns false the
     * item won't be placed.
     * 
     * @param pos The slot index to check for.
     * @param item The ItemObject being placed.
     * @return boolean Whether or not the item can be placed.
     */
    public boolean canAddItem (int pos, ItemObject item);
    
    /**
     * Gets a name to display for the inventory.
     * 
     * @return String The name of the inventory.
     */
    public String getNameForInventory ();
    
    /**
     * Reads an Inventory from a CompoundTag.
     * 
     * @param tag The CompoundTag to read from.
     */
    public void readData (CompoundTag tag);
    
    /**
     * Writes the inventory to a CompoundTag.
     * 
     * @param tag The CompoundTag to write to.
     * @return CompoundTag The tag being written to.
     */
    public CompoundTag writeData (CompoundTag tag);
}