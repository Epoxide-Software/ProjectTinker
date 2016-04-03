package net.epoxide.tinker.client.gui.elements;

import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Graphics2D;
import com.shc.silenceengine.graphics.IFont;

public class GuiButton extends GuiElement {
    
    /**
     * The font to use for the button.
     */
    private IFont font;
    
    /**
     * A numeric identifier for the button. This is specific to the GuiContainer.
     */
    private final int id;
    
    /**
     * The text to display on the button.
     */
    private String text;
    
    /**
     * Constructs a new button element.
     * 
     * @param id The ID for the button to use.
     * @param x The X position for the button.
     * @param y The Y position for the button.
     * @param width The length of the button away from the starting X position.
     * @param height The length of the button away from the starting Y position.
     */
    public GuiButton(int id, float x, float y, float width, float height) {
        
        this(id, x, y, width, height, null);
    }
    
    /**
     * Constructs a new button element.
     * 
     * @param id The ID for the button to use.
     * @param x The X position for the button.
     * @param y The Y position for the button.
     * @param width The length of the button away from the starting X position.
     * @param height The length of the button away from the starting Y position.
     * @param text The text to display on the button.
     */
    public GuiButton(int id, float x, float y, float width, float height, String text) {
        
        this(id, x, y, width, height, text, SilenceEngine.graphics.getGraphics2D().getFont());
    }
    
    /**
     * Constructs a new button element.
     * 
     * @param id The ID for the button to use.
     * @param x The X position for the button.
     * @param y The Y position for the button.
     * @param width The length of the button away from the starting X position.
     * @param height The length of the button away from the starting Y position.
     * @param text The text to display on the button.
     * @param font The font to use for the button.
     */
    public GuiButton(int id, float x, float y, float width, float height, String text, IFont font) {
        
        super(x, y, width, height);
        this.id = id;
        this.text = text;
        this.font = font;
    }
    
    @Override
    public void draw (Batcher batcher, float mouseX, float mouseY) {
        
        final Graphics2D g2d = Graphics2D.getInstance();
        final float centerX = this.getX() + this.getWidth() / 2;
        final float centerY = this.getY() + this.getHeight() / 2;
        
        if (this.text != null) {
            
            final float correctX = centerX - this.font.getWidth(this.text) / 2;
            final float correctY = centerY - this.font.getHeight() / 2;
            g2d.setFont(this.font);
            g2d.drawString(this.text, correctX, correctY);
        }
    }
    
    /**
     * Gets the font that the button is using.
     * 
     * @return IFont The font that the button is using.
     */
    public IFont getFont () {
        
        return this.font;
    }
    
    /**
     * Gets the Id of the button.
     * 
     * @return int The Id of the button.
     */
    public int getId () {
        
        return this.id;
    }
    
    /**
     * Gets the text to print on the button.
     * 
     * @return String The text to print on the button.
     */
    public String getText () {
        
        return this.text;
    }
    
    /**
     * Sets the font of the button.
     * 
     * @param font The new font to use.
     */
    public void setFont (IFont font) {
        
        this.font = font;
    }
    
    /**
     * Sets the text of the button.
     * 
     * @param text The new text to use.
     */
    public void setText (String text) {
        
        this.text = text;
    }
}