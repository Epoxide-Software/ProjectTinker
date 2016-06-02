package net.epoxide.tinker.world.dungeon;

import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.util.WeightedSelector;
import net.epoxide.tinker.util.WeightedSelector.WeightedEntry;
import net.epoxide.tinker.world.TileMap;
import net.epoxide.tinker.world.dungeon.room.RoomGenerator;

public class DungeonGenerator {
    
    /**
     * A weighted selector used to generate rooms for cells.
     */
    private final WeightedSelector<RoomGenerator> roomSelector = new WeightedSelector<RoomGenerator>();
    
    /**
     * Builder method for adding rooms to a generator.
     * 
     * @param room The room to add.
     * @param weight The weight to add it at.
     * @return The dungeon generator instance to allow builder method convenience.
     */
    public DungeonGenerator addRoom (RoomGenerator room, int weight) {
        
        this.roomSelector.addEntry(new WeightedEntry<RoomGenerator>(room, weight));
        return this;
    }
    
    /**
     * Generates a new TileMap for the dungeon type.
     * 
     * @param name The human readable name for the dungeon.
     * @param size A predefined size for the dungeon.
     * @param minRooms The smallest number of rooms that can generate.
     * @return A TileMap which can be played on.
     */
    public TileMap generateMap (String name, DungeonSize size, int minRooms) {
        
        return this.generateMap(name, size.getCellMapSize(), size.getCellSize(), size.getMapSize(), minRooms);
    }
    
    /**
     * Generates a new TileMap for the dungeon type.
     * 
     * @param name The human readable name for the dungeon.
     * @param cellMapSize The width and height of the Cell map.
     * @param cellSize The width and height of a cell in tiles.
     * @param mapSize The width and the height of the map in tiles.
     * @param minRooms The smallest number of rooms that can generate.
     * @return A TileMap which can be played on.
     */
    public TileMap generateMap (String name, int cellMapSize, int cellSize, int mapSize, int minRooms) {
        
        final Cell[][] cells = new Cell[cellMapSize][cellMapSize];
        
        // Initial room generation
        for (int cellX = 0; cellX < cellMapSize; cellX++)
            for (int cellY = 0; cellY < cellMapSize; cellY++) {
                
                final RoomGenerator room = this.roomSelector.getRandomEntry().getEntry();
                final Cell cell = new Cell(cellSize, room);
                
                cells[cellX][cellY] = cell;
                room.generateRoomTiles(cellSize, cell.getTiles());
            }
            
        // Allows for special rooms to override the existing rooms.
        this.generateSpecialRooms(cells);
        
        // Second loop to generate paths
        for (int cellX = 0; cellX < cellMapSize; cellX++)
            for (int cellY = 0; cellY < cellMapSize; cellY++) {
                
                final Cell cell = cells[cellX][cellY];
                cell.getRoomGenerator().generatePaths(cell, cell.getTiles());
            }
            
        // Third loop to remove all rooms that are not connected to anything.
        for (int cellX = 0; cellX < cellMapSize; cellX++)
            for (int cellY = 0; cellY < cellMapSize; cellY++) {
                
                final Cell cell = cells[cellX][cellY];
                
                if (!cell.isConnected() && cell.onRemoved())
                    cells[cellX][cellY] = null;
            }
            
        return this.stitchCells(cells, cellMapSize, cellSize, name);
    }
    
    /**
     * Allows for special rooms to be intentionally inserted into the cell map.
     * 
     * @param cells A 2d array which represents the map of cells.
     */
    public void generateSpecialRooms (Cell[][] cells) {
    
    }
    
    /**
     * Stitches cells together into a unified map.
     * 
     * @param cells The 2d array of cells to stitch together.
     * @param cellMapSize The width and height of the cell map.
     * @param cellSize The width and height of a cell in tiles.
     * @param name The human readable name for the map.
     * @return A TileMap that represents all of the cells stitched togehter.
     */
    // TODO lclc98 probably has a cleaner way to do this.
    public TileMap stitchCells (Cell[][] cells, int cellMapSize, int cellSize, String name) {
        
        final int tileMapSize = cellMapSize * cellSize;
        final Tile[][] tiles = new Tile[tileMapSize][tileMapSize];
        
        for (int cellX = 0; cellX < cellMapSize; cellX++)
            for (int cellY = 0; cellY < cellMapSize; cellY++) {
                
                final Cell cell = cells[cellX][cellY];
                final Tile[][] cellTiles = cell.getTiles();
                
                for (int tileX = 0; tileX < cellSize; tileX++)
                    for (int tileY = 0; tileY < cellSize; tileY++)
                        tiles[cellX * cellSize + tileX][cellY * cellSize + tileY] = cellTiles[tileX][tileY];
            }
            
        return new TileMap(tiles, name);
    }
}