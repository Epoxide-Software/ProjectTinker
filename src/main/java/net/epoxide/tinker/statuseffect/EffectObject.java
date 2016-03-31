package net.epoxide.tinker.statuseffect;

import net.darkhax.opennbt.tags.CompoundTag;

import net.epoxide.tinker.util.Persistent;

public class EffectObject implements Persistent {
    
    /**
     * The effect being applied.
     */
    private StatusEffect effect;
    
    /**
     * The tier of the effect.
     */
    private int tier;
    
    /**
     * The amount of time remaining on the effect.
     */
    private int time;
    
    /**
     * Constructs a new EffectObject, which handles an actual effect instance and not the
     * effect itself.
     * 
     * @param effect The effect being applied.
     * @param time The amount of time remaining on the effect.
     * @param tier The tier of the effect.
     */
    public EffectObject(StatusEffect effect, int time, int tier) {
        
        this.effect = effect;
        this.tier = tier;
        this.time = time;
    }
    
    /**
     * Gets the effect being applied.
     * 
     * @return StatusEffect The effect being applied.
     */
    public StatusEffect getEffect () {
        
        return this.effect;
    }
    
    /**
     * Gets the tier or strength of the effect.
     * 
     * @return int The tier/strength of the effect.
     */
    public int getTier () {
        
        return this.tier;
    }
    
    /**
     * Gets the time remaining before the effect runs out.
     * 
     * @return int The time remaining on the effect.
     */
    public int getTime () {
        
        return this.time;
    }
    
    @Override
    public void readData (CompoundTag tag) {
        
        this.time = tag.getInt("Time");
        this.tier = tag.getInt("Tier");
        this.effect = StatusEffect.REGISTRY.getValue(tag.getString("EffectID"));
    }
    
    @Override
    public CompoundTag writeData (CompoundTag tag) {
        
        tag.setInt("Time", this.time);
        tag.setInt("Tier", this.tier);
        tag.setString("EffectID", this.effect.ID.toString());
        return tag;
    }
}
