package net.epoxide.tinker.inventory;

import net.darkhax.opennbt.tags.CompoundTag;

import net.epoxide.tinker.util.ItemObject;

public class BasicInventory implements Inventory {
    
    /**
     * An array of ItemObject's held by the inventory.
     */
    private ItemObject[] inventory;
    
    private String inventoryName;
    
    /**
     * Constructs a new inventory of the specific size.
     * 
     * @param size The size for the inventory.
     */
    public BasicInventory(int size) {
        
        this.inventory = new ItemObject[size];
    }
    
    @Override
    public ItemObject getItemInPos (int pos) {
        
        return this.isPosValid(pos) ? this.inventory[pos] : null;
    }
    
    @Override
    public void setItemInPos (int pos, ItemObject item) {
        
        if (this.isPosValid(pos) && this.canAddItem(pos, item))
            this.inventory[pos] = item;
    }
    
    @Override
    public boolean isPosValid (int pos) {
        
        return pos >= 0 && pos < this.inventory.length;
    }
    
    @Override
    public void readData (CompoundTag tag) {
        
        this.inventory = new ItemObject[tag.getInt("Size")];
        
        for (int index = 0; index < this.inventory.length; index++) {
            
            final CompoundTag slotTag = tag.getCompoundTag("Slot" + index);
            
            if (slotTag != null)
                this.inventory[index] = new ItemObject(slotTag);
        }
    }
    
    @Override
    public CompoundTag writeData (CompoundTag tag) {
        
        tag.setInt("Size", this.inventory.length);
        
        for (int index = 0; index < this.inventory.length; index++)
            if (this.isPosValid(index))
                tag.setCompoundTag("Slot" + index, this.inventory[index].writeData(new CompoundTag("Slot" + index)));
                
        return tag;
    }
    
    @Override
    public boolean canAddItem (int pos, ItemObject item) {
        
        return true;
    }
    
    @Override
    public String getNameForInventory () {
        
        return this.inventoryName;
    }
}