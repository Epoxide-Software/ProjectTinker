package net.epoxide.tinker.inventory;

import net.darkhax.opennbt.tags.CompoundTag;

import net.epoxide.tinker.util.ItemObject;

public class Inventory {
    
    /**
     * An array of ItemObject's held by the inventory.
     */
    private ItemObject[] inventory;
    
    /**
     * Constructs a new inventory of the specific size.
     * 
     * @param size The size for the inventory.
     */
    public Inventory(int size) {
        
        this.inventory = new ItemObject[size];
    }
    
    /**
     * Gets an ItemObject in the specified slot. Checks to make sure that the slot is valid for
     * the inventory.
     * 
     * @param pos The slot index to look for.
     * @return ItemObject The ItemObject in the specified slot.
     */
    public ItemObject getItemInPos (int pos) {
        
        return this.isPosValid(pos) ? this.inventory[pos] : null;
    }
    
    /**
     * Sets an ItemObject in the specified slot. Checks to make sure that the slot is valid for
     * the inventory.
     * 
     * @param pos The slot index to place the item in.
     * @param item The ItemObject to place in the slot.
     */
    public void setItemInPos (int pos, ItemObject item) {
        
        if (this.isPosValid(pos))
            this.inventory[pos] = item;
    }
    
    /**
     * Checks if a slot index is in range for the inventory.
     * 
     * @param pos The slot index to look for.
     * @return boolean Whether or not the slot index is valid.
     */
    public boolean isPosValid (int pos) {
        
        return pos >= 0 && pos < this.inventory.length;
    }
    
    /**
     * Reads an Inventory from a CompoundTag.
     * 
     * @param tag The CompoundTag to read from.
     */
    public void readData (CompoundTag tag) {
        
        this.inventory = new ItemObject[tag.getInt("Size")];
        
        for (int index = 0; index < this.inventory.length; index++) {
            
            final CompoundTag slotTag = tag.getCompoundTag("Slot" + index);
            
            if (slotTag != null)
                this.inventory[index] = new ItemObject(slotTag);
        }
    }
    
    /**
     * Writes the inventory to a CompoundTag.
     * 
     * @param tag The CompoundTag to write to.
     * @return CompoundTag The tag being written to.
     */
    public CompoundTag writeData (CompoundTag tag) {
        
        tag.setInt("Size", this.inventory.length);
        
        for (int index = 0; index < this.inventory.length; index++)
            if (this.isPosValid(index))
                tag.setCompoundTag("Slot" + index, this.inventory[index].writeData(new CompoundTag("Slot" + index)));
                
        return tag;
    }
}