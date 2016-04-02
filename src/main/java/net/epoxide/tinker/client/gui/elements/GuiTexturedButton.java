package net.epoxide.tinker.client.gui.elements;

import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;

import net.epoxide.tinker.client.render.textures.AtlasTexture;

public class GuiTexturedButton extends GuiButton {
    
    private AtlasTexture baseTexture;
    private AtlasTexture hoverTexture;
    
    public GuiTexturedButton(int id, float x, float y, AtlasTexture texture) {
        this(id, x, y, texture.getWidth(), texture.getHeight(), texture, null);
    }
    
    public GuiTexturedButton(int id, float x, float y, AtlasTexture baseTexture, AtlasTexture hoverTexture) {
        this(id, x, y, baseTexture.getWidth(), baseTexture.getHeight(), baseTexture, hoverTexture);
    }
    
    public GuiTexturedButton(int id, float x, float y, float width, float height, AtlasTexture baseTexture, AtlasTexture hoverTexture) {
        super(id, x, y, width, height);
        this.baseTexture = baseTexture;
        this.hoverTexture = hoverTexture;
    }
    
    @Override
    public void draw (Batcher batcher, float mouseX, float mouseY) {
        
        if (this.isVisible()) {
            
            final AtlasTexture texture = this.isMouseOver(mouseX, mouseY) ? this.hoverTexture : this.baseTexture;
            batcher.begin();
            batcher.vertex(this.getX(), this.getY() + this.getHeight());
            batcher.texCoord(texture.getMinU(), texture.getMaxV());
            batcher.color(Color.TRANSPARENT);
            
            batcher.vertex(this.getX() + this.getWidth(), this.getY() + this.getHeight());
            batcher.texCoord(texture.getMaxU(), texture.getMaxV());
            batcher.color(Color.TRANSPARENT);
            
            batcher.vertex(this.getX(), this.getY());
            batcher.texCoord(texture.getMinU(), texture.getMinV());
            batcher.color(Color.TRANSPARENT);
            
            batcher.vertex(this.getX() + this.getWidth(), this.getY() + this.getHeight());
            batcher.texCoord(texture.getMaxU(), texture.getMaxV());
            batcher.color(Color.TRANSPARENT);
            
            batcher.vertex(this.getX(), this.getY());
            batcher.texCoord(texture.getMinU(), texture.getMinV());
            batcher.color(Color.TRANSPARENT);
            
            batcher.vertex(this.getX() + this.getWidth(), this.getY());
            batcher.texCoord(texture.getMaxU(), texture.getMinV());
            batcher.color(Color.TRANSPARENT);
            batcher.end();
        }
    }
    
    public AtlasTexture getBaseTexture () {
        
        return this.baseTexture;
    }
    
    public AtlasTexture getHoverTexture () {
        
        return this.hoverTexture;
    }
    
    public void setBaseTexture (AtlasTexture baseTexture) {
        
        this.baseTexture = baseTexture;
    }
    
    public void setHoverTexture (AtlasTexture hoverTexture) {
        
        this.hoverTexture = hoverTexture;
    }
}
