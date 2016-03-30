package net.epoxide.tinker.client.render;

import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;

import net.epoxide.tinker.TinkerGame;
import net.epoxide.tinker.client.render.textures.AtlasTexture;
import net.epoxide.tinker.client.render.textures.TextureManager;
import net.epoxide.tinker.entity.living.EntityPlayer;
import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.world.TileMap;

public class RenderWorldSystem {
    
    /**
     * Handles a render tick of the world by rendering all of the tiles on the map.
     *
     * @param delta Change in time since the last render.
     * @param batcher An instance of the game batcher.
     * @param tileMap The map which the entity is on.
     */
    public void renderWorld (float delta, Batcher batcher, TileMap tileMap) {
        
        final EntityPlayer entityPlayer = TinkerGame.entityPlayer;
        
        final float x = entityPlayer.getXPos();
        final float y = entityPlayer.getYPos();
        
        final int tileX = (int) (RenderSystem.renderWidth / 2 + x);
        final int tileY = (int) (RenderSystem.renderHeight / 2 + y);
        
        final float renderX = (float) ((x - Math.floor(x)) * RenderSystem.tileSize) - RenderSystem.tileSize / 2;
        final float renderY = (float) ((y - Math.floor(y)) * RenderSystem.tileSize) - RenderSystem.tileSize / 2;
        
        TextureManager.texture.bind();
        batcher.begin();
        
        int renderPass = 0;
        int maxPass = 1;
        while (renderPass < maxPass) {
            if (renderPass != 0) {
                batcher.end();
                batcher.begin();
            }
            
            for (int xx = 0; xx < RenderSystem.renderWidth + 1; xx++)
                for (int yy = 0; yy < RenderSystem.renderHeight + 1; yy++) {
                    
                    final float xRender = renderX + xx * RenderSystem.tileSize;
                    final float yRender = renderY + yy * RenderSystem.tileSize;
                    final Tile tile = tileMap.getTile(xx - tileX, yy - tileY);
                    
                    if (tile != null && tile != Tile.VOID) {
                        if (tile.getRenderPasses() > maxPass)
                            maxPass = tile.getRenderPasses();
                            
                        if (renderPass + 1 <= tile.getRenderPasses()) {
                            AtlasTexture atlasTexture = TextureManager.REGISTRY.getValue(tile.getTexture(renderPass));
                            if (atlasTexture == null)
                                atlasTexture = TextureManager.REGISTRY.getValue("missing");
                                
                            batcher.vertex(xRender, yRender + RenderSystem.tileSize);
                            batcher.texCoord(atlasTexture.getMinU(), atlasTexture.getMaxV());
                            batcher.color(Color.TRANSPARENT);
                            
                            batcher.vertex(xRender + RenderSystem.tileSize, yRender + RenderSystem.tileSize);
                            batcher.texCoord(atlasTexture.getMaxU(), atlasTexture.getMaxV());
                            batcher.color(Color.TRANSPARENT);
                            
                            batcher.vertex(xRender, yRender);
                            batcher.texCoord(atlasTexture.getMinU(), atlasTexture.getMinV());
                            batcher.color(Color.TRANSPARENT);
                            
                            batcher.vertex(xRender + RenderSystem.tileSize, yRender + RenderSystem.tileSize);
                            batcher.texCoord(atlasTexture.getMaxU(), atlasTexture.getMaxV());
                            batcher.color(Color.TRANSPARENT);
                            
                            batcher.vertex(xRender, yRender);
                            batcher.texCoord(atlasTexture.getMinU(), atlasTexture.getMinV());
                            batcher.color(Color.TRANSPARENT);
                            
                            batcher.vertex(xRender + RenderSystem.tileSize, yRender);
                            batcher.texCoord(atlasTexture.getMaxU(), atlasTexture.getMinV());
                            batcher.color(Color.TRANSPARENT);
                        }
                    }
                }
            renderPass++;
        }
        batcher.end();
    }
}
