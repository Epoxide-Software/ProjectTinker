package net.epoxide.tinker.client.input;

import com.shc.silenceengine.input.Keyboard;

import net.epoxide.tinker.util.Displayable;
import net.epoxide.tinker.util.RegistryName;
import net.epoxide.tinker.util.lang.I18n;

public class KeyBind implements Displayable {
    
    /**
     * Whether or not the key is listening to keypress updates. This needs to be true for
     * {@link #onKeyPressed(float)} and {@link #onKeyReleased(float)} to be activated.
     */
    private final boolean eventEnabled;
    
    /**
     * The numeric value for the key.
     */
    private int key;
    
    /**
     * Whether or not the key is currently pressed.
     */
    private boolean pressed;
    
    /**
     * The ID that the keybind is registered under.
     */
    public final RegistryName ID;
    
    /**
     * Constructs a new basic keybind.
     * 
     * @param id The ID that the KeyBind is registered under.
     * @param key The numeric value for the key being represented.
     */
    public KeyBind(String id, int key) {
        
        this(id, key, false);
    }
    
    /**
     * Constructs a new basic keybind with an optional event listener.
     * 
     * @param id The ID that the KeyBind is registered under.
     * @param key The numeric value for the key being represented.
     * @param eventEnabled Whether or not the KeyBind should listen to events.
     */
    public KeyBind(String id, int key, boolean eventEnabled) {
        
        this.key = key;
        this.eventEnabled = eventEnabled;
        this.ID = new RegistryName(id);
    }
    
    /**
     * The numeric key being represented. A full list of values can be seen in
     * com.shc.silenceengine.input.Keyboard.
     * 
     * @return The numeric key being represented.
     */
    public int getKey () {
        
        return this.key;
    }
    
    @Override
    public String getTranslatedName () {
        
        return I18n.translate("keybind." + this.ID.getDomain() + "." + this.ID.getName() + ".name");
    }
    
    /**
     * Whether or not the KeyBind is listening to events. This needs to be true for
     * {@link #onKeyPressed(float)} and {@link #onKeyReleased(float)} to be activated.
     * 
     * @return boolean Whether or not the key wants events.
     */
    public boolean isEventEnabled () {
        
        return this.eventEnabled;
    }
    
    /**
     * Checks whether or not the key is activated.
     * 
     * @return boolean Whether or not the key is pressed.
     */
    public boolean isPressed () {
        
        return this.pressed;
    }
    
    /**
     * A hook for activating code when the key is pressed. Requires {@link #isEventEnabled()}
     * to return true.
     * 
     * @param delta The amount of time since the last update.
     */
    public void onKeyPressed (float delta) {
    
    }
    
    /**
     * A hook for activating code when the key is released. Requires {@link #isEventEnabled()}
     * to return true.
     * 
     * @param delta The amount of time since the last update.
     */
    public void onKeyReleased (float delta) {
    
    }
    
    /**
     * Sets the key being represented by the keybind.
     * 
     * @param key The numeric ID for the key to represent.
     */
    public void setKey (int key) {
        
        this.key = key;
    }
    
    /**
     * Updates the pressed status of the key.
     */
    public void updatePressed () {
        
        this.pressed = Keyboard.isPressed(this.key);
    }
}