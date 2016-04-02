package net.epoxide.tinker.client.gui.elements;

import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Graphics2D;
import com.shc.silenceengine.graphics.IFont;
import net.epoxide.tinker.client.gui.Gui;

public class GuiButton extends GuiElement {

    private int id;
    private String text;
    private IFont font;

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
    public void draw(Batcher batcher, float mouseX, float mouseY) {

        Graphics2D g2d = Graphics2D.getInstance();
        float centerX = this.getX() + (this.getWidth() / 2);
        float centerY = this.getY() + (this.getHeight() / 2);

        if (this.text != null && this.isVisible()) {
            float correctX = centerX - (this.font.getWidth(this.text) / 2);
            float correctY = centerY - (this.font.getHeight() / 2);
            g2d.setFont(this.font);
            g2d.drawString(this.text, correctX, correctY);
        }
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public IFont getFont() {
        return font;
    }

    public void setFont(IFont font) {
        this.font = font;
    }
}