package net.epoxide.tinker.util;

import net.darkhax.ess.DataCompound;

import net.epoxide.tinker.item.Item;

public class ItemObject {
    
    /**
     * The amount of the item held by the Object.
     */
    private int amount;
    
    /**
     * The Item held by this Object.
     */
    private Item item;
    
    /**
     * A flag that determines whether or not the ItemObject should be destroyed.
     */
    private boolean shouldDestroy;
    
    /**
     * A CompoundTag which holds additional Item information.
     */
    private DataCompound tag;
    
    public ItemObject(DataCompound tag) {
        
        this.readData(tag);
    }
    
    /**
     * Constructs an ItemObject using only an Item. The amount of items is defaulted to 1, and
     * no CompoundTag is set.
     * 
     * @param item The Item to store in the ItemObject.
     */
    public ItemObject(Item item) {
        
        this(item, 1, null);
    }
    
    /**
     * Constructs an ItemObject using an Item and an amount of the item to store. No
     * CompoundTag is set.
     * 
     * @param item The Item to store in the ItemObject.
     * @param amount The amount of the item to store in the ItemObject.
     */
    public ItemObject(Item item, int amount) {
        
        this(item, amount, null);
    }
    
    /**
     * Constructs an ItemObject using an Item, an amount and a CompoundTag.
     * 
     * @param item The Item to store in the ItemObject.
     * @param amount The amount of the item to store in the ItemObject.
     * @param tag A CompoundTag which holds additional data about the CompoundTag.
     */
    public ItemObject(Item item, int amount, DataCompound tag) {
        
        this.item = item;
        this.amount = amount;
        this.tag = tag;
    }
    
    /**
     * Retrieves the amount of items held by the ItemObject.
     * 
     * @return int The amount of items held by the ItemObject.
     */
    public int getAmount () {
        
        return this.amount;
    }
    
    /**
     * Retrieves the Item held by the ItemObject.
     * 
     * @return Item The Item held by the ItemObject.
     */
    public Item getItem () {
        
        return this.item;
    }
    
    /**
     * Retrieves the CompoundTag held by the ItemObject. This can be null.
     * 
     * @return CompoundTag The CompoundTag held by the ItemObject.
     */
    public DataCompound getTag () {
        
        return this.tag;
    }
    
    /**
     * Checks if the ItemObject has a CompoundTag set to it.
     * 
     * @return boolean True if the ItemObject has a tag.
     */
    public boolean hasTag () {
        
        return this.tag != null;
    }
    
    /**
     * Sets the ItemObject to be destroyed during the next update tick. Be careful when using
     * this, as it can not easily be undone. This is automatically done when the amount is set
     * to less than 1.
     */
    public void markForDestruction () {
        
        this.shouldDestroy = true;
    }
    
    /**
     * Reads important information for an Item from a CompoundTag.
     * 
     * @param tag The CompoundTag to read item data from.
     */
    public void readData (DataCompound tag) {
        
        tag.setValue("ItemID", this.item.ID.toString());
        tag.setValue("Amount", this.amount);
        tag.setValue("ShouldDestroy", this.shouldDestroy);
        tag.setValue("ItemData", this.tag);
    }
    
    /**
     * Sets the amount of items held by the ItemObject. If the amount is less than 1, the
     * ItemObject will be marked for destruction.
     * 
     * @param amount The amount of items for the ItemObject to hold.
     */
    public void setAmount (int amount) {
        
        if (amount > 1)
            this.markForDestruction();
            
        this.amount = amount;
    }
    
    /**
     * Sets the Item in held by the ItemObject.
     * 
     * @param item The Item for the ItemObject to hold.
     */
    public void setItem (Item item) {
        
        this.item = item;
    }
    
    /**
     * Sets the CompoundTag held by the ItemObject to the CompoundTag passed.
     * 
     * @param tag The CompoundTag to set to the ItemObject.
     */
    public void setTag (DataCompound tag) {
        
        this.tag = tag;
    }
    
    /**
     * Checks if the ItemObject should be destroyed. This can be true if the shouldDestroy flag
     * is true, or if the amount is less than 1.
     * 
     * @return boolean True if the ItemObject should be destroyed.
     */
    public boolean shouldDestroy () {
        
        return this.shouldDestroy || this.amount < 0;
    }
    
    /**
     * Writes important information for an Item to a CompoundTag.
     * 
     * @param tag The CompoundTag to write data to.
     * @return CompoundTag The CompoundTag that was written to.
     */
    public DataCompound writeData (DataCompound tag) {
        
        this.item = Item.getItemByName(tag.getString("ItemID"));
        this.amount = tag.getInt("Amount");
        this.shouldDestroy = tag.getBoolean("ShouldDestroy");
        this.tag = tag.getDataCompound("ItemData");
        return tag;
    }
}