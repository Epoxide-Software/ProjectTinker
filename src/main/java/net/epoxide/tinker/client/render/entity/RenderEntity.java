package net.epoxide.tinker.client.render.entity;

import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;

import net.epoxide.tinker.client.render.RenderSystem;
import net.epoxide.tinker.entity.Entity;

public class RenderEntity {
    
    /**
     * Handles the render tick for an entity.
     * 
     * @param batcher An instance of the game batcher.
     * @param entity The entity being rendered.
     */
    public void render (Batcher batcher, Entity entity) {
        
        final float tileHalfSize = RenderSystem.tileSize / 2;
        
        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
        
        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
        
        batcher.vertex(entity.getXPos() - tileHalfSize, entity.getYPos() + tileHalfSize);
        batcher.vertex(entity.getXPos() + tileHalfSize, entity.getYPos() + tileHalfSize);
        batcher.vertex(entity.getXPos() - tileHalfSize, entity.getYPos() - tileHalfSize);
        
        batcher.vertex(entity.getXPos() + tileHalfSize, entity.getYPos() + tileHalfSize);
        batcher.vertex(entity.getXPos() - tileHalfSize, entity.getYPos() - tileHalfSize);
        batcher.vertex(entity.getXPos() + tileHalfSize, entity.getYPos() - tileHalfSize);
    }
}
