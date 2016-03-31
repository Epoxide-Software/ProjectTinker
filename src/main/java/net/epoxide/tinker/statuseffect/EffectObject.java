package net.epoxide.tinker.statuseffect;

public class EffectObject {
    
    /**
     * The effect being applied.
     */
    private final StatusEffect effect;
    
    /**
     * The tier of the effect.
     */
    private final int tier;
    
    /**
     * The amount of time remaining on the effect.
     */
    private final int time;
    
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
}
