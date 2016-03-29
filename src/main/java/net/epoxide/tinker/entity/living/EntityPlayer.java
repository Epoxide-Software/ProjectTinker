package net.epoxide.tinker.entity.living;

import net.darkhax.opennbt.tags.CompoundTag;

import net.epoxide.tinker.world.TileMap;

public class EntityPlayer extends EntityLiving {
    
    /**
     * Constructs the entity with no internal logic. Allows for all of the logic to be handled
     * by the entity.
     */
    public EntityPlayer() {
    
    }
    
    /**
     * Constructs a new entity that is on a TileMap.
     * 
     * @param map The TileMap to spawn the entity on.
     */
    public EntityPlayer(TileMap map) {
        
        super(map);
    }
    
    /**
     * Constructs a new entity from a CompoundTag. Intended for loading entities onto a TileMap
     * from the TileMap data.
     * 
     * @param map The TileMap to spawn the entity on.
     * @param tag The CompoundTag to load data from.
     */
    public EntityPlayer(TileMap map, CompoundTag tag) {
        
        super(map, tag);
    }
}