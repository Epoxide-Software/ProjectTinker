package net.epoxide.tinker.client.render.textures.packer;

import java.awt.Dimension;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import com.shc.silenceengine.backend.lwjgl3.opengl.Texture;
import com.shc.silenceengine.math.geom2d.Rectangle;

import net.epoxide.tinker.client.render.textures.TileTexture;

public class TexturePacker {
    
    private boolean packed = false;
    
    public Texture packImages (java.util.List<TileTexture> entries) {
        
        if (packed)
            System.out.println("Already Packed");
            
        if (entries.isEmpty())
            throw new IllegalStateException("nothing to pack");
            
        int maxWidth = 0;
        int maxHeight = 0;
        int totalArea = 0;
        
        for (TileTexture img : entries) {
            int width = img.width;
            int height = img.height;
            
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
            for (TileTexture img : entries) {
                Node inserted = root.insert(img);
                if (inserted == null) {
                    nextSize(size);
                    continue loop;
                }
            }
            fitAll = true;
        }
        
        ByteBuffer imageBuffer = BufferUtils.createByteBuffer(size.width * size.height * 4);
        
        for (TileTexture img : entries) {
            
            System.out.println(img.comp);
            ByteBuffer buffer = img.getImage();
            while (buffer.hasRemaining()) {
                int w1 = (buffer.position() % (img.width * 4)) + 4 * img.x;
                int w2 = ((buffer.position() / (img.width * 4)) + img.y) * (size.width * 4);
                
                int wh = w1 + w2;
                imageBuffer.put(wh, buffer.get());
            }
            
            float minU = (float) (img.x) / size.width;
            float minV = (float) (img.y) / size.height;
            float maxU = (float) (img.x + img.width) / size.width;
            float maxV = (float) (img.y + img.height) / size.height;
            img.setUV(minU, minV, maxU, maxV);
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
        private TileTexture image;
        
        private Node() {
        
        }
        
        private Node(int width, int height) {
            
            rc.set(0, 0, width, height);
        }
        
        private boolean isLeaf () {
            
            return child[0] == null && child[1] == null;
        }
        
        private Node insert (TileTexture image) {
            
            if (!isLeaf()) {
                Node newNode = child[0].insert(image);
                if (newNode != null)
                    return newNode;
                    
                // no room, insert into second
                return child[1].insert(image);
            }
            else {
                // if there's already a image here, return
                if (this.image != null)
                    return null;
                    
                int width = image.width;
                int height = image.height;
                
                // (if we're too small, return)
                if ((width > rc.getWidth()) || (height > rc.getHeight()))
                    return null;
                    
                // (if we're just right, accept)
                if ((width == rc.getWidth()) && (height == rc.getHeight())) {
                    this.image = image;
                    this.image.x = (int) this.rc.getX();
                    this.image.y = (int) this.rc.getY();
                    return this;
                }
                
                // otherwise, split this node
                child[0] = new Node();
                child[1] = new Node();
                
                // (decide which way to split)
                float dw = rc.getWidth() - width;
                float dh = rc.getHeight() - height;
                
                if (dw > dh) { // split horizontally
                    child[0].rc.set(rc.getX(), rc.getY(), width, rc.getHeight());
                    child[1].rc.set(rc.getX() + width, rc.getY(), rc.getWidth() - width, rc.getHeight());
                }
                else { // split vertically
                    child[0].rc.set(rc.getX(), rc.getY(), rc.getWidth(), height);
                    child[1].rc.set(rc.getX(), rc.getY() + height, rc.getWidth(), rc.getHeight() - height);
                }
                // insert into first child we created
                return child[0].insert(image);
            }
            
        }
        
        @Override
        public String toString () {
            
            return rc + ((image == null) ? " <no entry>" : " " + image.toString());
        }
    }
}