package net.epoxide.tinker.world.dungeon;

public enum DungeonSize {
    
    SMALL(80, 16),
    MEDIUM(128, 32),
    LARGE(512, 64),
    COLOSSAL(1024, 64);
    
    /**
     * The amount of tiles per axis of the map. Maps are square so this is height and width.
     */
    private int mapSize;
    
    /**
     * The amount of tiles per axis of a cell. Cells are square so this is height and width.
     */
    private int cellSize;
    
    /**
     * Constructs a new dungeon size. Used to determine how many dungeon rooms should generate,
     * and how many tiles to use for the tile map. It is important that the cell size is
     * divisible by the map size with no remainder.
     * 
     * @param mapSize The height and length of the map in tiles.
     * @param cellSize The height and length of a cell in tiles.
     */
    DungeonSize(int mapSize, int cellSize) {
        
        this.mapSize = mapSize;
        this.cellSize = cellSize;
    }
    
    /**
     * Gets the width and height of the map in tiles.
     * 
     * @return The width and height of the map in tiles.
     */
    public int getMapSize () {
        
        return this.mapSize;
    }
    
    /**
     * Gets the width and height of a cell in tiles.
     * 
     * @return The width and height of a cell in tiles.
     */
    public int getCellSize () {
        
        return this.cellSize;
    }
    
    /**
     * Calculates the width and height of the cell map.
     * 
     * @return The width and height of the cell map.
     */
    public int getCellMapSize () {
        
        return this.mapSize / this.cellSize;
    }
}