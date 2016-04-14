package net.epoxide.tinker.statuseffect;

import net.darkhax.ess.DataCompound;

import net.epoxide.tinker.entity.living.EntityLiving;
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
    
    @Override
    public boolean equals (Object obj) {
        
        if (obj == this)
            return true;
            
        if (obj instanceof EffectObject) {
            
            final EffectObject effect = (EffectObject) obj;
            return this.effect.equals(effect) && this.tier == effect.tier && this.time == effect.time;
        }
        
        return false;
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
    public void readData (DataCompound tag) {
        
        this.time = tag.getInt("Time");
        this.tier = tag.getInt("Tier");
        this.effect = StatusEffect.REGISTRY.getValue(tag.getString("EffectID"));
    }
    
    /**
     * Updates the status of the EffectObject. This will reduce {@link #time} by one and
     * trigger {@link StatusEffect#onUpdate(EntityLiving)}. Expired effects will return false,
     * allowing them to be purged using a filter.
     * 
     * @param entity The EntityLiving to apply the effect to.
     * @return boolean Whether or not the effect will stay.
     */
    public boolean updateState (EntityLiving entity) {
        
        if (this.time == 0)
            return false;
            
        this.time--;
        this.effect.onUpdate(entity);
        return true;
    }
    
    @Override
    public DataCompound writeData (DataCompound tag) {
        
        tag.setValue("Time", this.time);
        tag.setValue("Tier", this.tier);
        tag.setValue("EffectID", this.effect.ID.toString());
        return tag;
    }
}
