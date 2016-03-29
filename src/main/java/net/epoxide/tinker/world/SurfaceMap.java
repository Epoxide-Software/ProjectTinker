package net.epoxide.tinker.world;

import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.util.RegistryName;
import net.epoxide.tinker.util.lang.I18n;

public class SurfaceMap extends TileMap {
    
    /**
     * A registry for maps. TileMaps do not need to be registered here for them to work,
     * however static locations that can be warped to must be registered here.
     */
    public static final NamedRegistry<TileMap> REGISTRY = new NamedRegistry<>();
    
    /**
     * The ID that the map is registered with.
     */
    public final RegistryName ID;
    
    public SurfaceMap(int width, int height, RegistryName id) {
        
        super(width, height, "");
        this.ID = id;
    }
    
    @Override
    public String getName () {
        
        return I18n.translate("tile." + this.ID.getDomain() + "." + this.ID.getName() + ".name");
    }
    
    @Override
    public void setName (String name) {
    
    }
}
