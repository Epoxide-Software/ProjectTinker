package net.epoxide.tinker.client.input;

import com.shc.silenceengine.input.Keyboard;

public class KeyBind {

    private int key;
    private boolean eventEnabled;
    private boolean pressed;

    public KeyBind (int key) {

        this(key, false);
    }

    public KeyBind (int key, boolean eventEnabled) {

        this.key = key;
        this.eventEnabled = eventEnabled;
    }

    public void setKey (int key) {

        this.key = key;
    }

    public int getKey () {

        return this.key;
    }

    public boolean isPressed () {

        return this.pressed;
    }

    public void updatePressed () {

        this.pressed = Keyboard.isPressed(key);
    }

    public boolean isEventEnabled () {

        return eventEnabled;
    }

    public void onKeyPressed (float delta) {

    }

    public void onKeyReleased (float delta) {

    }

}
