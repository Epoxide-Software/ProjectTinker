package net.epoxide.tinker.statuseffect;

import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.entity.EntityStat;
import net.epoxide.tinker.entity.StatModifier;
import net.epoxide.tinker.entity.living.EntityLiving;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.util.RegistryName;
import net.epoxide.tinker.util.StatProvider;

public class StatusEffect implements StatProvider {
    
    /**
     * A named registry which holds all registered effects.
     */
    public static final NamedRegistry<StatusEffect> REGISTRY = new NamedRegistry<>();
    
    /**
     * Whether or not the effect is considered a buff or a debuff.
     */
    private boolean isBuff;
    
    /**
     * The ID that the effect is registered under.
     */
    public final RegistryName ID;
    
    /**
     * Constructs a StatusEffect.
     *
     * @param id The ID that the StatusEffect is registered under.
     */
    public StatusEffect(String id) {
        
        this.ID = new RegistryName(id);
    }
    
    /**
     * Constructs a StatusEffect.
     * 
     * @param id The ID that the StatusEffect is registered under.
     * @param isBuff Whether or not the effect is a buff.
     */
    public StatusEffect(String id, boolean isBuff) {
        
        this(id);
        this.isBuff = isBuff;
    }
    
    @Override
    public StatModifier getStatModifier (Entity entity, EntityStat type) {
        
        return null;
    }
    
    /**
     * Checks if the effect is a buff or not. If it is not a buff, it is considered a debuff.
     * 
     * @return boolean Whether or not the effect is a buff.
     */
    public boolean isBuff () {
        
        return this.isBuff;
    }
    
    /**
     * Called every time an entity with the effect updates.
     * 
     * @param entity The Entity being updated.
     */
    public void onUpdate (EntityLiving entity) {
    
    }
}