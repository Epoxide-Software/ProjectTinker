package net.epoxide.tinker.client.input;

import com.shc.silenceengine.input.Keyboard;

import net.epoxide.tinker.client.input.keys.MovementKey;
import net.epoxide.tinker.util.NamedRegistry;

public class KeyHandler {
    
    public static final NamedRegistry<KeyBind> REGISTRY = new NamedRegistry<KeyBind>();
    
    public static final KeyBind back = registerKeyBind(new MovementKey("back", Keyboard.KEY_S, 0));
    public static final KeyBind forward = registerKeyBind(new MovementKey("forward", Keyboard.KEY_W, 1));
    public static final KeyBind left = registerKeyBind(new MovementKey("left", Keyboard.KEY_A, 2));
    public static final KeyBind right = registerKeyBind(new MovementKey("right", Keyboard.KEY_D, 3));
    
    public static final KeyBind debug = registerKeyBind(new KeyBind("debug", Keyboard.KEY_F3));
    
    /**
     * Gets a KeyBind from the {@link #REGISTRY} which is associated with the name. If no key
     * exists, null will be returned.
     *
     * @param name The name of the KeyBind you are looking for.
     * @return KeyBind The KeyBind associated with the specified name.
     */
    public static KeyBind getKeyBindByName (String name) {
        
        return REGISTRY.getValue(name);
    }
    
    /**
     * Registers a KeyBind with the {@link #REGISTRY} using the ID stored in the KeyBind. This
     * should be used over directly accessing the REGISTRY.
     *
     * @param key The KeyBind to register.
     * @return KeyBind The same KeyBind being registered. Provided to make life easier.
     */
    public static KeyBind registerKeyBind (KeyBind key) {
        
        return REGISTRY.registerValue(key.ID, key);
    }
    
    /**
     * Updates the key press status of all keys and activates the
     * {@link KeyBind#onKeyPressed(float)} and {@link KeyBind#onKeyReleased(float)} hooks.
     * 
     * @param delta Time since the last update.
     */
    public static void update (float delta) {
        
        REGISTRY.forEach(keyBind -> {
            
            final boolean wasPressed = keyBind.isPressed();
            
            keyBind.updatePressed();
            
            if (keyBind.isEventEnabled()) {
                
                if ((!wasPressed || keyBind.isRepeatable()) && Keyboard.isPressed(keyBind.getKey()))
                    keyBind.onKeyPressed(delta);
                    
                if (wasPressed && !Keyboard.isPressed(keyBind.getKey()))
                    keyBind.onKeyReleased(delta);
            }
        });
    }
}
