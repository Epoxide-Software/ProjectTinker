package net.epoxide.tinker.tile;

import net.epoxide.tinker.util.RegistryName;

//TODO Test Class remove
public class TileSlime extends Tile {
    
    /**
     * Constructs a tile with the ID that the tile was registered under.
     *
     * @param id The ID that the tile is registered under.
     */
    public TileSlime(String id) {
        
        super(id);
    }
    
    @Override
    public int getRenderPasses () {
        
        return 2;
    }
    
    @Override
    public RegistryName getTexture (int renderPass) {
        
        return renderPass == 1 ? this.ID : new RegistryName("tinker:stone");
    }
}
