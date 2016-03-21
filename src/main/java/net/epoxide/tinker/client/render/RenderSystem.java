package net.epoxide.tinker.client.render;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.cameras.OrthoCam;
import net.epoxide.tinker.TinkerGame;
import net.epoxide.tinker.world.TileMap;

public class RenderSystem {
    
    private OrthoCam ortho;
    private RenderWorldSystem worldSystem;
    private RenderEntitySystem entitySystem;
    
    public static final int TILE_WINDOW_WIDTH = 16;
    public static float tileSize;
    
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
        
        float wA = Display.getWidth() / TILE_WINDOW_WIDTH;
        float hA = Display.getHeight() / TILE_WINDOW_WIDTH;
        tileSize = Math.max(wA, hA);
    }
}
