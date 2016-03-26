package net.epoxide.tinker.client.render.textures;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.stb.STBImage;

import com.shc.silenceengine.core.IResource;
import com.shc.silenceengine.io.FilePath;
import com.shc.silenceengine.utils.BufferUtils;
import com.shc.silenceengine.utils.FileUtils;

public class TileTexture implements IResource {
    
    /**
     * The path for the texture to load.
     */
    public FilePath path;
    
    /**
     * A buffer of bytes which contain the loaded image.
     */
    private ByteBuffer image;
    
    /**
     * The width of the image.
     */
    public int width;
    
    /**
     * The height of the image.
     */
    public int height;
    
    /**
     * The X of the texture.
     */
    public int x;
    
    /**
     * The Y of the texture.
     */
    public int y;
    
    /**
     * The starting U value.
     */
    public float minU;
    
    /**
     * The starting V value.
     */
    public float minV;
    
    /**
     * The end U value.
     */
    public float maxU;
    
    /**
     * The end V value.
     */
    public float maxV;
    
    public int comp;
    
    /**
     * Constructs a new TileTexture from a String that contains a file path.
     * 
     * @param path The path to the file.
     */
    public TileTexture(String path) {
        
        this(FilePath.getResourceFile(path));
    }
    
    /**
     * Constructs a new TileTexture from a FilePath.
     * 
     * @param path The path to the file.
     */
    public TileTexture(FilePath path) {
        
        this.path = path;
    }
    
    /**
     * Handles the loading of the file.
     */
    public void loadFile () {
        
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);
        try {
            ByteBuffer imageBuffer = FileUtils.readToByteBuffer(path.getInputStream());
            this.image = STBImage.stbi_load_from_memory(imageBuffer, width, height, comp, 0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        this.width = width.get(0);
        this.height = height.get(0);
        this.comp = comp.get(0);
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
        
        return image;
    }
    
    @Override
    public void dispose () {
    
    }
}