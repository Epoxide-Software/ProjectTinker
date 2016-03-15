package net.epoxide.tinker;

import javax.swing.JOptionPane;

import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.SilenceException;
import com.shc.silenceengine.graphics.Batcher;

import net.epoxide.tinker.client.render.RenderSystem;
import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.entity.component.ComponentPosition;

public class TinkerGame extends Game {
    
    private Entity entityPlayer = new Entity("player");
    private RenderSystem renderSystem = new RenderSystem();
    /**
     * The current version of the game. Version system follows a fairly standard version system
     * of major.minor.patch.build.
     */
    public static final String version = "0.0.0.0";
    
    // TODO As we add new launch arguments, they should be documented here as a doc.
    public static void main (String[] args) {
        
        try {
            
            new TinkerGame().start();
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
    
    }
    
    @Override
    public void init () {
        
        entityPlayer.addComponent(new ComponentPosition(0, 0));
        renderSystem.entityList.add(entityPlayer);
    }
    
    @Override
    public void resize () {
    
    }
    
    @Override
    public void update (float delta) {
    
    }
    
    @Override
    public void render (float delta, Batcher batcher) {
        
        renderSystem.renderEntities(batcher);
    }
}
