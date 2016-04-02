package net.epoxide.tinker.client.gui.screens;

import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;
import com.shc.silenceengine.graphics.Graphics2D;

public class GuiHUD extends GuiScreen {
    
    @Override
    public void drawBackground (Batcher batcher) {
    
    }
    
    @Override
    public void drawForeground (Batcher batcher, float mouseX, float mouseY) {
        
        super.drawForeground(batcher, mouseX, mouseY);
        final Graphics2D g2d = Graphics2D.getInstance();
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.format("FPS: %s", Game.getFPS()), 0, 0);
    }
    
    @Override
    public void init () {
    
    }
    
    @Override
    public void update (float delta) {
    
    }
}