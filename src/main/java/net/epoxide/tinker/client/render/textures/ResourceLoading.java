package net.epoxide.tinker.client.render.textures;

import java.util.HashMap;

import com.shc.silenceengine.backend.lwjgl3.opengl.Texture;
import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.core.ResourceLoader;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.Color;
import com.shc.silenceengine.graphics.Graphics2D;
import com.shc.silenceengine.graphics.Paint;
import com.shc.silenceengine.graphics.TrueTypeFont;
import com.shc.silenceengine.io.FilePath;
import com.shc.silenceengine.utils.MathUtils;

import net.epoxide.tinker.util.NamedRegistry;

public class ResourceLoading {
    
    /**
     * A map that holds all of the newly loaded textures.
     */
    private static HashMap<FilePath, AtlasTexture> textureMap;
    
    /**
     * The texture for the background being displayed during the loading process.
     */
    public static Texture background;
    
    // TODO This is kind of lame. I want to remove
    public static boolean hasSwitched;
    
    /**
     * The texture for the logo being displayed during the loading process.
     */
    public static Texture logo;
    
    /**
     * The color of the progression bar during startup.
     */
    public static Paint progressionColor;
    
    /**
     * The color of the progression bar text on startup.
     */
    public static Paint textColor;
    
    /**
     * The main resource loader.
     */
    public ResourceLoader loader;
    
    /**
     * Loads all of the registered textures.
     * 
     * @param REGISTRY A registry of TileTexture objects.
     */
    public void load (NamedRegistry<AtlasTexture> REGISTRY) {
        
        textureMap = new HashMap<>();
        this.loader = new ResourceLoader();
        this.loader.setLogo(FilePath.getResourceFile("assets/tinker/textures/logo_epoxide.png"));
        this.loader.setProgressRenderCallback(ResourceLoading::renderResourceLoaderCallback);
        ResourceLoader.setHelper(AtlasTexture.class, ResourceLoading::loadTileTexture);
        
        for (final AtlasTexture tileTexture : REGISTRY) {
            final FilePath filePath = tileTexture.getPath();
            if (filePath.exists()) {
                this.loader.loadResource(AtlasTexture.class, filePath);
                textureMap.put(filePath, tileTexture);
            }
            else
                System.out.println(filePath + " NOT FOUND");
                
        }
        this.loader.startLoading();
    }
    
    /**
     * Loads a file into the resource loader.
     * 
     * @param filePath The path to the file to load.
     * @param resourceLoader The ResourceLoader to load the resource.
     */
    private static void loadTileTexture (FilePath filePath, ResourceLoader resourceLoader) {
        
        final AtlasTexture tileTexture = textureMap.get(filePath);
        
        if (tileTexture != null) {
            
            tileTexture.loadFile();
            resourceLoader.putResource("tileTexture", filePath, tileTexture);
        }
    }
    
    /**
     * A callback from the SilenceEngine which allows for special loader effects to be
     * displayed.
     * 
     * @param info The current render info.
     * @param percentage The percent completion of the resource loading.
     */
    private static void renderResourceLoaderCallback (String info, float percentage) {
        
        if (logo == null)
            logo = Texture.fromFilePath(FilePath.getResourceFile("resources/logo.png"));
            
        if (progressionColor == null) {
            
            progressionColor = new Paint(Color.RED, Color.DARK_RED, Paint.Gradient.DIAGONAL_LEFT_TO_RIGHT);
            textColor = new Paint(Color.RED);
            background = Texture.fromFilePath(FilePath.getResourceFile("assets/tinker/textures/backdrop.png"));
        }
        
        if (percentage > 55f && !hasSwitched) {
            
            progressionColor = new Paint(Color.GREEN, Color.GREEN_YELLOW, Paint.Gradient.DIAGONAL_LEFT_TO_RIGHT);
            textColor = new Paint(Color.DARK_GREEN);
            logo = Texture.fromFilePath(FilePath.getResourceFile("assets/tinker/textures/logo_epoxide.png"));
            hasSwitched = true;
        }
        
        final float adjustedPercentage = MathUtils.convertRange(percentage, 0, 100, 0, Display.getWidth() - 100);
        
        final Graphics2D g2d = SilenceEngine.graphics.getGraphics2D();
        g2d.setFont(TrueTypeFont.DEFAULT);
        
        g2d.drawTexture(background, 0, 0, Display.getWidth(), Display.getHeight());
        
        float logoX = Display.getWidth() / 2 - logo.getWidth() / 2;
        float logoY = Display.getHeight() / 2 - logo.getHeight() / 2;
        float logoW = logo.getWidth();
        float logoH = logo.getHeight();
        
        if (logoW > Display.getWidth()) {
            
            logoX = 0;
            logoW = Display.getWidth();
            
        }
        
        if (logoH > Display.getHeight()) {
            
            logoY = 0;
            logoH = Display.getHeight();
            
        }
        
        g2d.drawTexture(logo, logoX, logoY, logoW, logoH);
        
        final Paint originalPaint = g2d.getPaint();
        g2d.setPaint(progressionColor);
        
        g2d.fillRect(50, Display.getHeight() - 75, adjustedPercentage, 25);
        g2d.setPaint(textColor);
        g2d.drawLine(50, Display.getHeight() - 50, Display.getWidth() - 50, Display.getHeight() - 50);
        g2d.drawString(info, 50, Display.getHeight() - 80 - g2d.getFont().getHeight());
        
        g2d.setPaint(originalPaint);
    }
}
