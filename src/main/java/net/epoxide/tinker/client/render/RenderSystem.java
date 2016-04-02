package net.epoxide.tinker.client.render;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.cameras.OrthoCam;

import net.epoxide.tinker.world.TileMap;

public class RenderSystem {
    
    /**
     * The height of the current display window.
     */
    public static int displayHeight;
    
    /**
     * The width of the current display window.
     */
    public static int displayWidth;
    
    /**
     * The amount of tiles to show along the Y axis.
     */
    public static float renderHeight;
    
    /**
     * The amount of tiles to show along the X axis.
     */
    public static float renderWidth;
    
    /**
     * The length of a tile's size to be rendered on the screen.
     */
    public static float tileSize;
    
    /**
     * The Orthographic camera used by the main renderer.
     */
    private OrthoCam camera;
    
    /**
     * The renderer for entities.
     */
    private final RenderEntitySystem entityRenderer;
    
    private final RenderGuiSystem guiRenderer;
    
    /**
     * The renderer for the world map.
     */
    private final RenderWorldSystem worldRenderer;
    
    /**
     * Constructs the core render system and initializes some of the main renderers.
     */
    public RenderSystem() {
        
        this.worldRenderer = new RenderWorldSystem();
        this.entityRenderer = new RenderEntitySystem();
        this.guiRenderer = new RenderGuiSystem();
        this.resize();
    }
    
    /**
     * Handles the render updating for the core renderer.
     * 
     * @param delta Change in time since the last render.
     * @param batcher An instance of the game batcher.
     * @param tileMap The map which the entity is on.
     */
    public void render (float delta, Batcher batcher, TileMap tileMap) {
        
        this.camera.apply();
        this.worldRenderer.renderWorld(delta, batcher, tileMap);
        this.entityRenderer.renderEntities(delta, batcher, tileMap);
        this.guiRenderer.renderGui(delta, batcher);
    }
    
    /**
     * Handles resizing of the window to ensure that the correct aspect ratios are maintained.
     */
    public void resize () {
        
        this.camera = new OrthoCam();
        
        displayWidth = Display.getWidth();
        displayHeight = Display.getHeight();
        
        tileSize = Math.max(displayWidth / 16, displayHeight / 16);
        
        renderWidth = displayWidth / tileSize;
        renderHeight = displayHeight / tileSize;
    }
}
