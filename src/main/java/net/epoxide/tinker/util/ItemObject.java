package net.epoxide.tinker.util;

import net.darkhax.opennbt.tags.CompoundTag;

import net.epoxide.tinker.item.Item;

public class ItemObject {
    
    /**
     * The Item held by this Object.
     */
    private Item item;
    
    /**
     * The amount of the item held by the Object.
     */
    private int amount;
    
    /**
     * A CompoundTag which holds additional Item information.
     */
    private CompoundTag tag;
    
    /**
     * A flag that determines whether or not the ItemObject should be destroyed.
     */
    private boolean shouldDestroy;
    
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
    public ItemObject(Item item, int amount, CompoundTag tag) {
        
        this.item = item;
        this.amount = amount;
        this.tag = tag;
    }
    
    /**
     * Retrieves the Item held by the ItemObject.
     * 
     * @return Item The Item held by the ItemObject.
     */
    public Item getItem () {
        
        return item;
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
     * Retrieves the amount of items held by the ItemObject.
     * 
     * @return int The amount of items held by the ItemObject.
     */
    public int getAmount () {
        
        return amount;
    }
    
    /**
     * Sets the amount of items held by the ItemObject. If the amount is less than 1, the
     * ItemObject will be marked for destruction.
     * 
     * @param amount The amount of items for the ItemObject to hold.
     */
    public void setAmount (int amount) {
        
        if (amount > 1)
            markForDestruction();
            
        this.amount = amount;
    }
    
    /**
     * Checks if the ItemObject has a CompoundTag set to it.
     * 
     * @return boolean True if the ItemObject has a tag.
     */
    public boolean hasTag () {
        
        return (tag != null);
    }
    
    /**
     * Retrieves the CompoundTag held by the ItemObject. This can be null.
     * 
     * @return CompoundTag The CompoundTag held by the ItemObject.
     */
    public CompoundTag getTag () {
        
        return tag;
    }
    
    /**
     * Sets the CompoundTag held by the ItemObject to the CompoundTag passed.
     * 
     * @param tag The CompoundTag to set to the ItemObject.
     */
    public void setTag (CompoundTag tag) {
        
        this.tag = tag;
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
     * Checks if the ItemObject should be destroyed. This can be true if the shouldDestroy flag
     * is true, or if the amount is less than 1.
     * 
     * @return boolean True if the ItemObject should be destroyed.
     */
    public boolean shouldDestroy () {
        
        return (shouldDestroy || amount < 0);
    }
}