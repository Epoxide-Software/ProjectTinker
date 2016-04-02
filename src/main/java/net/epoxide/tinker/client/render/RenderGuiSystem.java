package net.epoxide.tinker.client.render;

import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.input.Mouse;
import net.epoxide.tinker.client.gui.screens.GuiScreen;
import net.epoxide.tinker.util.NamedRegistry;

import java.util.ArrayList;
import java.util.List;

public class RenderGuiSystem {

    public static final NamedRegistry<GuiScreen> REGISTRY = new NamedRegistry<>();

    private static List<String> openedGui = new ArrayList<>();

    public static void openGui (String guiScreen) {

        GuiScreen gui = REGISTRY.getValue(guiScreen);
        openedGui.stream().filter(s->!gui.isCompatible(s)).forEach(s->REGISTRY.getValue(s).onClose());
        openedGui.removeIf(s->!gui.isCompatible(s));

        openedGui.add(guiScreen);
        gui.init();
    }

    public void renderGui (float delta, Batcher batcher) {

        openedGui.forEach(gui->REGISTRY.getValue(gui).draw(batcher, Mouse.getX(), Mouse.getY()));
    }
}
