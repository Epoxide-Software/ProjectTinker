package net.epoxide.tinker.client.render;

import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;

import net.epoxide.tinker.TinkerGame;
import net.epoxide.tinker.client.render.textures.TextureSystem;
import net.epoxide.tinker.client.render.textures.TileTexture;
import net.epoxide.tinker.entity.living.EntityPlayer;
import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.world.TileMap;

public class RenderWorldSystem {
    
    public void renderWorld (float delta, Batcher batcher, TileMap tileMap) {
        
        EntityPlayer entityPlayer = ((TinkerGame) TinkerGame.getInstance()).entityPlayer;
        
        float x = entityPlayer.getXPos();
        float y = entityPlayer.getYPos();
        
        int tileX = (int) (RenderSystem.tileWidth / 2 + x);
        int tileY = (int) (RenderSystem.tileHeight / 2 + y);
        
        float renderX = (float) ((x - Math.floor(x)) * RenderSystem.tileSize) - RenderSystem.tileSize / 2;
        float renderY = (float) ((y - Math.floor(y)) * RenderSystem.tileSize) - RenderSystem.tileSize / 2;
        
        TextureSystem.texture.bind();
        batcher.begin();
        for (int xx = 0; xx < RenderSystem.tileWidth + 1; xx++) {
            for (int yy = 0; yy < RenderSystem.tileHeight + 1; yy++) {
                
                float xRender = renderX + xx * RenderSystem.tileSize;
                float yRender = renderY + yy * RenderSystem.tileSize;
                Tile tile = tileMap.getTile(xx - tileX, yy - tileY);
                
                if (tile != null && tile != Tile.VOID) {
                    TileTexture texture = TextureSystem.REGISTRY.getValue(tile.getTexture(0));
                    if (texture == null)
                        texture = TextureSystem.REGISTRY.getValue("missing");
                        
                    batcher.texCoord(texture.minU, texture.maxV);
                    batcher.texCoord(texture.maxU, texture.maxV);
                    batcher.texCoord(texture.minU, texture.minV);
                    
                    batcher.texCoord(texture.maxU, texture.maxV);
                    batcher.texCoord(texture.minU, texture.minV);
                    batcher.texCoord(texture.maxU, texture.minV);
                    
                    batcher.color(Color.TRANSPARENT);
                    batcher.color(Color.TRANSPARENT);
                    batcher.color(Color.TRANSPARENT);
                    
                    batcher.color(Color.TRANSPARENT);
                    batcher.color(Color.TRANSPARENT);
                    batcher.color(Color.TRANSPARENT);
                    
                    batcher.vertex(xRender, yRender + RenderSystem.tileSize);
                    batcher.vertex(xRender + RenderSystem.tileSize, yRender + RenderSystem.tileSize);
                    batcher.vertex(xRender, yRender);
                    
                    batcher.vertex(xRender + RenderSystem.tileSize, yRender + RenderSystem.tileSize);
                    batcher.vertex(xRender, yRender);
                    batcher.vertex(xRender + RenderSystem.tileSize, yRender);
                    
                }
            }
        }
        batcher.end();
    }
}
