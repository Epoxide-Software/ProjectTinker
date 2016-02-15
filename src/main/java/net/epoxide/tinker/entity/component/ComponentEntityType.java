package net.epoxide.tinker.entity.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComponentEntityType extends EntityComponent {
    
    // TODO these types should get JavaDocs explaining how vanilla code interacts with them.
    public static final String HUMAN = "human";
    public static final String BUG = "bug";
    public static final String FUNGUS = "fungus";
    public static final String PLANT = "plant";
    public static final String DOG = "dog";
    public static final String CAT = "cat";
    public static final String BIRD = "bird";
    public static final String UNDEAD = "undead";
    
    /**
     * A list of all types held by this entity.
     */
    private List<String> types;
    
    /**
     * Provides an entity with an entity type. These are used to categorize entities into
     * special groups. An entity can be part of more than one group. While types do not have
     * any special properties on their own, things may interact differently with entities of a
     * certain type. For example, some types might not be allowed to walk on certain blocks.
     * When declared, types should always be static and final. Good people also make their
     * types public.
     * 
     * @param types: The types to set on the entity.
     */
    public ComponentEntityType(String... types) {
        
        this.types = new ArrayList<String>(Arrays.asList(types));
    }
    
    /**
     * Gets an array of all types that the entity is a part of.
     * 
     * @return String[] An array of all types set on this entity.
     */
    public String[] getTypes () {
        
        return this.types.toArray(new String[this.types.size()]);
    }
    
    /**
     * Adds a new type to the entity. Types can not be set twice.
     * 
     * @param type: The type to add to the entity.
     */
    public void addType (String type) {
        
        if (!this.types.contains(type))
            this.types.add(type);
    }
    
    /**
     * Attempts to remove a type from the entity.
     * 
     * @param type: The type to remove.
     */
    public void removeType (String type) {
        
        this.types.remove(type);
    }
    
    /**
     * Checks if the entity has a specific type.
     * 
     * @param type: The type being searched for.
     * @return boolean: Whether or not the entity has the specified type.
     */
    public boolean hasType (String type) {
        
        return types.contains(type);
    }
}