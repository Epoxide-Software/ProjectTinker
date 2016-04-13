package net.epoxide.tinker.client.gui.screens;

import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;
import com.shc.silenceengine.graphics.Graphics2D;

import net.epoxide.tinker.TinkerGame;
import net.epoxide.tinker.client.gui.Gui;
import net.epoxide.tinker.client.input.KeyHandler;

public class GuiHUD extends GuiContainer {
    
    public class GuiInfo extends Gui {
        
        public char[] characters = new char[] { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd', '!' };
        
        public GuiInfo() {
            
            if (Math.random() < 0.25)
                this.characters = new char[] { 'T', 'e', 'd', ' ', 'C', 'r', 'u', 'z', ' ', 'i', 's', ' ', 't', 'h', 'e', ' ', 'Z', 'o', 'd', 'i', 'a', 'c', ' ', 'K', 'i', 'l', 'l', 'e', 'r' };
        }
        
        @Override
        public void drawForeground (Batcher batcher, float mouseX, float mouseY) {
            
            if (KeyHandler.debug.isPressed()) {
                
                final Graphics2D g2d = Graphics2D.getInstance();
                g2d.setColor(Color.WHITE);
                g2d.drawString(String.format("FPS: %s\nX:%s Y:%s\nKeys: %s\nDirection: %s", Game.getFPS(), mouseX, mouseY, new String(this.characters), TinkerGame.entityPlayer.getRotation().name()), 0, 0);
            }
        }
        
        @Override
        public void onKeyTyped (char[] chars, int codePoint, int mods) {
            
            this.characters = chars;
        }
    }
    
    public GuiHUD() {
        
        this.addElement(new GuiInfo());
    }
}