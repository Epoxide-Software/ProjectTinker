package net.epoxide.tinker.client.gui.screens;

import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;
import com.shc.silenceengine.graphics.Graphics2D;

import net.epoxide.tinker.client.gui.Gui;

public class GuiHUD extends GuiContainer {
    
    public class GuiInfo extends Gui {
        
        @Override
        public void drawForeground (Batcher batcher, float mouseX, float mouseY) {
            
            super.drawForeground(batcher, mouseX, mouseY);
            final Graphics2D g2d = Graphics2D.getInstance();
            g2d.setColor(Color.WHITE);
            g2d.drawString(String.format("FPS: %s", Game.getFPS()), 0, 0);
            g2d.drawString(String.format("X:%s Y:%s", mouseX, mouseY), 0, 15);
        }
    }
    
    public GuiHUD() {
        
        this.addElement(new GuiInfo());
    }
}