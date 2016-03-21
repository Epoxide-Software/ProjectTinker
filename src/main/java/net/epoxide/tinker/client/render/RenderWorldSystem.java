package net.epoxide.tinker.client.render;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;
import net.epoxide.tinker.TinkerGame;
import net.epoxide.tinker.entity.living.EntityPlayer;
import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.world.TileMap;

public class RenderWorldSystem {
    
    public void renderWorld (float delta, Batcher batcher, TileMap tileMap) {

        EntityPlayer entityPlayer = ((TinkerGame) TinkerGame.getInstance()).entityPlayer;
        int dW = Display.getWidth();
        int dH = Display.getHeight();

        float x = entityPlayer.getXPos();
        float y = entityPlayer.getYPos();
        
        float tWidthAmount = dW / RenderSystem.tileSize;
        float tHeightAmount = dH / RenderSystem.tileSize;
        
        batcher.begin();
        for (int xx = 0; xx < tWidthAmount + 1; xx++) {
            for (int yy = 0; yy < tHeightAmount + 1; yy++) {
                
                float xRender = (float) ((xx + (x - Math.floor(x))) * RenderSystem.tileSize) - RenderSystem.tileSize / 2;
                float yRender = (float) ((yy + (y - Math.floor(y))) * RenderSystem.tileSize) - RenderSystem.tileSize / 2;
                int tempX = (int) ((int) (xx - tWidthAmount / 2) + x);
                int tempY = (int) ((int) (yy - tHeightAmount / 2) + y);
                Tile tile = tileMap.getTile(tempX, tempY);
                
                if (tile != null) {
                    batcher.color(Color.GRAY);
                    batcher.color(Color.GRAY);
                    batcher.color(Color.GRAY);
                    
                    batcher.color(Color.GRAY);
                    batcher.color(Color.GRAY);
                    batcher.color(Color.GRAY);
                    
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
