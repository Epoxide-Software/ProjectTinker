package net.epoxide.tinker;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.core.SilenceException;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.input.Keyboard;
import com.shc.silenceengine.utils.Logger;

import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import net.darkhax.ess.ESSHelper;

import net.epoxide.tinker.client.gui.screens.GuiHUD;
import net.epoxide.tinker.client.input.KeyHandler;
import net.epoxide.tinker.client.render.GuiManager;
import net.epoxide.tinker.client.render.RenderEntitySystem;
import net.epoxide.tinker.client.render.RenderSystem;
import net.epoxide.tinker.client.render.entity.RenderEntityPlayer;
import net.epoxide.tinker.client.render.textures.TextureManager;
import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.entity.living.EntityPlayer;
import net.epoxide.tinker.util.GenericUtilities;
import net.epoxide.tinker.util.RegistryName;
import net.epoxide.tinker.world.TileMap;

public class TinkerGame extends Game {
    
    public static final String DOMAIN = "tinker";
    public static EntityPlayer entityPlayer;
    
    public static final Random RANDOM = new Random();
    /**
     * The current version of the game. Version system follows a fairly standard version system
     * of major.minor.patch.build.
     */
    public static final String version = "0.0.0.0";
    private final RenderSystem renderSystem = new RenderSystem();
    
    private final TileMap tileMap = new TileMap(512, 512, "world");
    
    @Override
    public void init () {
        
        GuiManager.REGISTRY.registerValue("hud", new GuiHUD());
        
        entityPlayer = new EntityPlayer(this.tileMap);
        Entity.REGISTRY.registerValue(new RegistryName("entityPlayer"), EntityPlayer.class);
        
        this.tileMap.spawnEntity(entityPlayer);
        RenderEntitySystem.REGISTRY.registerValue("entityPlayer", new RenderEntityPlayer());
        
        TextureManager.init();
        
        GuiManager.openGui("hud");
    }
    
    @Override
    public void preInit () {
        
        Logger.info("[ESS] Version " + ESSHelper.VERSION + " detected.");
        Display.setVSync(false);
        
        TextureManager.registerTileTextures("stone");
        TextureManager.registerTileTextures("slime");
        TextureManager.registerTileTextures("missing");
        Keyboard.registerTextListener(TinkerGame::onKeyTyped);
    }
    
    @Override
    public void render (float delta, Batcher batcher) {
        
        this.renderSystem.render(delta, batcher, this.tileMap);
    }
    
    @Override
    public void resize () {
        
        this.renderSystem.resize();
    }
    
    @Override
    public void update (float delta) {
        
        if (Game.DEVELOPMENT)
            Display.setTitle("FPS: " + Game.getFPS() + " | UPS: " + Game.getUPS() + " | RC: " + SilenceEngine.graphics.renderCallsPerFrame);
            
        KeyHandler.update(delta);
    }
    
    /**
     * The initial call for the application. Handles program arguments after the game is
     * constructed but before the game is started.
     *
     * The following is a list of valid program arguments and what they do. <li>debug - Allows
     * GLExceptions to display in the log. Disables the Logger.
     *
     * @param args An array of the different program arguments being used.
     */
    public static void main (String[] args) {
        
        try {
            DEVELOPMENT = false;
            
            final TinkerGame game = new TinkerGame();
            
            for (final String arg : args)
                if (arg.equalsIgnoreCase("debug"))
                    DEVELOPMENT = true;
                    
            if (!DEVELOPMENT) {
                
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                if (!GenericUtilities.displayHTMLOptionPane("Hardware Survey", "Would you like to participate in the automatic hardware survey? If you select yes, the demo will submit anonymous information about your hardware, and how your well your computer handles the demo. No personal information is collected and submissions are completely anonymous. For more information read " + GenericUtilities.createHyperlink("http://www.google.com", "here"), JOptionPane.YES_NO_OPTION, 500, 80, 2))
                    return;
            }
            
            game.start();
        }
        
        catch (final Exception exception) {
            
            if (exception instanceof SilenceException && exception.getMessage().startsWith("java.nio.file.AccessDeniedException:") && exception.getMessage().contains("glfw.dll"))
                JOptionPane.showMessageDialog(null, "Another instance of this game is already running. Please close that instance and try again.", "Project Tinker", JOptionPane.WARNING_MESSAGE);
                
            else
                exception.printStackTrace();
        }
    }
    
    /**
     * Handles key typing for the game. This hook only handles text input, and is meant for
     * handling typing events. See com.shc.silenceengine.input.Keyboard.ITextListener for more
     * info.
     * 
     * @param chars The characters being typed.
     * @param codePoint The unicode position. You can read more here:
     *        https://en.wikipedia.org/wiki/Code_point
     * @param mods The modifiers applied to the key.
     */
    public static void onKeyTyped (char[] chars, int codePoint, int mods) {
        
        GuiManager.handleInput(chars, codePoint, mods);
    }
}
