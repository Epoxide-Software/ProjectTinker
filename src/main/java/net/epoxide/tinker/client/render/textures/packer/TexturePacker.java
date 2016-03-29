package net.epoxide.tinker.client.render.textures.packer;

import java.awt.Dimension;
import java.nio.ByteBuffer;
import java.util.List;

import org.lwjgl.BufferUtils;

import com.shc.silenceengine.backend.lwjgl3.opengl.Texture;
import com.shc.silenceengine.math.geom2d.Rectangle;

import net.epoxide.tinker.client.render.textures.AtlasTexture;

public class TexturePacker {
    
    /**
     * Whether or not the textures have been packed.
     */
    private boolean packed = false;
    
    /**
     * Packs all textures into a single texture atlas.
     *
     * @param entries A List of textures to pack.
     * @return Texture A single texture that has been packed together.
     */
    public Texture packImages (List<AtlasTexture> entries) {

        if (this.packed)
            System.out.println("Already Packed");

        if (entries.isEmpty())
            throw new IllegalStateException("nothing to pack");

        int maxWidth = 0;
        int maxHeight = 0;
        int totalArea = 0;

        for (final AtlasTexture texture : entries) {

            final int width = texture.getWidth();
            final int height = texture.getHeight();

            if (width > maxWidth)
                maxWidth = width;

            if (height > maxHeight)
                maxHeight = height;

            totalArea += width * height;
        }

        final Dimension size = new Dimension(this.closestTwoPower(maxWidth), this.closestTwoPower(maxHeight));
        boolean fitAll = false;

        loop : while (!fitAll) {
            final int area = size.width * size.height;
            if (area < totalArea) {
                this.nextSize(size);
                continue;
            }

            final Node root = new Node(size.width, size.height);
            for (final AtlasTexture texture : entries) {
                final Node inserted = root.insert(texture);
                if (inserted == null) {
                    this.nextSize(size);
                    continue loop;
                }
            }
            fitAll = true;
        }

        final ByteBuffer imageBuffer = BufferUtils.createByteBuffer(size.width * size.height * 4);

        for (final AtlasTexture texture : entries) {

            final ByteBuffer buffer = texture.getImage();

            for (int y = 0; y < texture.getHeight(); y++)
                for (int x = 0; x < texture.getWidth(); x++) {

                    final int position = (x + texture.getX()) * texture.getComponents() + (y + texture.getY()) * size.width * 4;

                    imageBuffer.put(position, buffer.get());
                    imageBuffer.put(position + 1, buffer.get());
                    imageBuffer.put(position + 2, buffer.get());
                    imageBuffer.put(position + 3, texture.getComponents() == 4 ? buffer.get() : 0);
                }

            final float minU = (float) (texture.getX() + 1) / size.width;
            final float minV = (float) (texture.getY() + 1) / size.width;
            final float maxU = (float) (texture.getX() + texture.getWidth() - 1) / size.width;
            final float maxV = (float) (texture.getY() + texture.getHeight() - 1) / size.height;
            texture.setUV(minU, minV, maxU, maxV);
        }

        imageBuffer.flip();
        this.packed = true;
        return Texture.fromByteBuffer(imageBuffer, size.width, size.height, 4);
    }
    
    private void nextSize (Dimension size) {
        
        if (size.width > size.height)
            size.height <<= 1;
        else
            size.width <<= 1;
    }
    
    // TODO move to MathUtils
    private int closestTwoPower (int i) {
        
        int power = 1;
        while (power < i)
            power <<= 1;
        return power;
    }

    private static class Node {
        private final Node[] child = new Node[2];
        private final Rectangle rc = new Rectangle();
        private AtlasTexture image;

        private Node() {

        }

        private Node(int width, int height) {

            this.rc.set(0, 0, width, height);
        }

        private boolean isLeaf () {

            return this.child[0] == null && this.child[1] == null;
        }

        private Node insert (AtlasTexture texture) {

            if (!this.isLeaf()) {
                final Node newNode = this.child[0].insert(texture);
                if (newNode != null)
                    return newNode;

                return this.child[1].insert(texture);
            }
            else {

                if (this.image != null)
                    return null;

                final int width = texture.getWidth();
                final int height = texture.getHeight();

                if (width > this.rc.getWidth() || height > this.rc.getHeight())
                    return null;

                if (width == this.rc.getWidth() && height == this.rc.getHeight()) {
                    this.image = texture;
                    this.image.setX((int) this.rc.getX());
                    this.image.setY((int) this.rc.getY());
                    return this;
                }

                this.child[0] = new Node();
                this.child[1] = new Node();

                final float dw = this.rc.getWidth() - width;
                final float dh = this.rc.getHeight() - height;

                if (dw > dh) {
                    this.child[0].rc.set(this.rc.getX(), this.rc.getY(), width, this.rc.getHeight());
                    this.child[1].rc.set(this.rc.getX() + width, this.rc.getY(), this.rc.getWidth() - width, this.rc.getHeight());
                }
                else {
                    this.child[0].rc.set(this.rc.getX(), this.rc.getY(), this.rc.getWidth(), height);
                    this.child[1].rc.set(this.rc.getX(), this.rc.getY() + height, this.rc.getWidth(), this.rc.getHeight() - height);
                }
                return this.child[0].insert(texture);
            }

        }

        @Override
        public String toString () {

            return this.rc + (this.image == null ? " <no entry>" : " " + this.image.toString());
        }
    }
}