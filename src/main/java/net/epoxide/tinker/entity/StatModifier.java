package net.epoxide.tinker.entity;

import java.util.UUID;

public class StatModifier {
    
    /**
     * The name of the stat being modified.
     */
    private final String type;
    
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
     * @param value The value of the stat modifier.
     * @param modType The type of modifications being performed.
     * @param type The name of the stat being modified.
     */
    public StatModifier(float value, byte modType, String type) {
        
        this.value = value;
        this.modType = modType;
        this.type = type;
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
     * Gets the name of the stat being modified.
     * 
     * @return String The name of the stat being modified.
     */
    public String getType () {
        
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