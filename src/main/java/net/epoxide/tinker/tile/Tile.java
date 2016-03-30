package net.epoxide.tinker.tile;

import java.awt.Color;

import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.util.ItemObject;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.util.RegistryName;
import net.epoxide.tinker.util.lang.I18n;
import net.epoxide.tinker.world.TileMap;

public class Tile {
    
    /**
     * A named registry which holds all registered tiles. Due to the way Tile IDs are handled,
     * registration using this registry should only be done through {@link #registerTile(Tile)}
     * which uses the Tile's static ID value.
     */
    public static final NamedRegistry<Tile> REGISTRY = new NamedRegistry<>();
    
    public static final Tile VOID = registerTile(new Tile("tinker:void"));
    public static final Tile STONE = registerTile(new Tile("tinker:stone"));
    public static final Tile SLIME = registerTile(new TileSlime("tinker:slime"));
    
    /**
     * The ID that the tile is registered under.
     */
    public final RegistryName ID;
    
    /**
     * Constructs a tile with the ID that the tile was registered under.
     *
     * @param id The ID that the tile is registered under.
     */
    public Tile(String id) {
        
        this.ID = new RegistryName(id);
    }
    
    /**
     * Registers a tile with the {@link #REGISTRY} using the ID stored in the Tile. This should
     * be used over directly accessing the REGISTRY.
     *
     * @param tile The Tile to register.
     * @return Tile The same Tile being registered. Provided to make life easier.
     */
    public static Tile registerTile (Tile tile) {
        
        return REGISTRY.registerValue(tile.ID, tile);
    }
    
    /**
     * Gets a Tile from the {@link #REGISTRY} which is associated with the name. If no tile
     * exists, {@link #VOID} will be returned.
     *
     * @param name The name of the Tile you are looking for.
     * @return Tile The Tile associated with the specified name, or {@link #VOID} if no tile is
     *         found.
     */
    public static Tile getTileByName (String name) {
        
        final Tile tile = REGISTRY.getValue(name);
        return tile == null ? VOID : tile;
    }
    
    /**
     * Gets the name for the tile translated with the current language.
     *
     * @return String the name for the Tile.
     */
    public String getTranslatedName () {
        
        return I18n.translate("tile." + this.ID.getDomain() + "." + this.ID.getName() + ".name");
    }
    
    /**
     * Called when the tile is being placed on a TileMap. Can be used to initialize data,
     * trigger events, or to prevent the tile from being placed.
     *
     * @param map The TileMap where the Tile was placed.
     * @param posX The X coordinate of the tile on the tile map.
     * @param posY The Y coordinate of the tile on the tile map.
     * @return boolean Whether or not the tile should be placed.
     */
    public boolean placeTile (TileMap map, int posX, int posY) {
        
        return true;
    }
    
    /**
     * Called when the tile is being removed from a TileMap. Can be used to clear data, trigger
     * events, or to prevent the tile from being removed..
     *
     * @param map The TileMap where the Tile was removed.
     * @param posX The X coordinate of the tile on the tile map.
     * @param posY The Y coordinate of the tile on the tile map.
     * @return boolean Whether or not the tile should be removed..
     */
    public boolean removeTile (TileMap map, int posX, int posY) {
        
        return true;
    }
    
    /**
     * Called when the tile is being harvested by an entity. Can be used to handle custom
     * harvest logic, or prevent harvesting.
     *
     * @param map The TileMap where the Tile was harvested.
     * @param harvester An instance of the Entity that is trying to harvest the Tile.
     * @param item The Item used to harvest the tile. Might be null.
     * @param posX The X coordinate of the tile on the tile map.
     * @param posY The Y coordinate of the tile on the tile map.
     * @return boolean Whether or not the tile should be harvested.
     */
    public boolean harvestTile (TileMap map, Entity harvester, ItemObject item, int posX, int posY) {
        
        return true;
    }
    
    /**
     * Called when the tile is being activated by an entity. Can be used to handle custom
     * activation logic.
     *
     * @param map The TileMap where the Tile was activated.
     * @param activator An instance of the Entity that is trying to activate the Tile.
     * @param item The Item used to activate the tile. Might be null.
     * @param posX The X coordinate of the tile on the tile map.
     * @param posY The Y coordinate of the tile on the tile map.
     * @return boolean Whether or not the tile has been activated.
     */
    public boolean activateTile (TileMap map, Entity activator, ItemObject item, int posX, int posY) {
        
        return true;
    }
    
    /**
     * Called when an entity is about to collide with the tile. Can be used to handle custom
     * collision logic, or to prevent collision all together.
     *
     * @param map The TileMap where the Tile was collided with.
     * @param collider The Entity that is about to collide with the tile.
     * @param posX The X coordinate of the tile on the tile map.
     * @param posY The z coordinate of the tile on the tile map.
     * @return boolean Whether or not the entity collided with the tile.
     */
    public boolean handleColision (TileMap map, Entity collider, int posX, int posY) {
        
        return true;
    }
    
    /**
     * Checks if the tile has a color multiplier. if it does,
     * {@link Tile#getColorMultiplier(TileMap, int, int)} will be used when rendering the tile.
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
     * @param map The TileMap where the Tile exists.
     * @param posX The X coordinate of the tile on the tile map.
     * @param posY The Y coordinate of the tile on the tile map.
     * @return int An RGB integer which represents the color multiplier to use for the tile.
     */
    public int getColorMultiplier (TileMap map, int posX, int posY) {
        
        return Color.white.getRGB();
    }
    
    public int getRenderPasses () {
        
        return 1;
    }
    
    public RegistryName getTexture (int renderPass) {
        
        return this.ID;
    }
}