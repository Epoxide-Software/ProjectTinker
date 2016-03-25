package net.epoxide.tinker.client.render;

import com.shc.silenceengine.graphics.Batcher;

import net.epoxide.tinker.client.render.entity.RenderEntity;
import net.epoxide.tinker.client.render.entity.RenderEntityPlayer;
import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.world.TileMap;

public class RenderEntitySystem {
    
    /**
     * A named registry which holds all registered entity renderers. Allows for new renderers
     * to be registered and applied using a simple name.
     */
    public static final NamedRegistry<RenderEntity> REGISTRY = new NamedRegistry<>();
    
    // TODO replace with actual renderer
    private RenderEntity DEFAULT_RENDER = new RenderEntityPlayer();
    
    /**
     * Loops through each entity on a TileMap, and calls the registered renderer for each.
     * 
     * @param delta Change in time since the last render.
     * @param batcher An instance of the game batcher.
     * @param tileMap The map which the entity is on.
     */
    public void renderEntities (float delta, Batcher batcher, TileMap tileMap) {
        
        batcher.begin();
        
        for (Entity entity : tileMap.getEntityList()) {
            
            // TODO implement actual renderer lookup.
            DEFAULT_RENDER.render(batcher, entity);
        }
        
        batcher.end();
    }
}
