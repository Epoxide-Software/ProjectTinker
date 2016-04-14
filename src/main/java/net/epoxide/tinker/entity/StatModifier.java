package net.epoxide.tinker.entity;

import java.util.UUID;

import net.darkhax.ess.DataCompound;

import net.epoxide.tinker.util.Persistent;

public class StatModifier implements Persistent {
    
    /**
     * A UUID specific to the instance of StatModifier.
     */
    private final UUID id;
    
    /**
     * The modifier type of the stat modifier.
     */
    private byte modType;
    
    /**
     * The type of the stat being modified.
     */
    private EntityStat type;
    
    /**
     * The value of the stat modifier.
     */
    private float value;
    
    /**
     * Constructs a new StatModifier from a CompoundTag. Expects a tag containing data from
     * {@link #writeStat(CompoundTag)}.
     * 
     * @param tag The CompoundTag to read the data from.
     */
    public StatModifier(DataCompound tag) {
        
        this.readData(tag);
        this.id = UUID.randomUUID();
    }
    
    /**
     * Constructs a new StatModifier which handles the modification of a certain stat type.
     * 
     * @param type The EntityStat being modified.
     * @param value The value of the stat modifier.
     * @param modType The type of modifications being performed.
     */
    public StatModifier(EntityStat type, float value, byte modType) {
        
        this.type = type;
        this.value = value;
        this.modType = modType;
        this.id = UUID.randomUUID();
    }
    
    @Override
    public boolean equals (Object obj) {
        
        if (this == obj)
            return true;
            
        else if (obj instanceof StatModifier)
            return this.id.equals(((StatModifier) obj).id);
            
        else
            return false;
    }
    
    /**
     * Gets the byte identifier for the type of modification being performed.
     * 
     * @return byte The identifier for the modification performed by the modifier.
     */
    public byte getModifierType () {
        
        return this.modType;
    }
    
    /**
     * Gets the EntityStat being modified.
     * 
     * @return EntityStat The EntityStat being modified.
     */
    public EntityStat getType () {
        
        return this.type;
    }
    
    /**
     * Gets the value of the modifier.
     * 
     * @return float The value of the modifier.
     */
    public float getValue () {
        
        return this.value;
    }
    
    @Override
    public int hashCode () {
        
        return this.id.hashCode();
    }
    
    @Override
    public void readData (DataCompound tag) {
        
        this.type = EntityStat.REGISTRY.getValue(tag.getString("StatType"));
        this.value = tag.getFloat("StatValue");
        this.modType = tag.getByte("StatModifier");
    }
    
    @Override
    public String toString () {
        
        return "StatModifier(type=" + this.type + " value=" + this.value + " modType=" + this.modType + " id=" + this.id.toString() + ")";
    }
    
    @Override
    public DataCompound writeData (DataCompound tag) {
        
        tag.setValue("StatType", this.type.getId());
        tag.setValue("StatValue", this.value);
        tag.setValue("StatModifier", this.modType);
        return tag;
    }
}