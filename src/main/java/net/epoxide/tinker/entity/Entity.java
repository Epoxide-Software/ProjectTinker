package net.epoxide.tinker.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import com.shc.silenceengine.utils.Logger;

import net.darkhax.opennbt.tags.CompoundTag;

import net.epoxide.tinker.util.Direction;
import net.epoxide.tinker.util.Displayable;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.util.Persistent;
import net.epoxide.tinker.util.RegistryName;
import net.epoxide.tinker.util.lang.I18n;
import net.epoxide.tinker.world.TileMap;

public class Entity implements Persistent, Displayable {
    
    /**
     * A registry for registering Entities into the game.
     */
    public static final NamedRegistry<Class<? extends Entity>> REGISTRY = new NamedRegistry<>();
    
    /**
     * A name that is associated with this entity. It is generally not unique.
     */
    private String displayName;
    
    /**
     * A CompoundTag which holds all of the data for this entity.
     */
    private CompoundTag entityData;
    
    /**
     * The ID that the entity is registered under.
     */
    private RegistryName ID;
    
    /**
     * The rotation of the entity
     */
    private Direction rotation;
    
    /**
     * A flag to determine whether or not the entity should be removed from the world.
     */
    private boolean shouldRemove;
    
    /**
     * The current TileMap that the entity is currently on.
     */
    private TileMap tileMap;
    
    /**
     * A unique identifier to represent the entity.
     */
    private UUID uniqueId;
    
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
        this.setRotation(Direction.UP);
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
     * Gets the current TileMap that the entity is on.
     * 
     * @return TileMap The current TileMap that the entity is on. Might be null.
     */
    public TileMap getCurrentMap () {
        
        return this.tileMap;
    }
    
    /**
     * Gets the name associated with the entity.
     *
     * @return String The name associated with the entity.
     */
    public String getDisplayName () {
        
        return this.displayName == null || this.displayName.isEmpty() ? this.getTranslatedName() : this.displayName;
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
     * Gets the current Direction that the entity is facing
     * 
     * @return Direction The current direction that the entity is facing
     */
    public Direction getRotation () {
        
        return this.rotation;
    }
    
    @Override
    public String getTranslatedName () {
        
        return I18n.translate("entity." + this.ID.getDomain() + "." + this.ID.getName() + ".name");
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
     * Gets the X position of the entity.
     * 
     * @return int The X position.
     */
    public float getXPos () {
        
        return this.xPos;
    }
    
    /**
     * Gets the Y position of the entity.
     * 
     * @return int The Y position.
     */
    public float getYPos () {
        
        return this.yPos;
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
     * Called whenever the entity is added to a TileMap. This is not limited to the initial
     * spawn of the mob, and happens on loading.
     */
    public void onJoinWorld (TileMap map) {
    
    }
    
    /**
     * Called when the entity is removed from the world.
     */
    public void onRemoval () {
        
        // TODO populate arguments
    }
    
    /**
     * Called when the entity initially spawns on a TileMap.
     * 
     * @param map The TileMap that the entity spawned on.
     */
    public void onSpawn (TileMap map) {
    
    }
    
    /**
     * Called on every update tick.
     */
    public void onUpdate () {
        
        // TODO populate arguments
    }
    
    @Override
    public void readData (CompoundTag tag) {
        
        this.displayName = tag.getString("EntityName");
        this.uniqueId = UUID.fromString(tag.getString("EntityUUID"));
        this.setPos(tag.getFloat("XPos"), tag.getFloat("YPos"));
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
     * Sets the name associated with the entity. The name can not be null or empty.
     *
     * @param name The new name for the entity.
     */
    public void setDisplayName (String name) {
        
        if (name != null && !name.isEmpty())
            this.displayName = name;
    }
    
    /**
     * Overrides the data of the entity with a new tag compound. Be careful when using this as
     * it completely deletes the CompoundTag and if set to null will break stuff.
     * 
     * @param tag The new CompoundTag. Please don't use null!
     */
    public void setEntityData (CompoundTag tag) {
        
        this.entityData = tag;
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
    
    // TODO
    public void setRotation (Direction rotation) {
        
        this.rotation = rotation;
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
     * Sets the Y position.
     * 
     * @param yPos The new Y position.
     */
    public void setYPos (float yPos) {
        
        this.yPos = yPos;
    }
    
    /**
     * Checks if the entity should be removed from the world.
     *
     * @return boolean Whether or not the entity should be removed.
     */
    public boolean shouldRemove () {
        
        return this.shouldRemove;
    }
    
    @Override
    public CompoundTag writeData (CompoundTag tag) {
        
        tag.setString("EntityID", REGISTRY.getNameForValue(this.getClass()).toString());
        tag.setString("EntityName", this.displayName);
        tag.setString("EntityUUID", this.uniqueId.toString());
        tag.setFloat("XPos", this.xPos);
        tag.setFloat("YPos", this.yPos);
        return tag;
    }
    
    /**
     * Creates an instance of an entity from the class it was registered with. All sub types of
     * Entity must have the standard blank constructor or else this will fail.
     * 
     * @param entityClass The Class of the entity you are trying to instantiated.
     * @return Entity A new entity instance, or null of an issue occurred.
     */
    public static Entity createInstance (Class<? extends Entity> entityClass) {
        
        try {
            
            final Constructor<? extends Entity> constructor = entityClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        }
        
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException exception) {
            
            Logger.warn("The Entity type " + entityClass.getName() + " Could not be instantiated!");
            exception.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Registers an Entity with the {@link #REGISTRY} using the ID stored in the Entity. This
     * should be used over directly accessing the REGISTRY.
     *
     * @param entity The Entity to register.
     * @return Class The class for the entity being registered.
     */
    public static Class<? extends Entity> registerEntity (RegistryName id, Entity entity) {
        
        entity.ID = id;
        return REGISTRY.registerValue(id, entity.getClass());
    }
}