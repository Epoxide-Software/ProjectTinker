package net.epoxide.tinker.entity;

import java.util.UUID;

import net.darkhax.opennbt.tags.CompoundTag;
import net.epoxide.tinker.world.TileMap;

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
     * A CompoundTag which holds all of the data for this entity.
     */
    private CompoundTag entityData;
    
    /**
     * A flag to determine whether or not the entity should be removed from the world.
     */
    private boolean shouldRemove;
    
    /**
     * Constructs a new entity that is on a TileMap.
     * 
     * @param map The TileMap to spawn the entity on.
     */
    public Entity(TileMap map) {
        
        this.uniqueId = UUID.randomUUID();
        this.entityData = new CompoundTag("data");
    }
    
    /**
     * Constructs a new entity from a CompoundTag. Intended for loading entities onto a TileMap
     * from the TileMap data.
     * 
     * @param map The TileMap to spawn the entity on.
     * @param tag The CompoundTag to load data from.
     */
    public Entity(TileMap map, CompoundTag tag) {
        
        this.readData(tag);
        this.entityData = tag;
    }
    
    /**
     * Gets the name associated with the entity.
     *
     * @return String The name associated with the entity.
     */
    public String getDisplayName () {
        
        return this.displayName;
    }
    
    /**
     * Sets the name associated with the entity. The name can not be null or empty.
     *
     * @param name The new name for the entity.
     */
    public void setDisplayName (String name) {
        
        if (name != null && !name.isEmpty())
            this.displayName = name;
    }
    
    /**
     * Gets the unique identifier for the entity. No two entities should ever have the same
     * identifier.
     *
     * @return UUID The unique identifier for this entity.
     */
    public UUID getUniqueId () {
        
        return this.uniqueId;
    }
    
    /**
     * Gets the CompoundTag holding all the persistent data for this entity.
     * 
     * @return CompoundTag A CompoundTag which holds persistent data.
     */
    public CompoundTag getEntityData () {
        
        return this.entityData;
    }
    
    /**
     * Checks if the entity should be removed from the world.
     *
     * @return boolean Whether or not the entity should be removed.
     */
    public boolean shouldRemove () {
        
        return this.shouldRemove;
    }
    
    /**
     * Updates the status of the shouldRemove flag. If set to true, the mob will be removed
     * from the world in the next update tick, and will be garbage collected.
     *
     * @param isRemovable Whether or not the entity should be removed.
     */
    public void markForRemoval (boolean isRemovable) {
        
        this.shouldRemove = isRemovable;
    }
    
    /**
     * Called whenever the entity enters a world. This is typically limited to when the mob
     * initially spawns.
     * 
     * @param map The TileMap that the entity spawned on.
     */
    
    /**
     * Called whenever the entity is added to a TileMap. This is not limited to the initial
     * spawn of the mob, and happens on loading.
     */
    public void onJoinWorld (TileMap map) {
    
    }
    
    /**
     * Called when the entity initially spawns on a TileMap.
     * 
     * @param map The TileMap that the entity spawned on.
     */
    public void onSpawn (TileMap map) {
    
    }
    
    /**
     * Called when the entity is removed from the world.
     */
    public void onRemoval () {
        
        // TODO populate arguments
    }
    
    /**
     * Called on every update tick.
     */
    public void onUpdate () {
        
        // TODO populate arguments
    }
    
    /**
     * Called when the entity is being loaded from disk.
     */
    public void readData (CompoundTag tag) {
        
        this.displayName = tag.getString("EntityName");
        this.uniqueId = UUID.fromString(tag.getString("EntityUUID"));
    }
    
    /**
     * Called when the entity is being written to data. Things saved here can be loaded during
     * readData.
     */
    public void writeData (CompoundTag tag) {
        
        tag.setString("EntityName", this.displayName);
        tag.setString("EntityUUID", this.uniqueId.toString());
    }
}