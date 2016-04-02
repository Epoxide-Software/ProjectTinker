package net.epoxide.tinker.client.gui.elements;

import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;
import net.epoxide.tinker.client.gui.Gui;
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
    public void draw(Batcher batcher, float mouseX, float mouseY) {

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

    public AtlasTexture getTexture() {
        return texture;
    }

    public void setTexture(AtlasTexture texture) {
        this.texture = texture;
    }
}