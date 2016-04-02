package net.epoxide.tinker.client.gui;

import com.shc.silenceengine.graphics.Batcher;

import net.epoxide.tinker.client.gui.screens.GuiContainer;

public class Gui {
    
    /**
     * The main draw hook for a gui element. The default behavior is to pass the call to the
     * background and foreground draw calls.
     * 
     * @param batcher The current Batcher instance.
     * @param mouseX The X coordinate for the mouse.
     * @param mouseY The Y coordinate for the mouse.
     */
    public void draw (Batcher batcher, float mouseX, float mouseY) {
        
        this.drawBackground(batcher, mouseX, mouseY);
        this.drawForeground(batcher, mouseX, mouseY);
    }
    
    /**
     * A draw hook for rendering background gui elements.
     * 
     * @param batcher The current Batcher instance.
     * @param mouseX The X coordinate for the mouse.
     * @param mouseY The Y coordinate for the mouse.
     */
    public void drawBackground (Batcher batcher, float mouseX, float mouseY) {
    
    }
    
    /**
     * A draw hook for rendering foreground gui elements.
     * 
     * @param batcher The current Batcher instance.
     * @param mouseX The X coordinate for the mouse.
     * @param mouseY The Y coordinate for the mouse.
     */
    public void drawForeground (Batcher batcher, float mouseX, float mouseY) {
    
    }
    
    /**
     * A hook for when the mouse moves.
     * 
     * @param dx The X coordinate of the mouse.
     * @param dy The Y coordinate of the mouse.
     */
    public void mouseMove (int dx, int dy) {
    
    }
    
    /**
     * A hook for when the mouse is pressed.
     * 
     * @param mouseX The X coordinate of the mouse.
     * @param mouseY The Y coordiante of the mouse.
     */
    public void mousePressed (float mouseX, float mouseY) {
    
    }
    
    /**
     * A hook for when the gui is added to a GuiContainer. Allows for certain things to be
     * initialized.
     * 
     * @param container The GuiContainer that the gui was added to.
     */
    public void onAdded (GuiContainer container) {
    
    }
    
    /**
     * A hook for when a key is typed. This is used for handling text input.
     * 
     * @param chars The characters being typed.
     * @param codePoint The unicode position. You can read more here:
     *        https://en.wikipedia.org/wiki/Code_point
     * @param mods The modifiers applied to the key.
     */
    public void onKeyTyped (char[] chars, int codePoint, int mods) {
    
    }
    
    /**
     * A hook for when the gui is removed from a GuiContainer. Allows for certain things to
     * deinitialized.
     * 
     * @param container The GuiContainer that the gui was removed from.
     */
    public void onRemoved (GuiContainer container) {
    
    }
    
    /**
     * An update hook that is ran every game tick.
     */
    public void update () {
    
    }
}
