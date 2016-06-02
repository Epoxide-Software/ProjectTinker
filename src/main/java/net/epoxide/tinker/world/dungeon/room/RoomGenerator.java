package net.epoxide.tinker.world.dungeon.room;

import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.world.dungeon.Cell;

public class RoomGenerator {
    
    /**
     * Generates the tiles for the room.
     * 
     * @param roomSize The maximum width and length of the room.
     * @param tiles The 2d tile array to place tiles in. Only contains tiles for the room.
     */
    public void generateRoomTiles (int roomSize, Tile[][] tiles) {
        
        for (int startX = 3; startX < roomSize - 3; startX++)
            for (int startY = 3; startY < roomSize - 3; startY++)
                tiles[startX][startY] = Tile.STONE;
    }
    
    /**
     * Handles the generation of paths for the room. Each room can generate it's own path
     * logic, however a room can only generate paths within its own space.
     * 
     * @param cell The cell to generate paths for.
     * @param tiles The tiles
     */
    public void generatePaths (Cell cell, Tile[][] tiles) {
        
        // TODO add pinwheel algorithm
        // TOOD create way for cells to communicate connections
    }
}