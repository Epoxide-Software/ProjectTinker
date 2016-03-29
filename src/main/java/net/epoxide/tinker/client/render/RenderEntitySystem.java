package net.epoxide.tinker.client.render;

import com.shc.silenceengine.graphics.Batcher;

import net.epoxide.tinker.client.render.entity.RenderEntity;
import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.world.TileMap;

public class RenderEntitySystem {
    
    /**
     * A named registry which holds all registered entity renderers. Allows for new renderers
     * to be registered and applied using a simple name.
     */
    public static final NamedRegistry<RenderEntity> REGISTRY = new NamedRegistry<>();
    
    private final RenderEntity DEFAULT_RENDER = new RenderEntity();
    
    /**
     * Loops through each entity on a TileMap, and calls the registered renderer for each.
     *
     * @param delta Change in time since the last render.
     * @param batcher An instance of the game batcher.
     * @param tileMap The map which the entity is on.
     */
    public void renderEntities (float delta, Batcher batcher, TileMap tileMap) {
        
        batcher.begin();
        
        for (final Entity entity : tileMap.getEntityList()) {
            
            // if (entity.renderers.size() == 0)
            // DEFAULT_RENDER.render(batcher, entity);
            
            // TODO figure out how to handle multiple layers
            // entity.renderers.stream().filter(modelID -> modelID != null).forEach(modelID ->
            // {
            // RenderEntity renderEntity = REGISTRY.getValue(modelID);
            // if (renderEntity == null)
            // DEFAULT_RENDER.render(batcher, entity);
            // else
            // renderEntity.render(batcher, entity);
            
            // });
            
        }
        
        batcher.end();
    }
}
