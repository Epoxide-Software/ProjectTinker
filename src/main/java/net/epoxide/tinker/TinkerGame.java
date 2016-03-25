package net.epoxide.tinker;

import javax.swing.JOptionPane;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.core.SilenceException;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.utils.Logger;

import net.darkhax.opennbt.NBTHelper;

import net.epoxide.tinker.client.render.RenderEntitySystem;
import net.epoxide.tinker.client.render.RenderSystem;
import net.epoxide.tinker.client.render.entity.RenderEntityPlayer;
import net.epoxide.tinker.client.render.textures.TextureSystem;
import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.entity.living.EntityPlayer;
import net.epoxide.tinker.util.RegistryName;
import net.epoxide.tinker.world.TileMap;
import net.epoxide.tinker.world.dungeon.Dungeon;

public class TinkerGame extends Game {
    
    /**
     * TODO Temp
     */
    public EntityPlayer entityPlayer;
    private RenderSystem renderSystem = new RenderSystem();
    /**
     * TODO Temp
     */
    public static TileMap tileMap = new TileMap(512, 512, "world");
    
    /**
     * The current version of the game. Version system follows a fairly standard version system
     * of major.minor.patch.build.
     */
    public static final String version = "0.0.0.0";
    
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
            
            TinkerGame game = new TinkerGame();
            for (String arg : args) {
                if (arg.equalsIgnoreCase("debug"))
                    DEVELOPMENT = true;
            }
            game.start();
        }
        
        catch (Exception exception) {
            
            if (exception instanceof SilenceException && exception.getMessage().startsWith("java.nio.file.AccessDeniedException:") && exception.getMessage().contains("glfw.dll"))
                JOptionPane.showMessageDialog(null, "Another instance of this game is already running. Please close that instance and try again.", "Project Tinker", JOptionPane.WARNING_MESSAGE);
                
            else
                exception.printStackTrace();
        }
    }
    
    @Override
    public void preInit () {
        
        Logger.info("[OpenNBT] Version " + NBTHelper.VERSION + " detected.");
        Display.setVSync(false);
        
        TextureSystem.registerTileTextures("stone");
        TextureSystem.registerTileTextures("slime");
        TextureSystem.registerTileTextures("missing");
        
    }
    
    @Override
    public void init () {
        
        Dungeon.DEFAULT.generateMap(tileMap);
        entityPlayer = new EntityPlayer(tileMap);
        Entity.REGISTRY.registerValue(new RegistryName("player"), EntityPlayer.class);
        // entityPlayer.renderers.add("entityPlayer");
        tileMap.spawnEntity(entityPlayer);
        RenderEntitySystem.registerRenderer("entityPlayer", new RenderEntityPlayer());
        
        TextureSystem.init();
    }
    
    @Override
    public void resize () {
        
        renderSystem.resize();
    }
    
    @Override
    public void update (float delta) {
        
        if (Game.DEVELOPMENT)
            Display.setTitle("FPS: " + Game.getFPS() + " | UPS: " + Game.getUPS() + " | RC: " + SilenceEngine.graphics.renderCallsPerFrame);
    }
    
    @Override
    public void render (float delta, Batcher batcher) {
        
        renderSystem.render(delta, batcher, tileMap);
    }
}
