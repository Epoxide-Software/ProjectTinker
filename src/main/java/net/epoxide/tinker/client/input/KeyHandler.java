package net.epoxide.tinker.client.input;

import java.util.ArrayList;
import java.util.List;

import com.shc.silenceengine.input.Keyboard;

import net.epoxide.tinker.entity.living.EntityPlayer;

public class KeyHandler {
    public static final List<KeyBind> REGISTRY = new ArrayList<>();
    
    public static final KeyBind forward = new KeyBind(Keyboard.KEY_W);
    public static final KeyBind back = new KeyBind(Keyboard.KEY_S);
    public static final KeyBind left = new KeyBind(Keyboard.KEY_A);
    public static final KeyBind right = new KeyBind(Keyboard.KEY_D);
    
    static {
        REGISTRY.add(forward);
        REGISTRY.add(back);
        REGISTRY.add(left);
        REGISTRY.add(right);
    }
    
    public static void update (EntityPlayer entityPlayer, float delta) {
        
        if (forward.isPressed())
            entityPlayer.setYPos(entityPlayer.getYPos() + delta);
        if (back.isPressed())
            entityPlayer.setYPos(entityPlayer.getYPos() - delta);
        if (left.isPressed())
            entityPlayer.setXPos(entityPlayer.getXPos() + delta);
        if (right.isPressed())
            entityPlayer.setXPos(entityPlayer.getXPos() - delta);
            
        REGISTRY.forEach(keyBind -> {
            final boolean wasPressed = keyBind.isPressed();
            
            keyBind.updatePressed();
            if (keyBind.isEventEnabled()) {
                if (!wasPressed && Keyboard.isPressed(keyBind.getKey()))
                    keyBind.onKeyPressed(delta);
                if (wasPressed && !Keyboard.isPressed(keyBind.getKey()))
                    keyBind.onKeyReleased(delta);
            }
        });
    }
}
