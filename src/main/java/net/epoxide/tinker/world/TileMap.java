package net.epoxide.tinker.world;

import java.util.ArrayList;
import java.util.List;

import net.darkhax.opennbt.tags.CompoundTag;
import net.darkhax.opennbt.tags.Tag;

import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.entity.EntityStat;
import net.epoxide.tinker.entity.StatModifier;
import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.util.StatProvider;
import net.epoxide.tinker.world.dungeon.Dungeon;

public class TileMap implements StatProvider {
    
    private Dungeon dungeon;
    
    /**
     * A List of all entities on the TileMap.
     */
    protected final List<Entity> entityList;
    
    /**
     * The tile height of the map.
     */
    protected int height;
    
    /**
     * A human readable name for the map. Not an ID!
     */
    protected String name;
    
    /**
     * A 2D array of CompoundTag. This is used to store tile data in a persistent way.
     */
    protected CompoundTag[][] tileData;
    
    /**
     * A 2D array of the tiles in the map.
     */
    protected Tile[][] tileMap;
    
    /**
     * The tile width of the map.
     */
    protected int width;
    
    /**
     * Constructs a new TileMap using basic data. The Tile array will have the correct size,
     * however it will be empty, same with the data array. The List of entities will be
     * initialized as empty.
     * 
     * @param width The amount of tiles on the Z axis.
     * @param height The amount of tiles on the Y axis.
     * @param name A human readable name for the map. This should not be used as an ID.
     */
    public TileMap(int width, int height, String name) {
        
        this.width = width;
        this.height = height;
        this.name = name;
        this.tileMap = new Tile[width][height];
        this.tileData = new CompoundTag[width][height];
        this.entityList = new ArrayList<>();
    }
    
    /**
     * Adds an entity to the TileMap without actually spawning it. Calls
     * {@link Entity#setCurrentMap(TileMap)} which will call
     * {@link Entity#onJoinWorld(TileMap)}.
     * 
     * @param entity The entity to add to the map.
     */
    public void addEntity (Entity entity) {
        
        this.entityList.add(entity);
        entity.setCurrentMap(this);
    }
    
    /**
     * Gets the Dungeon type of this map. This might be null.
     * 
     * @return Dungeon The dungeon type for this map.
     */
    public Dungeon getDungeon () {
        
        return this.dungeon;
    }
    
    /**
     * Retrieves the list of entities on this map.
     * 
     * @return List<Entity> The list of entities on the map.
     */
    public List<Entity> getEntityList () {
        
        return this.entityList;
    }
    
    /**
     * Retrieves the height of the map.
     * 
     * @return int The map height.
     */
    public int getHeight () {
        
        return this.height;
    }
    
    /**
     * Overrides the 2D tag array. This should be used sparingly, as it will completely
     * override all of the tile data!
     * 
     * @param tileData The new 2D tag array.
     */
    
    /**
     * Retrieves the name of the map. This should not be treated as an ID, and will not always
     * be unique.
     * 
     * @return String The name of the map.
     */
    public String getName () {
        
        return this.name;
    }
    
    /**
     * Gets a tile from the TileMap. This will check that the location specified is valid. If
     * you know that the location is valid, and you are getting lots of tiles,
     * {@link #getTileUnsafely(int, int)} will be more efficient.
     * 
     * @param posX The X position to get the tile from.
     * @param posY The Y position to get the tile from.
     * @return Tile The tile at the specified location. This can be null, and will be null if
     *         the location is invalid.
     */
    public Tile getTile (int posX, int posY) {
        
        if (this.isValidLocation(posX, posY))
            return this.tileMap[posX][posY];
            
        return null;
    }
    
    /**
     * Retrieves the 2D array of tags stored on the map. Some entries may be null.
     * 
     * @return Tag[][] A 2D array of tags on the map.
     */
    public Tag[][] getTileData () {
        
        return this.tileData;
    }
    
    /**
     * Retrieves tile data at the specified location. This will check to make sure the location
     * is valid before getting the tile data. If you already know the position is valid, you
     * can use {@link #getTileDataUnsafely(int, int)} which is a bit faster.
     * 
     * @param posX The X position of the tile to get data for.
     * @param posY The Y position of the tile to get data for.
     * @return Tag A Tag that was stored at the specified location. This can sometimes be null
     *         if no data was found, or an invalid location was used.
     */
    public Tag getTileData (int posX, int posY) {
        
        if (this.isValidLocation(posX, posY))
            return this.tileData[posX][posY];
            
        return null;
    }
    
    /**
     * Retrieves tile data without verifying that the specified location is valid. This is a
     * bit faster than {@link #getTileData(int, int)}.
     * 
     * @param posX The X position of the tile to get data for.
     * @param posY The Y position of the tile to get data for.
     * @return Tag A Tag that was stored at the specified location. This can be null if no data
     *         was found.
     */
    public CompoundTag getTileDataUnsafely (int posX, int posY) {
        
        CompoundTag tag = this.tileData[posX][posY];
        
        if (tag == null) {
            
            tag = new CompoundTag("TileData");
            this.tileData[posX][posY] = tag;
        }
        
        return tag;
    }
    
    /**
     * Retrieves the 2D array of tiles stored on the map. Some entries might be null.
     * 
     * @return Tile[][] A 2D array of the tiles on the map.
     */
    public Tile[][] getTileMap () {
        
        return this.tileMap;
    }
    
    /**
     * Gets a tile from the TileMap without checking if the location is valid. This is a bit
     * faster than {@link #getTile(int, int)}.
     * 
     * @param posX The X position to get the tile from.
     * @param posY The Y position to get the tile from.
     * @return Tile The tile at the specified location. This can be null.
     */
    public Tile getTileUnsafely (int posX, int posY) {
        
        final Tile tile = this.tileMap[posX][posY];
        return tile == null ? Tile.VOID : tile;
    }
    
    /**
     * Retrieves the width of the map.
     * 
     * @return int The map width.
     */
    public int getWidth () {
        
        return this.width;
    }
    
    /**
     * Checks if a location is valid for the map. Be default, only locations that are within
     * the X and Y bounds of the map are valid.
     * 
     * @param posX The X position to check for.
     * @param posY The Y position to check for.
     * @return boolean Whether or not the location is valid.
     */
    public boolean isValidLocation (int posX, int posY) {
        
        return posX >= 0 && posX < this.width && posY >= 0 && posY < this.height;
    }
    
    /**
     * Removes a tile from the TileMap. This will check that the location specified is valid.
     * This will also call {@link Tile#removeTile(TileMap, int, int)} which will allow the tile
     * to clean up, or do removal effects, it can also prevent removal. If you know that the
     * location is valid, and you are setting lots of tiles, #setTileUnsafely(Tile, int, int)
     * will be more efficient.
     * 
     * @param posX The X position of the tile to remove.
     * @param posY The Y position of the tile to remove.
     */
    public void removeTile (int posX, int posY) {
        
        if (this.isValidLocation(posX, posY)) {
            
            final Tile tile = this.tileMap[posX][posY];
            
            if (tile != null && tile.removeTile(this, posX, posY)) {
                
                this.tileMap[posX][posY] = null;
                this.removeTileDataUnsafely(posX, posY);
            }
        }
    }
    
    /**
     * Removes tile data at the specified location. This will check to make sure the location
     * is valid before removing the data. If you already know the location is valid, you can
     * use {@link #removeTileDataUnsafely(int, int)} which is a bit faster.
     * 
     * @param posX The X position of the tile to remove data for.
     * @param posY The Y position of the tile to remove data for.
     */
    public void removeTileData (int posX, int posY) {
        
        if (this.isValidLocation(posX, posY))
            this.tileData[posX][posY] = null;
    }
    
    /**
     * Removes tile data without verifying that the location is valid. This is a bit faster
     * that {@link #removeTileData(int, int)}.
     * 
     * @param posX The X position of the tile to remove data for.
     * @param posY The Y position of the tile to remove data for.
     */
    public void removeTileDataUnsafely (int posX, int posY) {
        
        this.tileData[posX][posY] = null;
    }
    
    /**
     * Removes a tile from the TileMap without checking if the location is valid. This is a bit
     * faster than {@link #removeTile(int, int)}. This will still call
     * {@link Tile#removeTile(TileMap, int, int)} which will allow the tile to clean up, or do
     * removal effects, it can also prevent removal.
     * 
     * @param posX The X position of the tile to remove.
     * @param posY The Y position of the tile to remove.
     */
    public void removeTileUnsafely (int posX, int posY) {
        
        final Tile tile = this.tileMap[posX][posY];
        
        if (tile != null && tile.removeTile(this, posX, posY)) {
            
            this.tileMap[posX][posY] = null;
            this.removeTileDataUnsafely(posX, posY);
        }
    }
    
    /**
     * Sets the Dungeon type for the map.
     * 
     * @param dungeon The dungeon type to set for this map.
     */
    public void setDungeon (Dungeon dungeon) {
        
        this.dungeon = dungeon;
    }
    
    /**
     * Sets the name of the map. This should not be treated as an ID, and does not have to be
     * unique.
     * 
     * @param name The name to apply to the map.
     */
    public void setName (String name) {
        
        this.name = name;
    }
    
    /**
     * Sets a tile on the TileMap. This will check that the location specified is valid. This
     * will call {@link Tile#placeTile(TileMap, int, int)} which will allow the tile to
     * initialize, and can cancel the placing of the tile. If you know that the location is
     * valid, and you are setting lots of tiles, {@link #setTileUnsafely(Tile, int, int)} will
     * be more efficient.
     * 
     * @param tile The Tile to set on the map.
     * @param posX The X position to set the tile at.
     * @param posY The Y position to set the tile at.
     */
    public void setTile (Tile tile, int posX, int posY) {
        
        if (this.isValidLocation(posX, posY) && tile.placeTile(this, posX, posY))
            this.tileMap[posX][posY] = tile;
    }
    
    /**
     * Sets tile data at the specified location. This will check to make sure the location is
     * valid before setting the data. If you already know the location is valid, you can use
     * {@link #setTileDataUnsafely(CompoundTag, int, int)} which is a bit faster.
     * 
     * @param tag The data tag to store at the location.
     * @param posX The X position of the tile to set data for.
     * @param posY The Y position of the tile to set data for.
     */
    public void setTileData (CompoundTag tag, int posX, int posY) {
        
        if (this.isValidLocation(posX, posY))
            this.tileData[posX][posY] = tag;
    }
    
    /**
     * Overrides the 2D tag array. This should be used sparingly, as it will completely
     * override all of the tile data! A valid use case would be for procedurally generated tile
     * maps.
     * 
     * @param tileData The new 2D tag array.
     */
    public void setTileData (CompoundTag[][] tileData) {
        
        this.tileData = tileData;
    }
    
    /**
     * Sets tile data without verifying that the location is valid. This is a bit faster than
     * {@link #setTileData(CompoundTag, int, int)}.
     * 
     * @param tag The data tag to store at the location.
     * @param posX The X position of the tile to set data for.
     * @param posY The Y position of the tile to set data for.
     */
    public void setTileDataUnsafely (CompoundTag tag, int posX, int posY) {
        
        this.tileData[posX][posY] = tag;
    }
    
    /**
     * Overrides the 2D tile array. This should be used sparingly, as it will completely
     * override all of the tiles. A valid use case would be for procedurally generated tile
     * maps.
     * 
     * @param tileMap The new 2D tile array.
     */
    public void setTileMap (Tile[][] tileMap) {
        
        this.tileMap = tileMap;
    }
    
    /**
     * Sets a tile on the TileMap without checking if the location is valid. This is a bit
     * faster than {@link #setTile(Tile, int, int)}. This will still call
     * {@link Tile#placeTile(TileMap, int, int)} which will allow the tile to initialize, or
     * cancel the placement.
     * 
     * @param tile The Tile to set on the map.
     * @param posX The X position to set the tile at.
     * @param posY The Y position to set the tile at.
     */
    public void setTileUnsafely (Tile tile, int posX, int posY) {
        
        if (tile.placeTile(this, posX, posY))
            this.tileMap[posX][posY] = tile;
    }
    
    /**
     * Spawns an entity into the TileMap. This will add the entity to the entity list and call
     * {@link Entity#onSpawn(TileMap)} and {@link Entity#setCurrentMap(TileMap)} which will
     * call {@link Entity#onJoinWorld(TileMap)}.
     * 
     * @param entity The entity to spawn.
     */
    public void spawnEntity (Entity entity) {
        
        this.entityList.add(entity);
        entity.onSpawn(this);
        entity.setCurrentMap(this);
    }

    @Override
    public StatModifier getStatModifier (Entity entity, EntityStat type) {
        
        return null;
    }
}