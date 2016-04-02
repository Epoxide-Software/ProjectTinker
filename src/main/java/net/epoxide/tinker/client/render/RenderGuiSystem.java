package net.epoxide.tinker.client.render;

import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.input.Mouse;

import net.epoxide.tinker.client.gui.screens.GuiContainer;
import net.epoxide.tinker.util.NamedRegistry;

public class RenderGuiSystem {
    
    /**
     * The current GuiContainer that has been loaded.
     */
    private static GuiContainer currentContainer;
    
    public static final NamedRegistry<GuiContainer> REGISTRY = new NamedRegistry<>();
    
    /**
     * The render call for the gui hook.
     * 
     * @param batcher The current batcher instance.
     */
    public void renderGui (Batcher batcher) {
        
        if (currentContainer != null)
            currentContainer.draw(batcher, Mouse.getX(), Mouse.getY());
    }
    
    /**
     * Handles typing input for guis.
     * 
     * @param chars The characters being typed.
     * @param codePoint The unicode position. You can read more here:
     *        https://en.wikipedia.org/wiki/Code_point
     * @param mods The modifiers applied to the key.
     */
    public static void handleInput (char[] chars, int codePoint, int mods) {
        
        if (currentContainer != null)
            currentContainer.onKeyTyped(chars, codePoint, mods);
    }
    
    /**
     * Opens up a new GuiContainer. This will call {@link GuiContainer#onClosed()} on the
     * existing GuiContainer, and then call {@link GuiContainer#onOpened()} on the new gui. It
     * will also set the {@link #currentContainer} to the new container.
     * 
     * @param guiID The ID of the gui to open.
     */
    public static void openGui (String guiID) {
        
        final GuiContainer gui = REGISTRY.getValue(guiID);
        
        if (gui != null) {
            
            if (currentContainer != null)
                currentContainer.onClosed();
                
            gui.onOpened();
            currentContainer = gui;
        }
    }
}
