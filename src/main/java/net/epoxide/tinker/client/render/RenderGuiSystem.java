package net.epoxide.tinker.client.render;

import java.util.ArrayList;
import java.util.List;

import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.input.Mouse;

import net.epoxide.tinker.client.gui.screens.GuiScreen;
import net.epoxide.tinker.util.NamedRegistry;

public class RenderGuiSystem {
    
    private static List<String> openedGui = new ArrayList<>();
    
    public static final NamedRegistry<GuiScreen> REGISTRY = new NamedRegistry<>();
    
    public void renderGui (float delta, Batcher batcher) {
        
        openedGui.forEach(gui -> REGISTRY.getValue(gui).draw(batcher, Mouse.getX(), Mouse.getY()));
    }
    
    public static void openGui (String guiScreen) {
        
        final GuiScreen gui = REGISTRY.getValue(guiScreen);
        openedGui.stream().filter(s -> !gui.isCompatible(s)).forEach(s -> REGISTRY.getValue(s).onClose());
        openedGui.removeIf(s -> !gui.isCompatible(s));
        
        openedGui.add(guiScreen);
        gui.init();
    }
}
