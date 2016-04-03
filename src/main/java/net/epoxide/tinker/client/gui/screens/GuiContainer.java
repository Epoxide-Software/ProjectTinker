package net.epoxide.tinker.client.gui.screens;

import java.util.ArrayList;
import java.util.List;

import com.shc.silenceengine.graphics.Batcher;

import net.epoxide.tinker.client.gui.Gui;

public abstract class GuiContainer extends Gui {
    
    /**
     * A list of all the loaded elements.
     */
    private final List<Gui> guiElements = new ArrayList<>();
    
    /**
     * Adds an element to the container. Will also call the {@link Gui#onAdded(GuiContainer)}
     * hook.
     * 
     * @param element The Gui element to add to the container.
     */
    public void addElement (Gui element) {
        
        element.onAdded(this);
        this.guiElements.add(element);
    }
    
    @Override
    public void draw (Batcher batcher, float mouseX, float mouseY) {
        
        this.guiElements.stream().filter(element -> element.isVisible()).forEach(element -> element.draw(batcher, mouseX, mouseY));
    }
    
    /**
     * A hook for when the container is closed.
     */
    public void onClosed () {
    
    }
    
    @Override
    public void onKeyTyped (char[] chars, int codePoint, int mods) {
        
        this.guiElements.forEach(element -> element.onKeyTyped(chars, codePoint, mods));
    }
    
    /**
     * A hook for when the container is opened.
     */
    public void onOpened () {
    
    }
    
    /**
     * Removes an element from the container. Will also call the
     * {@link Gui#onRemoved(GuiContainer)} hook.
     * 
     * @param element The Gui element to remove from the container.
     */
    public void removeElement (Gui element) {
        
        element.onRemoved(this);
        this.guiElements.remove(element);
    }
    
    @Override
    public void update () {
        
        this.guiElements.forEach(element -> element.update());
    }
}