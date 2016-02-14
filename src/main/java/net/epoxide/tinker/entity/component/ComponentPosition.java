package net.epoxide.tinker.entity.component;

public class ComponentPosition {
    
    /**
     * The position of the entity on the X axis.
     */
    private int xPos;
    
    /**
     * The position of the entity on the Y axis.
     */
    private int yPos;
    
    /**
     * Provides the entity with position data. This constructor sets the position to the
     * origin.
     */
    public ComponentPosition() {
        
        this(0, 0);
    }
    
    /**
     * Provides the entity with position data.
     * 
     * @param posX: The X position for the entity.
     * @param posY: The Y position for the entity.
     */
    public ComponentPosition(int posX, int posY) {
        
        this.xPos = posX;
        this.yPos = posY;
    }
    
    /**
     * Gets the X position of the entity.
     * 
     * @return int: The X position.
     */
    public int getXPos () {
        
        return xPos;
    }
    
    /**
     * Sets the X Position.
     * 
     * @param xPos: The new X position.
     */
    public void setXPos (int xPos) {
        
        this.xPos = xPos;
    }
    
    /**
     * Gets the Y position of the entity.
     * 
     * @return int: The Y position.
     */
    public int getYPos () {
        
        return yPos;
    }
    
    /**
     * Sets the Y position.
     * 
     * @param yPos: The new Y position.
     */
    public void setYPos (int yPos) {
        
        this.yPos = yPos;
    }
}