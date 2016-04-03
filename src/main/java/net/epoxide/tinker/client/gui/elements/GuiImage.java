package net.epoxide.tinker.client.gui.elements;

import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;

import net.epoxide.tinker.client.render.textures.AtlasTexture;

public class GuiImage extends GuiElement {
    
    private AtlasTexture texture;
    
    public GuiImage(float x, float y, AtlasTexture texture) {
        
        this(x, y, texture.getWidth(), texture.getHeight(), texture);
    }
    
    public GuiImage(float x, float y, float width, float height, AtlasTexture texture) {
        
        super(x, y, width, height);
        this.texture = texture;
    }
    
    @Override
    public void draw (Batcher batcher, float mouseX, float mouseY) {
        
        batcher.begin();
        batcher.vertex(this.getX(), this.getY() + this.getHeight());
        batcher.texCoord(this.texture.getMinU(), this.texture.getMaxV());
        batcher.color(Color.TRANSPARENT);
        
        batcher.vertex(this.getX() + this.getWidth(), this.getY() + this.getHeight());
        batcher.texCoord(this.texture.getMaxU(), this.texture.getMaxV());
        batcher.color(Color.TRANSPARENT);
        
        batcher.vertex(this.getX(), this.getY());
        batcher.texCoord(this.texture.getMinU(), this.texture.getMinV());
        batcher.color(Color.TRANSPARENT);
        
        batcher.vertex(this.getX() + this.getWidth(), this.getY() + this.getHeight());
        batcher.texCoord(this.texture.getMaxU(), this.texture.getMaxV());
        batcher.color(Color.TRANSPARENT);
        
        batcher.vertex(this.getX(), this.getY());
        batcher.texCoord(this.texture.getMinU(), this.texture.getMinV());
        batcher.color(Color.TRANSPARENT);
        
        batcher.vertex(this.getX() + this.getWidth(), this.getY());
        batcher.texCoord(this.texture.getMaxU(), this.texture.getMinV());
        batcher.color(Color.TRANSPARENT);
        batcher.end();
    }
    
    public AtlasTexture getTexture () {
        
        return this.texture;
    }
    
    public void setTexture (AtlasTexture texture) {
        
        this.texture = texture;
    }
}