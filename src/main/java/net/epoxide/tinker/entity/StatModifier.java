package net.epoxide.tinker.entity;

import java.util.UUID;

public class StatModifier {
    
    /**
     * The type of the stat being modified.
     */
    private final EntityStat type;
    
    /**
     * The value of the stat modifier.
     */
    private final float value;
    
    /**
     * The modifier type of the stat modifier.
     */
    private final byte modType;
    
    /**
     * A UUID specific to the instance of StatModifier.
     */
    private final UUID id;
    
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
    
    /**
     * Gets the value of the modifier.
     * 
     * @return float The value of the modifier.
     */
    public float getValue () {
        
        return value;
    }
    
    /**
     * Gets the byte identifier for the type of modification being performed.
     * 
     * @return byte The identifier for the modification performed by the modifier.
     */
    public byte getModifierTpe () {
        
        return modType;
    }
    
    /**
     * Gets the EntityStat being modified.
     * 
     * @return EntityStat The EntityStat being modified.
     */
    public EntityStat getType () {
        
        return type;
    }
    
    @Override
    public boolean equals (Object obj) {
        
        if (this == obj)
            return true;
            
        else if (obj instanceof StatModifier)
            return (this.id.equals(((StatModifier) obj).id));
            
        else
            return false;
    }
    
    @Override
    public int hashCode () {
        
        return this.id.hashCode();
    }
    
    @Override
    public String toString () {
        
        return "StatModifier(type=" + this.type + " value=" + this.value + " modType=" + this.modType + " id=" + this.id.toString() + ")";
    }
}