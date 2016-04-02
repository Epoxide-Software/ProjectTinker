package net.epoxide.tinker.client.gui.elements;

import net.epoxide.tinker.client.gui.Gui;

public class GuiElement extends Gui {
    
    private boolean enabled;
    private float height;
    private boolean visible;
    private float width;
    private float x;
    private float y;
    
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
    
    public float getHeight () {
        
        return this.height;
    }
    
    public float getWidth () {
        
        return this.width;
    }
    
    public float getX () {
        
        return this.x;
    }
    
    public float getY () {
        
        return this.y;
    }
    
    public boolean isEnabled () {
        
        return this.enabled;
    }
    
    public boolean isMouseOver (float mouseX, float mouseY) {
        
        return this.enabled && this.visible && mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.getWidth() && mouseY < this.getY() + this.getHeight();
    }
    
    public boolean isVisible () {
        
        return this.visible;
    }
    
    public void setEnabled (boolean enabled) {
        
        this.enabled = enabled;
    }
    
    public void setHeight (float height) {
        
        this.height = height;
    }
    
    public void setVisible (boolean visible) {
        
        this.visible = visible;
    }
    
    public void setWidth (float width) {
        
        this.width = width;
    }
    
    public void setX (float x) {
        
        this.x = x;
    }
    
    public void setY (float y) {
        
        this.y = y;
    }
}
