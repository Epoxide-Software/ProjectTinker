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

/**
 * @author lclc98
 */
public class ResourceLoading {
    
    public ResourceLoader loader;
    
    public static Paint progressionColor;
    public static Paint textColor;
    public static Texture logo;
    public static Texture background;
    public static boolean hasSwitched;
    
    private static HashMap<FilePath, TileTexture> textureMap;
    
    public void load (NamedRegistry<TileTexture> REGISTRY) {
        
        textureMap = new HashMap<>();
        loader = new ResourceLoader();
        loader.setLogo(FilePath.getResourceFile("assets/tinker/textures/logo_epoxide.png"));
        loader.setProgressRenderCallback(ResourceLoading::renderResourceLoaderCallback);
        ResourceLoader.setHelper(TileTexture.class, ResourceLoading::loadTileTexture);
        
        TileTexture[] x = REGISTRY.getValues();
        for (TileTexture tileTexture : x) {
            if (tileTexture.path.exists()) {
                loader.loadResource(TileTexture.class, tileTexture.path);
                textureMap.put(tileTexture.path, tileTexture);
            }
            else {
                System.out.println(tileTexture.path + " NOT FOUND");
            }
            
        }
        loader.startLoading();
    }
    
    private static void loadTileTexture (FilePath filePath, ResourceLoader resourceLoader) {
        
        TileTexture tileTexture = textureMap.get(filePath);
        if (tileTexture != null) {
            tileTexture.loadFile();
            resourceLoader.putResource("tileTexture", filePath, tileTexture);
        }
    }
    
    private static void renderResourceLoaderCallback (String info, float percentage) {
        
        if (logo == null)
            logo = Texture.fromFilePath(FilePath.getResourceFile("resources/logo.png"));
            
        if (progressionColor == null) {
            
            progressionColor = new Paint(Color.RED, Color.DARK_RED, Paint.Gradient.DIAGONAL_LEFT_TO_RIGHT);
            textColor = new Paint(Color.BLUE);
            background = Texture.fromFilePath(FilePath.getResourceFile("assets/tinker/textures/backdrop.png"));
        }
        
        if (percentage > 55f && !hasSwitched) {
            
            progressionColor = new Paint(Color.GREEN, Color.GREEN_YELLOW, Paint.Gradient.DIAGONAL_LEFT_TO_RIGHT);
            textColor = new Paint(Color.DARK_GREEN);
            logo = Texture.fromFilePath(FilePath.getResourceFile("assets/tinker/textures/logo_epoxide.png"));
            hasSwitched = true;
            
        }
        
        float adjustedPercentage = MathUtils.convertRange(percentage, 0, 100, 0, Display.getWidth() - 100);
        
        Graphics2D g2d = SilenceEngine.graphics.getGraphics2D();
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
        
        Paint originalPaint = g2d.getPaint();
        g2d.setPaint(progressionColor);
        
        g2d.fillRect(50, Display.getHeight() - 75, adjustedPercentage, 25);
        g2d.setPaint(textColor);
        g2d.drawLine(50, Display.getHeight() - 50, Display.getWidth() - 50, Display.getHeight() - 50);
        g2d.drawString(info, 50, Display.getHeight() - 80 - g2d.getFont().getHeight());
        
        g2d.setPaint(originalPaint);
    }
}
