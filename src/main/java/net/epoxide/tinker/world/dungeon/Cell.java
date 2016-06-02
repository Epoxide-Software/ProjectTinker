package net.epoxide.tinker.world.dungeon;

import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.world.dungeon.room.RoomGenerator;

public class Cell {
    
    /**
     * A 2d array of tiles that represents the tiles in the room.
     */
    private final Tile[][] tiles;
    
    /**
     * A generator that handles all worldgen logic.
     */
    private final RoomGenerator generator;
    
    /**
     * Whether or not the cell is connected to another cell via a path.
     */
    private boolean hasBeenConnected;
    
    /**
     * Creates a new Cell object.
     * 
     * @param cellSize The width and height of the cell in tiles.
     * @param generator The generator used to handle worldgen logic.
     */
    public Cell(int cellSize, RoomGenerator generator) {
        
        this.tiles = new Tile[cellSize][cellSize];
        this.generator = generator;
    }
    
    /**
     * Retrieves the 2d tile map.
     * 
     * @return A 2d array of all tiles contained by the cell.
     */
    public Tile[][] getTiles () {
        
        return this.tiles;
    }
    
    /**
     * Retrieves the room generator.
     * 
     * @return The room generator object.
     */
    public RoomGenerator getRoomGenerator () {
        
        return this.generator;
    }
    
    /**
     * Sets whether or not a cell has been connected. This should be called by path gen logic
     * so cells don't get dropped.
     * 
     * @param connected Whether or not the room has been connected.
     */
    public void setConnected (boolean connected) {
        
        this.hasBeenConnected = connected;
    }
    
    /**
     * Checks if the room is connected to another.
     * 
     * @return Whether or not the cell is connected.
     */
    public boolean isConnected () {
        
        return this.hasBeenConnected;
    }
    
    /**
     * Called when a cell is being removed from the map. This is usually because the room was
     * not connected.
     * 
     * @return Whether or not the room can be removed. If false is returned, the room can not
     *         be removed.
     */
    public boolean onRemoved () {
        
        return true;
    }
}