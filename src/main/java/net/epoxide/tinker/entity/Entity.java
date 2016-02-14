package net.epoxide.tinker.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.epoxide.tinker.entity.component.EntityComponent;

public class Entity {
    
    /**
     * A name that is associated with this entity. It is generally not unique.
     */
    private String displayName;
    
    /**
     * A unique identifier to represent the entity.
     */
    private UUID uniqueId;
    
    /**
     * A List containing all components for this entity.
     */
    private List<EntityComponent> components;
    
    /**
     * A flag to determine whether or not the entity should be removed from the world.
     */
    private boolean shouldRemove;
    
    // TODO Constructor Params
    public Entity(String name) {
        
        this.displayName = name;
        this.uniqueId = UUID.randomUUID();
        this.components = new ArrayList<EntityComponent>();
    }
    
    /**
     * Called whenever the entity enters a world. This is typically limited to when the mob
     * initially spawns.
     */
    public void onJoinWorld () {
        
        for (EntityComponent component : this.components)
            component.onJoinWorld(this);
    }
    
    /**
     * Called when the entity is killed or removed from the world.
     */
    public void onKilled () {
        
        for (EntityComponent component : this.components)
            component.onKilled(this);
    }
    
    /**
     * Called on every update tick.
     */
    public void onUpdate () {
        
        for (EntityComponent component : this.components)
            component.onUpdate(this);
    }
    
    /**
     * Called when the entity is being loaded from disk.
     */
    public void readData () {
        
        for (EntityComponent component : this.components)
            component.readData(this);
    }
    
    /**
     * Called when the entity is being written to data. Things saved here can be loaded during
     * readData.
     */
    public void writeData () {
        
        for (EntityComponent component : this.components)
            component.writeData(this);
    }
    
    /**
     * Gets the name associated with the entity.
     * 
     * @return String: The name associated with the entity.
     */
    public String getEntityName () {
        
        return this.displayName;
    }
    
    /**
     * Sets the name associated with the entity. The name can not be null or empty.
     * 
     * @param name: The new name for the entity.
     */
    public void setEntityName (String name) {
        
        if (name != null && !name.isEmpty())
            this.displayName = name;
    }
    
    /**
     * Gets the unique identifier for the entity. No two entities should ever have the same
     * identifier.
     * 
     * @return UUID: The unique identifier for this entity.
     */
    public UUID getUniqueId () {
        
        return this.uniqueId;
    }
    
    /**
     * Gets the List of components attached to the entity.
     * 
     * @return List<EntityComponent>: A List of all attached components.
     */
    public List<EntityComponent> getComponents () {
        
        return this.components;
    }
    
    /**
     * Adds a component to the list of components used by the entity.
     * 
     * @param component: The component to add.
     * @return Entity: An instance of the entity for convenience.
     */
    public Entity addComponent (EntityComponent component) {
        
        this.components.add(component);
        return this;
    }
    
    /**
     * Removes a component from the list of components. For the component to be removed, an
     * existing component must pass the equals check.
     * 
     * @param toRemove: The component to remove from the entity.
     * @return Entity: An instance of the entity for convenience.
     */
    public Entity removeComponent (EntityComponent toRemove) {
        
        for (EntityComponent component : this.components) {
            
            if (component.equals(toRemove)) {
                
                this.components.remove(toRemove);
                break;
            }
        }
        
        return this;
    }
    
    /**
     * Removes a component from the entity by using its index within the component list.
     * 
     * @param index: The index of the component to remove.
     * @return Entity: An instance of the entity for convenience.
     */
    public Entity removeComponent (int index) {
        
        this.components.remove(index);
        return this;
    }
    
    /**
     * Checks if the entity should be removed from the world.
     * 
     * @return boolean: Whether or not the entity should be removed.
     */
    public boolean shouldRemove () {
        
        return this.shouldRemove;
    }
    
    /**
     * Updates the status of the shouldRemove flag. If set to true, the mob will be removed
     * from the world in the next update tick, and will be garbage collected.
     * 
     * @param isRemovable: Whether or not the entity should be removed.
     */
    public void setRemoveStatus (boolean isRemovable) {
        
        this.shouldRemove = isRemovable;
    }
}
