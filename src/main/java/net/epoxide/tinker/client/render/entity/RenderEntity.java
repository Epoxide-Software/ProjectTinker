package net.epoxide.tinker.client.render.entity;

import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;
import net.epoxide.tinker.client.render.RenderSystem;
import net.epoxide.tinker.entity.Entity;

public class RenderEntity {
    
    public void render (Batcher batcher, Entity e) {

        float tileHalfSize = RenderSystem.tileSize / 2;

        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);

        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);

        batcher.vertex(e.getXPos() - tileHalfSize, e.getYPos() + tileHalfSize);
        batcher.vertex(e.getXPos() + tileHalfSize, e.getYPos() + tileHalfSize);
        batcher.vertex(e.getXPos() - tileHalfSize, e.getYPos() - tileHalfSize);

        batcher.vertex(e.getXPos() + tileHalfSize, e.getYPos() + tileHalfSize);
        batcher.vertex(e.getXPos() - tileHalfSize, e.getYPos() - tileHalfSize);
        batcher.vertex(e.getXPos() + tileHalfSize, e.getYPos() - tileHalfSize);
    }
}
