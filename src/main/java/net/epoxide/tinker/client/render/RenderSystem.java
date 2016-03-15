package net.epoxide.tinker.client.render;

import com.shc.silenceengine.graphics.Batcher;
import net.epoxide.tinker.client.render.entity.RenderEntity;
import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.util.NamedRegistry;

import java.util.ArrayList;

public class RenderSystem {

    private static NamedRegistry<RenderEntity> REGISTRY = new NamedRegistry<>();

    private RenderEntity DEFAULT_RENDER = new RenderEntity();

    public ArrayList<Entity> entityList = new ArrayList<>();

    public void renderEntities (Batcher batcher) {

        batcher.begin();
        for (Entity entity : entityList) {
            if (entity.renderers.size() == 0)
                DEFAULT_RENDER.render(batcher, entity);

            for (String modelID : entity.renderers) {
                if (modelID != null) {
                    RenderEntity renderEntity = REGISTRY.getValue(modelID);
                    if (renderEntity == null)
                        DEFAULT_RENDER.render(batcher, entity);
                    else
                        renderEntity.render(batcher, entity);
                }
            }
        }
        batcher.end();
    }

    public void registerRenderer (String modelID, RenderEntity entityRenderer) {

        if (REGISTRY.hasName(modelID)) {
            System.out.println("Conflict");
            return;
        }

        REGISTRY.registerValue(modelID, entityRenderer);
    }
}
