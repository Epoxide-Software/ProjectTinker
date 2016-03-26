package net.epoxide.tinker.input;

public class KeyBind {

    private int key;
    public boolean isEvent;
    public boolean pressed;

    public KeyBind (int key) {

        this(key, false);
    }

    public KeyBind (int key, boolean isEvent) {

        this.key = key;
        this.isEvent = isEvent;
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

    public void setPressed (boolean b) {

        this.pressed = b;
    }

    public void onKeyPressed (float delta) {

    }

    public void onKeyReleased (float delta) {

    }
}
