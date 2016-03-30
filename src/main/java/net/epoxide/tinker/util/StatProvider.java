package net.epoxide.tinker.util;

import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.entity.EntityStat;
import net.epoxide.tinker.entity.StatModifier;

public interface StatProvider {
    
    /**
     * Gets a StatModifier for the provider. This allows for the provider to manipulate an
     * entities stats.
     * 
     * @param entity The Enttity to modify stats for.
     * @param type The type of stat to provide a modifier for.
     * @return StatModifier A StatModifier which represents the effects of the provider on the
     *         entities stats.
     */
    public StatModifier getStatModifier (Entity entity, EntityStat type);
}