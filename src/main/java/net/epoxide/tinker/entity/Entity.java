package net.epoxide.tinker.entity;

import java.util.UUID;

import net.darkhax.opennbt.tags.CompoundTag;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.world.TileMap;

public class Entity {
    
    /**
     * A registry for registering Entities into the game.
     */
    public static final NamedRegistry<Entity> REGISTRY = new NamedRegistry<Entity>();
    
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
     * The current TileMap that the entity is currently on.
     */
    private TileMap tileMap;
    
    /**
     * The position of the entity on the X axis.
     */
    private float xPos;
    
    /**
     * The position of the entity on the Y axis.
     */
    private float yPos;
    
    /**
     * Constructs the entity with no internal logic. Allows for all of the logic to be handled
     * by the entity.
     */
    public Entity() {
    
    }
    
    /**
     * Constructs a new entity that is on a TileMap.
     * 
     * @param map The TileMap to spawn the entity on.
     */
    public Entity(TileMap map) {
        
        this.uniqueId = UUID.randomUUID();
        this.entityData = new CompoundTag("data");
        this.setPos(0, 0);
        this.setCurrentMap(map);
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
        this.setCurrentMap(map);
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
     * Gets the X position of the entity.
     * 
     * @return int The X position.
     */
    public float getXPos () {
        
        return xPos;
    }
    
    /**
     * Sets the X Position.
     * 
     * @param xPos The new X position.
     */
    public void setXPos (float xPos) {
        
        this.xPos = xPos;
    }
    
    /**
     * Gets the Y position of the entity.
     * 
     * @return int The Y position.
     */
    public float getYPos () {
        
        return yPos;
    }
    
    /**
     * Sets the Y position.
     * 
     * @param yPos The new Y position.
     */
    public void setYPos (float yPos) {
        
        this.yPos = yPos;
    }
    
    /**
     * Sets the X and Y position of the entity at the same time.
     * 
     * @param xPos The new X position.
     * @param yPos The new Y position.
     */
    public void setPos (float xPos, float yPos) {
        
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    /**
     * Gets the current TileMap that the entity is on.
     * 
     * @return TileMap The current TileMap that the entity is on. Might be null.
     */
    public TileMap getCurrentMap () {
        
        return this.tileMap;
    }
    
    /**
     * Sets the TileMap that the entity is currently on. This will also trigger
     * {@link #onJoinWorld(TileMap)}
     * 
     * @param map The TileMap for the entity to be put on.
     */
    public void setCurrentMap (TileMap map) {
        
        this.tileMap = map;
        this.onJoinWorld(map);
    }
    
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
        this.setPos(tag.getFloat("XPos"), tag.getFloat("YPos"));
    }
    
    /**
     * Called when the entity is being written to data. Things saved here can be loaded during
     * readData.
     */
    public void writeData (CompoundTag tag) {
        
        tag.setString("EntityName", this.displayName);
        tag.setString("EntityUUID", this.uniqueId.toString());
        tag.setFloat("XPos", this.xPos);
        tag.setFloat("YPos", this.yPos);
    }
}