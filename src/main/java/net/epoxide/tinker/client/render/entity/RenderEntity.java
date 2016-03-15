package net.epoxide.tinker.client.render.entity;

import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;
import net.darkhax.opennbt.tags.CompoundTag;
import net.epoxide.tinker.entity.Entity;

public class RenderEntity {

    public void render (Batcher batcher, Entity e) {

        CompoundTag data = e.getEntityData();

        float xPos = 0;
        float yPos = 0;
        if (data.hasTag("position")) {
            CompoundTag position = data.getCompoundTag("position");
            xPos = position.getFloat("xPos");
            yPos = position.getFloat("yPos");
        }

        float hW = 0.5f;
        float hH = 0.5f;
        if (data.hasTag("size")) {
            CompoundTag position = data.getCompoundTag("size");
            hW = position.getFloat("width");
            hH = position.getFloat("height");
        }

        batcher.vertex(xPos - hW, yPos + hH);
        batcher.vertex(xPos + hW, yPos + hH);
        batcher.vertex(xPos - hW, yPos - hH);
        batcher.vertex(xPos + hW, yPos + hH);
        batcher.vertex(xPos - hW, yPos - hH);
        batcher.vertex(xPos + hW, yPos - hH);

        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);

        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
        batcher.color(Color.WHITE);
    }
}
