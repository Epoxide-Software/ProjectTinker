package net.epoxide.tinker;

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
import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.entity.component.ComponentPosition;
import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.world.TileMap;

import javax.swing.*;

public class TinkerGame extends Game {

    /**
     * TODO Temp
     */
    public Entity entityPlayer = new Entity("player");
    private RenderSystem renderSystem = new RenderSystem();
    /**
     * TODO Temp
     */
    public static TileMap world = new TileMap(512, 512, "world");

    /**
     * The current version of the game. Version system follows a fairly standard version system
     * of major.minor.patch.build.
     */
    public static final String version = "0.0.0.0";

    /**
     * The initial call for the application. The args are handle and dealt with before the game starts
     *
     * @param args The arguments for the game, the list of support arguments are:
     * - debug: Will enable the debugging of the application, and will return more information, to help solve problems.
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
    }
    
    @Override
    public void init () {

        entityPlayer.addComponent(new ComponentPosition(15, 15));
        entityPlayer.renderers.add("entityPlayer");
        world.getEntityList().add(entityPlayer);

        for (int x = 0; x < 512; x++) {
            for (int y = 0; y < 512; y++) {
                world.setTile(Tile.VOID, x, y);
            }
        }

        RenderEntitySystem.registerRenderer("entityPlayer", new RenderEntityPlayer());
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

        renderSystem.render(delta, batcher);
    }
}
