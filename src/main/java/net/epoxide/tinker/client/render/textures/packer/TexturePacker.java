package net.epoxide.tinker.client.render.textures.packer;

import java.awt.Dimension;
import java.nio.ByteBuffer;
import java.util.List;

import net.epoxide.tinker.client.render.textures.AtlasTexture;
import org.lwjgl.BufferUtils;

import com.shc.silenceengine.backend.lwjgl3.opengl.Texture;
import com.shc.silenceengine.math.geom2d.Rectangle;

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
        
        if (packed)
            System.out.println("Already Packed");

        if (entries.isEmpty())
            throw new IllegalStateException("nothing to pack");

        int maxWidth = 0;
        int maxHeight = 0;
        int totalArea = 0;
        
        for (AtlasTexture texture : entries) {
            int width = texture.getWidth();
            int height = texture.getHeight();
            
            if (width > maxWidth)
                maxWidth = width;
            if (height > maxHeight)
                maxHeight = height;

            totalArea += width * height;
        }
        
        Dimension size = new Dimension(closestTwoPower(maxWidth), closestTwoPower(maxHeight));
        boolean fitAll = false;
        
        loop : while (!fitAll) {
            int area = size.width * size.height;
            if (area < totalArea) {
                nextSize(size);
                continue;
            }
            
            Node root = new Node(size.width, size.height);
            for (AtlasTexture texture : entries) {
                Node inserted = root.insert(texture);
                if (inserted == null) {
                    nextSize(size);
                    continue loop;
                }
            }
            fitAll = true;
        }
        
        ByteBuffer imageBuffer = BufferUtils.createByteBuffer(size.width * size.height * 4);
        
        for (AtlasTexture texture : entries) {
            
            ByteBuffer buffer = texture.getImage();
            
            for (int y = 0; y < texture.getHeight(); y++) {
                for (int x = 0; x < texture.getWidth(); x++) {
                    int position = (x + texture.getX()) * texture.getComponents() + (y + texture.getY()) * (size.width * 4);
                    
                    imageBuffer.put(position, buffer.get());
                    imageBuffer.put(position + 1, buffer.get());
                    imageBuffer.put(position + 2, buffer.get());
                    imageBuffer.put(position + 3, texture.getComponents() == 4 ? buffer.get() : 0);
                }
            }
            
            float minU = (float) (texture.getX()) / size.width;
            float minV = (float) (texture.getY()) / size.height;
            float maxU = (float) (texture.getX() + texture.getWidth()) / size.width;
            float maxV = (float) (texture.getY() + texture.getHeight()) / size.height;
            texture.setUV(minU, minV, maxU, maxV);
        }
        imageBuffer.flip();
        packed = true;
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
        while (power < i) {
            power <<= 1;
        }
        return power;
    }
    
    private static class Node {
        private final Node[] child = new Node[2];
        private final Rectangle rc = new Rectangle();
        private AtlasTexture image;
        
        private Node() {

        }
        
        private Node(int width, int height) {
            
            rc.set(0, 0, width, height);
        }
        
        private boolean isLeaf () {
            
            return child[0] == null && child[1] == null;
        }
        
        private Node insert (AtlasTexture texture) {
            
            if (!isLeaf()) {
                Node newNode = child[0].insert(texture);
                if (newNode != null)
                    return newNode;

                return child[1].insert(texture);
            }
            else {

                if (this.image != null)
                    return null;

                int width = texture.getWidth();
                int height = texture.getHeight();

                if ((width > rc.getWidth()) || (height > rc.getHeight()))
                    return null;

                if ((width == rc.getWidth()) && (height == rc.getHeight())) {
                    this.image = texture;
                    this.image.setX((int) this.rc.getX());
                    this.image.setY((int) this.rc.getY());
                    return this;
                }

                child[0] = new Node();
                child[1] = new Node();

                float dw = rc.getWidth() - width;
                float dh = rc.getHeight() - height;
                
                if (dw > dh) {
                    child[0].rc.set(rc.getX(), rc.getY(), width, rc.getHeight());
                    child[1].rc.set(rc.getX() + width, rc.getY(), rc.getWidth() - width, rc.getHeight());
                }
                else {
                    child[0].rc.set(rc.getX(), rc.getY(), rc.getWidth(), height);
                    child[1].rc.set(rc.getX(), rc.getY() + height, rc.getWidth(), rc.getHeight() - height);
                }
                return child[0].insert(texture);
            }
            
        }
        
        @Override
        public String toString () {
            
            return rc + ((this.image == null) ? " <no entry>" : " " + this.image.toString());
        }
    }
}