package net.epoxide.tinker.client.render;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.cameras.OrthoCam;

import net.epoxide.tinker.world.TileMap;

public class RenderSystem {
    
    /**
     * The Orthographic camera used by the main renderer.
     */
    private OrthoCam camera;
    
    /**
     * The renderer for the world map.
     */
    private RenderWorldSystem worldRenderer;
    
    /**
     * The renderer for entities. 
     */
    private RenderEntitySystem entityRenderer;
    
    public static float tileSize;
    public static float tileWidth;
    public static float tileHeight;
    
    /**
     * The width of the current display window.
     */
    public static int displayWidth;
    
    /**
     * The height of the current display window.
     */
    public static int displayHeight;

    public RenderSystem() {
        
        this.worldRenderer = new RenderWorldSystem();
        this.entityRenderer = new RenderEntitySystem();
        resize();
    }
    
    public void render (float delta, Batcher batcher, TileMap tileMap) {
        
        camera.apply();
        worldRenderer.renderWorld(delta, batcher, tileMap);
        entityRenderer.renderEntities(delta, batcher, tileMap);
    }
    
    public void resize () {
        
        this.camera = new OrthoCam();

        displayWidth = Display.getWidth();
        displayHeight = Display.getHeight();
        
        tileSize = Math.max(displayWidth / 16, displayHeight / 16);
        
        tileWidth = displayWidth / tileSize;
        tileHeight = displayHeight / tileSize;
    }
}
