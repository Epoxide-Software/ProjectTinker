package net.epoxide.tinker.world.dungeon;

import java.util.Arrays;

import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.world.TileMap;

public class Dungeon {
    
    /**
     * A registry that holds all of the dungeons registered into the game.
     */
    public static final NamedRegistry<Dungeon> REGISTRY = new NamedRegistry<Dungeon>();
    
    public static final Dungeon DEFAULT = new Dungeon("default", 0);
    
    /**
     * The name used in dungeon name localization.
     */
    private final String name;
    
    /**
     * The amount of floors that this dungeon makes up.
     */
    private final int floors;
    
    /**
     * The parent dungeon which leads to this dungeon.
     */
    private Dungeon parent;
    
    /**
     * The child dungeon that this dungeon would lead to.
     */
    private Dungeon child;
    
    /**
     * Creates a Dungeon instance using a localization name and the amount of floors held by
     * the dungeon.
     * 
     * @param name The name used for the name localization of the Dungeon.
     * @param floors The amount of floors that the dungeon is made up of.
     */
    public Dungeon(String name, int floors) {
        
        this.name = name;
        this.floors = floors;
    }
    
    /**
     * Gets the name used for the name localization of the Dungeon.
     * 
     * @return String The localization name for the dungeon.
     */
    public String getName () {
        
        return name;
    }
    
    /**
     * Gets the amount of floors held by the dungeon. This is the amount of floors the player
     * will have to go through before completing the dungeon.
     * 
     * @return int The amount of floors in the dungeon.
     */
    public int getFloors () {
        
        return floors;
    }
    
    /**
     * Gets the parent dungeon which leads to this dungeon. Not required, can be null.
     * 
     * @return Dungeon The parent dungeon.
     */
    public Dungeon getParent () {
        
        return parent;
    }
    
    /**
     * Sets the parent dungeon which leads to this dungeon.
     * 
     * @param parent The parent dungeon to set.
     */
    public void setParent (Dungeon parent) {
        
        this.parent = parent;
    }
    
    /**
     * Gets the child dungeon which this dungeon leads into.
     * 
     * @return Dungeon The child dungeon.
     */
    public Dungeon getChild () {
        
        return child;
    }
    
    /**
     * Sets the child dungeon which this dungeon leads to.
     * 
     * @param child The child dungeon to sed.
     */
    public void setChild (Dungeon child) {
        
        this.child = child;
    }
    
    /**
     * Provides a way to generate a dungeon map depending on the Dungeon. This allows for
     * different world generation based on the dungeon type. This will also set the Dungeon for
     * the TileMap to this dungeon.
     * 
     * @param map The TileMap to populate with worldgen.
     */
    public void generateMap (TileMap map) {
        
        map.setDungeon(this);
        for (Tile[] row : map.getTileMap())
            Arrays.fill(row, (Math.random() < 0.5) ? Tile.VOID : Tile.STONE);
    }
}