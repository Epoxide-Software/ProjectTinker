package net.epoxide.tinker.entity.living;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.darkhax.ess.DataCompound;

import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.entity.EntityStat;
import net.epoxide.tinker.entity.StatModifier;
import net.epoxide.tinker.statuseffect.EffectObject;
import net.epoxide.tinker.statuseffect.StatusEffect;
import net.epoxide.tinker.world.TileMap;

public class EntityLiving extends Entity {
    
    /**
     * A list of status effects that the entity currently has.
     */
    private List<EffectObject> effects = new ArrayList<EffectObject>();
    
    /**
     * The amount of health that the entity is currently at.
     */
    private int health;
    
    /**
     * A flag used to determine whether or not the entity can lose health.
     */
    private boolean isInvulnerable;
    
    /**
     * The most health that the entity should ever have. It will be possible for entities to
     * have more health than the maximum in unnatural circumstances.
     */
    private int maxHealth;
    
    /**
     * Constructs the entity with no internal logic. Allows for all of the logic to be handled
     * by the entity.
     */
    public EntityLiving() {
    
    }
    
    /**
     * Constructs a new entity that is on a TileMap.
     * 
     * @param map The TileMap to spawn the entity on.
     */
    public EntityLiving(TileMap map) {
        
        super(map);
    }
    
    /**
     * Constructs a new entity from a CompoundTag. Intended for loading entities onto a TileMap
     * from the TileMap data.
     * 
     * @param map The TileMap to spawn the entity on.
     * @param tag The CompoundTag to load data from.
     */
    public EntityLiving(TileMap map, DataCompound tag) {
        
        super(map, tag);
    }
    
    /**
     * Applies an effect to the entity. Only effects that are not null and have a time greater
     * than 0 can be added.
     * 
     * @param effect The effect to apply.
     */
    public void applyEffect (EffectObject effect) {
        
        if (effect != null && effect.getTime() > 0)
            this.effects.add(effect);
    }
    
    /**
     * Cures all effects that are of the specified type.
     * 
     * @param effect The type of effect to clear.
     */
    public void cureEffect (StatusEffect effect) {
        
        this.effects = this.effects.stream().filter(currentEffect -> currentEffect.getEffect().equals(effect)).collect(Collectors.toList());
    }
    
    /**
     * Gets the base stat value for a stat type. This is the stat before any modifiers have
     * been applied to it.
     * 
     * @param type The type of stat to get the base stat for.
     * @return float A base stat for the stat type passed.
     */
    public float getBaseStat (EntityStat type) {
        
        if (type == EntityStat.MAX_HEALTH)
            return 5f;
            
        return 0;
    }
    
    /**
     * Gets the current health of the entity.
     * 
     * @return int The current health of the entity.
     */
    public int getCurrentHealth () {
        
        return this.health;
    }
    
    /**
     * Calculates the percentage of remaining health that the mob has.
     * 
     * @return double The percentage as a double.
     */
    // TODO Round this down to show up to 1 decimal place, if that decimal is
    // not 0.
    public double getHealthPercentage () {
        
        return (double) this.health / (double) this.maxHealth;
    }
    
    /**
     * Gets the maximum amount of health that the entity should have under normal
     * circumstances.
     * 
     * @return int The maximum amount of health the entity should have.
     */
    public int getMaxHealth () {
        
        return this.maxHealth;
    }
    
    /**
     * Gets the list of modifiers that are effecting the entity.
     * 
     * @param type The type of modifier you want to solve for.
     * @return List<StatModifier> A list of modifiers currently effecting the entity.
     */
    public List<StatModifier> getModifiers (EntityStat type) {
        
        final List<StatModifier> stats = new ArrayList<StatModifier>();
        stats.add(this.getCurrentMap().getStatModifier(this, type));
        
        if (!this.effects.isEmpty())
            this.effects.forEach(effect -> stats.add(effect.getEffect().getStatModifier(this, type)));
            
        return stats;
    }
    
    /**
     * Calculates a stat after it has been effected by all it's other modifiers.
     * 
     * @param type The type of stat to calculate.
     * @return float A flort that represents the stat.
     */
    public float getTotalStatValue (EntityStat type) {
        
        float base = this.getBaseStat(type);
        float addition = 0;
        float percentage = 1f;
        
        final List<StatModifier> stats = this.getModifiers(type);
        
        for (final StatModifier modifier : stats)
            if (modifier.getModifierType() == 0)
                addition += modifier.getValue();
                
            else if (modifier.getModifierType() == 1)
                percentage += modifier.getValue();
                
        base *= percentage;
        base += addition;
        
        return base;
    }
    
    /**
     * Harms the entity by the specified amount. Will never harm the entity below 0. If the
     * entity is invulnerable they will receive no damage.
     * 
     * @param amount The amount to damage the entity by.
     */
    public void harm (int amount) {
        
        if (!this.isInvulnerable)
            this.health = Math.max(this.health - amount, 0);
    }
    
    /**
     * Heals the entity by the specified amount. Will never heal more than the max health.
     * 
     * @param amount The amount to heal the entity by.
     */
    public void heal (int amount) {
        
        this.health = Math.min(this.health + amount, this.maxHealth);
    }
    
    /**
     * Checks if an entity should be immune to damage.
     * 
     * @return boolean Whether or not the entity can not take damage.
     */
    public boolean isInvulnerable () {
        
        return this.isInvulnerable;
    }
    
    @Override
    public void onUpdate () {
        
        if (this.health <= 0)
            this.markForRemoval(true);
            
        this.effects = this.effects.stream().filter(effect -> effect.updateState(this)).collect(Collectors.toList());
    }
    
    /**
     * Updates the vulnerability of the entity.
     * 
     * @param vulnerability If true, the entity will be invulnerable.
     */
    public void setVulnerability (boolean vulnerability) {
        
        this.isInvulnerable = vulnerability;
    }
}