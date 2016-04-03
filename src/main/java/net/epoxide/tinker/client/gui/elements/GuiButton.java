package net.epoxide.tinker.client.gui.elements;

import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Graphics2D;
import com.shc.silenceengine.graphics.IFont;

public class GuiButton extends GuiElement {
    
    private IFont font;
    private final int id;
    private String text;
    
    public GuiButton(int id, float x, float y, float width, float height) {
        
        this(id, x, y, width, height, null);
    }
    
    public GuiButton(int id, float x, float y, float width, float height, String text) {
        
        this(id, x, y, width, height, text, SilenceEngine.graphics.getGraphics2D().getFont());
    }
    
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
    
    public IFont getFont () {
        
        return this.font;
    }
    
    public int getId () {
        
        return this.id;
    }
    
    public String getText () {
        
        return this.text;
    }
    
    public void setFont (IFont font) {
        
        this.font = font;
    }
    
    public void setText (String text) {
        
        this.text = text;
    }
}