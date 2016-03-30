package net.epoxide.tinker.entity;

import java.util.UUID;

import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.util.RegistryName;

public class EntityStat {
    
    /**
     * A stat which represents the entities charm. Used as a friendship speed modifier.
     */
    public static final EntityStat CHARM = createStat("charm");
    
    /**
     * A stat which represents the entities physical damage.
     */
    public static final EntityStat DAMAGE = createStat("damage");
    
    /**
     * A stat which represents the entities physical defence.
     */
    public static final EntityStat DEFENCE = createStat("defence");
    
    /**
     * A stat which represents the entities intelligence.
     */
    public static final EntityStat INT = createStat("intelligence");
    
    /**
     * A stat which represents the entities luck. Used as a random outcome modifier.
     */
    public static final EntityStat LUCK = createStat("luck");
    
    /**
     * A stat which represents the entities magical damage.
     */
    public static final EntityStat MAGIC_DAMAGE = createStat("magicdamage");
    
    /**
     * A stat which represents the entities magical defence.
     */
    public static final EntityStat MAGIC_RESIST = createStat("magicresist");
    
    /**
     * A stat which represents the entities maximum health.
     */
    public static final EntityStat MAX_HEALTH = createStat("maxhealth");
    
    /**
     * A registry of all the different types of EntityStat.
     */
    public static final NamedRegistry<EntityStat> REGISTRY = new NamedRegistry<>();
    
    /**
     * A stat which represents the entities movement speed.
     */
    public static final EntityStat SPEED = createStat("speed");
    
    /**
     * A stat which represents the entities physical strength.
     */
    public static final EntityStat STRENGTH = createStat("strength");
    
    /**
     * The registry Id for the EntityStat.
     */
    private final String ID;
    
    /**
     * The name of the EntityStat. Used in localizations.
     */
    private final String NAME;
    
    /**
     * A unique UUID specific to the instance of the EntityStat. Not reliable!
     */
    private final UUID UNIQUE_ID;
    
    /**
     * Constructs a new EntityStat using the basic registry info.
     * 
     * @param domain The domain for the EntityStat.
     * @param name The name for the EntityStat.
     */
    protected EntityStat(String domain, String name) {
        
        this.ID = domain + ":" + name;
        this.NAME = name;
        this.UNIQUE_ID = UUID.randomUUID();
    }
    
    @Override
    public boolean equals (Object obj) {
        
        if (this == obj)
            return true;
            
        else if (obj instanceof EntityStat)
            return this.ID.equals(((EntityStat) obj).ID);
            
        else
            return false;
    }
    
    /**
     * Gets the Id that the EntityStat was registered with in {@link #REGISTRY}.
     * 
     * @return String The Id of the EntityStat.
     */
    public String getId () {
        
        return this.ID;
    }
    
    /**
     * The name that the EntityStat was registered with. Usually used for localization.
     * 
     * @return String The name of the EntityStat.
     */
    public String getName () {
        
        return this.NAME;
    }
    
    /**
     * Gets the instance specific UUID. This Id is not reliable for serialization as it is
     * random every startup.
     * 
     * @return UUID The UUID for the EntityStat.
     */
    public UUID getUUID () {
        
        return this.UNIQUE_ID;
    }
    
    @Override
    public int hashCode () {
        
        return this.ID.hashCode();
    }
    
    @Override
    public String toString () {
        
        return "Stat(" + "id=" + this.ID + " uuid=" + this.UNIQUE_ID.toString() + ")";
    }
    
    /**
     * Creates a new EntityStat from the passed name and registers it with {@link #REGISTRY}.
     * If the name passed does not contain a domain substring the default domain will be
     * applied.
     * 
     * @param name The name for the new EntityStat.
     * @return EntityStat the new EntityStat.
     */
    public static EntityStat createStat (String name) {
        
        final RegistryName regName = new RegistryName(name);
        return REGISTRY.registerValue(regName, new EntityStat(regName.getDomain(), regName.getName()));
    }
    
    /**
     * Creates a new EntityStat from the passed name data and registers it with
     * {@link #REGISTRY}.
     * 
     * @param domain The domain that the EntityStat is added under.
     * @param name The name for the new EntityStat.
     * @return EntityStat The new EntityStat.
     */
    public static EntityStat createStat (String domain, String name) {
        
        return REGISTRY.registerValue(domain, name, new EntityStat(domain, name));
    }
}
