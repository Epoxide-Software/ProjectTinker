package net.epoxide.tinker.client.gui.elements;

import net.epoxide.tinker.client.gui.Gui;

public class GuiElement extends Gui {
    
    /**
     * The height of the element.
     */
    private float height;
    
    /**
     * Whether or not the element is visible.
     */
    private boolean visible;
    
    /**
     * The width of the element.
     */
    private float width;
    
    /**
     * The starting X position of the element.
     */
    private float x;
    
    /**
     * The starting Y position of the element.
     */
    private float y;
    
    /**
     * Constructs a new GuiElement.
     * 
     * @param x The starting X position of the element.
     * @param y The starting Y position of the element.
     * @param width The width of the element.
     * @param height The height of the element.
     */
    public GuiElement(float x, float y, float width, float height) {
        
        this(x, y, width, height, true);
    }
    
    /**
     * Constructs a new GuiElement.
     * 
     * @param x The starting X position of the element.
     * @param y The starting Y position of the element.
     * @param width The width of the element.
     * @param height The height of the element.
     * @param visible Whether or not the element is visible.
     */
    public GuiElement(float x, float y, float width, float height, boolean visible) {
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = visible;
    }
    
    /**
     * Gets the height of the element.
     * 
     * @return float The height of the element.
     */
    public float getHeight () {
        
        return this.height;
    }
    
    /**
     * Gets the width of the element.
     * 
     * @return float The width of the element.
     */
    public float getWidth () {
        
        return this.width;
    }
    
    /**
     * Gets the X position of the element.
     * 
     * @return float The X position of the element.
     */
    public float getX () {
        
        return this.x;
    }
    
    /**
     * Gets the Y position of the element.
     * 
     * @return float The Y position of the element.
     */
    public float getY () {
        
        return this.y;
    }
    
    /**
     * Checks if the mouse is currently over the element.
     * 
     * @param mouseX The mouse X position.
     * @param mouseY The mouse Y position.
     * @return boolean Whether or not the mouse is over the region defined by the element.
     */
    public boolean isMouseOver (float mouseX, float mouseY) {
        
        return this.visible && mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.getWidth() && mouseY < this.getY() + this.getHeight();
    }
    
    @Override
    public boolean isVisible () {
        
        return this.visible;
    }
    
    /**
     * Sets the height of the element.
     * 
     * @param height The new height for the element.
     */
    public void setHeight (float height) {
        
        this.height = height;
    }
    
    /**
     * Changes the visibility status of the element.
     * 
     * @param visible Whether or not the element should be visible.
     */
    public void setVisible (boolean visible) {
        
        this.visible = visible;
    }
    
    /**
     * Sets the width of the element.
     * 
     * @param width The new width for the element.
     */
    public void setWidth (float width) {
        
        this.width = width;
    }
    
    /**
     * Sets the X position of the element.
     * 
     * @param x The new X position for the element.
     */
    public void setX (float x) {
        
        this.x = x;
    }
    
    /**
     * Sets the Y position of the element.
     * 
     * @param y The new Y position for the element.
     */
    public void setY (float y) {
        
        this.y = y;
    }
}
