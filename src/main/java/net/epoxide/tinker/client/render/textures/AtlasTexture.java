package net.epoxide.tinker.client.render.textures;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.stb.STBImage;

import com.shc.silenceengine.core.IResource;
import com.shc.silenceengine.io.FilePath;
import com.shc.silenceengine.utils.BufferUtils;
import com.shc.silenceengine.utils.FileUtils;

public class AtlasTexture implements IResource {
    
    /**
     * The path for the texture to load.
     */
    private final FilePath path;
    
    /**
     * A buffer of bytes which contain the loaded image.
     */
    private ByteBuffer image;
    
    /**
     * The width of the image.
     */
    private int width;
    
    /**
     * The height of the image.
     */
    private int height;
    
    /**
     * The X of the texture.
     */
    private int x;
    
    /**
     * The Y of the texture.
     */
    private int y;
    
    /**
     * The starting U value.
     */
    private float minU;
    
    /**
     * The starting V value.
     */
    private float minV;
    
    /**
     * The end U value.
     */
    private float maxU;
    
    /**
     * The end V value.
     */
    private float maxV;
    
    private int components;
    
    /**
     * Constructs a new TileTexture from a String that contains a file path.
     * 
     * @param path The path to the file.
     */
    public AtlasTexture(String path) {
        
        this(FilePath.getResourceFile(path));
    }
    
    /**
     * Constructs a new TileTexture from a FilePath.
     * 
     * @param path The path to the file.
     */
    public AtlasTexture(FilePath path) {
        
        this.path = path;
    }
    
    /**
     * Handles the loading of the file.
     */
    public void loadFile () {
        
        final IntBuffer width = BufferUtils.createIntBuffer(1);
        final IntBuffer height = BufferUtils.createIntBuffer(1);
        final IntBuffer comp = BufferUtils.createIntBuffer(1);
        
        try {
            final ByteBuffer imageBuffer = FileUtils.readToByteBuffer(this.path.getInputStream());
            this.image = STBImage.stbi_load_from_memory(imageBuffer, width, height, comp, 0);
        }
        catch (final IOException exception) {
            
            exception.printStackTrace();
        }
        
        this.width = width.get(0);
        this.height = height.get(0);
        this.components = comp.get(0);
    }
    
    /**
     * Sets the U and V for the texture.
     * 
     * @param minU The starting U value.
     * @param minV The starting V value.
     * @param maxU The ending U value.
     * @param maxV The ending V value.
     */
    public void setUV (float minU, float minV, float maxU, float maxV) {
        
        this.minU = minU;
        this.minV = minV;
        this.maxU = maxU;
        this.maxV = maxV;
    }
    
    /**
     * Gets the ByteBuffer for the image being loaded.
     * 
     * @return ByteBuffer A buffer of bytes containing the image data.
     */
    public ByteBuffer getImage () {
        
        return this.image;
    }
    
    public int getX () {
        
        return this.x;
    }
    
    public void setX (int x) {
        
        this.x = x;
    }
    
    public int getY () {
        
        return this.y;
    }
    
    public void setY (int y) {
        
        this.y = y;
    }
    
    public int getHeight () {
        
        return this.height;
    }
    
    public int getWidth () {
        
        return this.width;
    }
    
    public int getComponents () {
        
        return this.components;
    }
    
    public FilePath getPath () {
        
        return this.path;
    }
    
    public float getMinU () {
        
        return this.minU;
    }
    
    public float getMinV () {
        
        return this.minV;
    }
    
    public float getMaxU () {
        
        return this.maxU;
    }
    
    public float getMaxV () {
        
        return this.maxV;
    }
    
    @Override
    public void dispose () {
    
    }
}