package net.epoxide.tinker.entity.component;

public class ComponentPosition extends EntityComponent {
    
    /**
     * The position of the entity on the X axis.
     */
    private float xPos;
    
    /**
     * The position of the entity on the Y axis.
     */
    private float yPos;
    
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
     * @param posX The X position for the entity.
     * @param posY The Y position for the entity.
     */
    public ComponentPosition(float posX, float posY) {
        
        this.xPos = posX;
        this.yPos = posY;
    }
    
    /**
     * Gets the X position of the entity.
     * 
     * @return int The X position.
     */
    public float getXPos () {
        
        return xPos;
    }
    
    /**
     * Sets the X Position.
     * 
     * @param xPos The new X position.
     */
    public void setXPos (float xPos) {
        
        this.xPos = xPos;
    }
    
    /**
     * Gets the Y position of the entity.
     * 
     * @return int The Y position.
     */
    public float getYPos () {
        
        return yPos;
    }
    
    /**
     * Sets the Y position.
     * 
     * @param yPos The new Y position.
     */
    public void setYPos (float yPos) {
        
        this.yPos = yPos;
    }
}