package net.epoxide.tinker.client.input;

import com.shc.silenceengine.input.Keyboard;

public class KeyBind {
    
    private final boolean eventEnabled;
    private int key;
    private boolean pressed;
    
    public KeyBind(int key) {
        
        this(key, false);
    }
    
    public KeyBind(int key, boolean eventEnabled) {
        
        this.key = key;
        this.eventEnabled = eventEnabled;
    }
    
    public int getKey () {
        
        return this.key;
    }
    
    public boolean isEventEnabled () {
        
        return this.eventEnabled;
    }
    
    public boolean isPressed () {
        
        return this.pressed;
    }
    
    public void onKeyPressed (float delta) {
    
    }
    
    public void onKeyReleased (float delta) {
    
    }
    
    public void setKey (int key) {
        
        this.key = key;
    }
    
    public void updatePressed () {
        
        this.pressed = Keyboard.isPressed(this.key);
    }
    
}
