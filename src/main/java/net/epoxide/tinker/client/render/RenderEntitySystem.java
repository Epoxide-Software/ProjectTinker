package net.epoxide.tinker.client.render;

import com.shc.silenceengine.graphics.Batcher;

import net.epoxide.tinker.client.render.entity.RenderEntity;
import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.world.TileMap;

public class RenderEntitySystem {
    
    private static NamedRegistry<RenderEntity> REGISTRY = new NamedRegistry<>();
    
    private RenderEntity DEFAULT_RENDER = new RenderEntity();
    
    public void renderEntities (float delta, Batcher batcher, TileMap tileMap) {
        
        batcher.begin();
        for (Entity entity : tileMap.getEntityList()) {
//            if (entity.renderers.size() == 0)
//                DEFAULT_RENDER.render(batcher, entity);
//
//            entity.renderers.stream().filter(modelID -> modelID != null).forEach(modelID -> {
//                RenderEntity renderEntity = REGISTRY.getValue(modelID);
//                if (renderEntity == null)
                    DEFAULT_RENDER.render(batcher, entity);
//                else
//                    renderEntity.render(batcher, entity);
//            });
        }
        batcher.end();
    }
    
    public static void registerRenderer (String modelID, RenderEntity entityRenderer) {
        
        if (REGISTRY.hasName(modelID)) {
            System.out.println("Conflict");
            return;
        }
        
        REGISTRY.registerValue(modelID, entityRenderer);
    }
}
