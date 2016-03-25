package net.epoxide.tinker.client.render;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.cameras.OrthoCam;

import net.epoxide.tinker.world.TileMap;

public class RenderSystem {
    
    private OrthoCam ortho;
    private RenderWorldSystem worldSystem;
    private RenderEntitySystem entitySystem;
    
    public static float tileSize;
    public static float tileWidth;
    public static float tileHeight;
    
    public static int displayWidth;
    public static int displayHeight;
    
    public static final int TILE_WINDOW_WIDTH = 16;
    
    public RenderSystem() {
        
        this.worldSystem = new RenderWorldSystem();
        this.entitySystem = new RenderEntitySystem();
        resize();
    }
    
    public void render (float delta, Batcher batcher, TileMap tileMap) {
        
        ortho.apply();
        worldSystem.renderWorld(delta, batcher, tileMap);
        entitySystem.renderEntities(delta, batcher, tileMap);
    }
    
    public void resize () {
        
        this.ortho = new OrthoCam();
        displayWidth = Display.getWidth();
        displayHeight = Display.getHeight();
        
        tileSize = Math.max(displayWidth / TILE_WINDOW_WIDTH, displayHeight / TILE_WINDOW_WIDTH);
        
        tileWidth = displayWidth / tileSize;
        tileHeight = displayHeight / tileSize;
    }
}
