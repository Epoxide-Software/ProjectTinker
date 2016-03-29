package net.epoxide.tinker.client.render.entity;

import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;

import net.epoxide.tinker.client.render.RenderSystem;
import net.epoxide.tinker.entity.Entity;

public class RenderEntityPlayer extends RenderEntity {
    
    @Override
    public void render (Batcher batcher, Entity enttity) {
        
        final float xPos = RenderSystem.displayWidth / 2;
        final float yPos = RenderSystem.displayHeight / 2;
        
        final float tileHalfSize = RenderSystem.tileSize / 2;
        
        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
        
        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
        
        batcher.vertex(xPos - tileHalfSize, yPos + tileHalfSize);
        batcher.vertex(xPos + tileHalfSize, yPos + tileHalfSize);
        batcher.vertex(xPos - tileHalfSize, yPos - tileHalfSize);
        
        batcher.vertex(xPos + tileHalfSize, yPos + tileHalfSize);
        batcher.vertex(xPos - tileHalfSize, yPos - tileHalfSize);
        batcher.vertex(xPos + tileHalfSize, yPos - tileHalfSize);
    }
}