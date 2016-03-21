package net.epoxide.tinker.client.render.entity;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;

import net.epoxide.tinker.client.render.RenderSystem;
import net.epoxide.tinker.entity.Entity;

public class RenderEntityPlayer extends RenderEntity {
    
    public void render (Batcher batcher, Entity e) {
        
        float xPos = Display.getWidth() / 2;
        float yPos = Display.getHeight() / 2;
        
        float tileHalfSize = RenderSystem.tileSize / 2;

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
