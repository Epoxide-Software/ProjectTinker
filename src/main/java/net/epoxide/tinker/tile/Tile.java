package net.epoxide.tinker.tile;

import java.awt.Color;

import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.util.ItemObject;
import net.epoxide.tinker.util.NamedRegistry;

public class Tile {
    
    public static final NamedRegistry<Tile> REGISTRY = new NamedRegistry<Tile>();
    
    public static Tile VOID = REGISTRY.registerValue("tinker:void", new Tile());

    /**
     * Called when the tile is being placed on a TileMap. Can be used to initialize data,
     * trigger events, or to prevent the tile from being placed.
     * 
     * @param posX The X coordinate of the tile on the tile map.
     * @param posY The Y coordinate of the tile on the tile map.
     * @return boolean Whether or not the tile should be placed.
     */
    // TODO add TileMap param
    public boolean placeTile (int posX, int posY) {
        
        return true;
    }
    
    /**
     * Called when the tile is being removed from a TileMap. Can be used to clear data, trigger
     * events, or to prevent the tile from being removed..
     * 
     * @param posX The X coordinate of the tile on the tile map.
     * @param posY The Y coordinate of the tile on the tile map.
     * @return boolean Whether or not the tile should be removed..
     */
    // TODO add TileMap param
    public boolean removeTile (int posX, int posY) {
        
        return true;
    }
    
    /**
     * Called when the tile is being harvested by an entity. Can be used to handle custom
     * harvest logic, or prevent harvesting.
     * 
     * @param harvester An instance of the Entity that is trying to harvest the Tile.
     * @param item The Item used to harvest the tile. Might be null.
     * @param posX The X coordinate of the tile on the tile map.
     * @param posY The Y coordinate of the tile on the tile map.
     * @return boolean Whether or not the tile should be harvested.
     */
    // TODO add TileMap param
    public boolean harvestTile (Entity harvester, ItemObject item, int posX, int posY) {
        
        return true;
    }
    
    /**
     * Called when the tile is being activated by an entity. Can be used to handle custom
     * activation logic.
     * 
     * @param activator An instance of the Entity that is trying to activate the Tile.
     * @param item The Item used to activate the tile. Might be null.
     * @param posX The X coordinate of the tile on the tile map.
     * @param posY The Y coordinate of the tile on the tile map.
     * @return boolean Whether or not the tile has been activated.
     */
    // TODO add TileMap param
    public boolean activateTile (Entity activator, ItemObject item, int posX, int posY) {
        
        return true;
    }
    
    /**
     * Called when an entity is about to collide with the tile. Can be used to handle custom
     * collision logic, or to prevent collision all together.
     * 
     * @param collider The Entity that is about to collide with the tile.
     * @param posX The X coordinate of the tile on the tile map.
     * @param posY The z coordinate of the tile on the tile map.
     * @return boolean Whether or not the entity collided with the tile.
     */
    // TODO add TileMap param
    public boolean handleColision (Entity collider, int posX, int posY) {
        
        return true;
    }
    
    /**
     * Checks if the tile has a color multiplier. if it does, {@link Tile#getColorMultiplier(int, int)}}
     * will be used when rendering the tile.
     * 
     * @return boolean Whether or not the tile requires a color multiplier.
     */
    public boolean hasColorMultiplier () {
        
        return false;
    }
    
    /**
     * Provides a color multiplier to use when rendering the tile. The color multiplier is only
     * used when {@link Tile#hasColorMultiplier()} returns true. The color multiplier returned
     * is an RGB integer.
     *
     * @param posX The X coordinate of the tile on the tile map.
     * @param posY The Y coordinate of the tile on the tile map.
     * @return int An RGB integer which represents the color multiplier to use for the tile.
     */
    // TODO add TileMap param
    public int getColorMultiplier (int posX, int posY) {

        return Color.white.getRGB();
    }
}