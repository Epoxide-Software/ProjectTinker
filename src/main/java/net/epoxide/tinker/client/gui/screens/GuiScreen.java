package net.epoxide.tinker.client.gui.screens;

import java.util.ArrayList;
import java.util.List;

import com.shc.silenceengine.graphics.Batcher;

import net.epoxide.tinker.client.gui.Gui;

public abstract class GuiScreen extends Gui {
    
    public List<Gui> guiElements = new ArrayList<>();
    
    @Override
    public void draw (Batcher batcher, float mouseX, float mouseY) {
        
        this.drawBackground(batcher);
        this.drawForeground(batcher, mouseX, mouseY);
    }
    
    public void drawBackground (Batcher batcher) {
    
    }
    
    public void drawForeground (Batcher batcher, float mouseX, float mouseY) {
        
        this.guiElements.forEach(element -> element.draw(batcher, mouseX, mouseY));
    }
    
    public void init () {
    
    }
    
    public boolean isCompatible (String guiScreen) {
        
        return false;
    }
    
    public void onClose () {
    
    }
    
    @Override
    public void update (float delta) {
        
        this.guiElements.forEach(element -> element.update(delta));
    }
}