package net.epoxide.tinker.client.gui.elements;

import net.epoxide.tinker.client.gui.Gui;

public class GuiElement extends Gui {


    private float x;
    private float y;
    private float width;
    private float height;
    private boolean enabled;
    private boolean visible;

    public GuiElement(float x, float y, float width, float height) {
        this(x, y, width, height, true, true);
    }

    public GuiElement(float x, float y, float width, float height, boolean enabled, boolean visible) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.enabled = enabled;
        this.visible = visible;
    }

    public boolean isMouseOver(float mouseX, float mouseY) {

        return this.enabled && this.visible && mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.getWidth() && mouseY < this.getY() + this.getHeight();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
