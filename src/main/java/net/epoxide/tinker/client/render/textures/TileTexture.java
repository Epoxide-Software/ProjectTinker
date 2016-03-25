package net.epoxide.tinker.client.render.textures;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.stb.STBImage;

import com.shc.silenceengine.backend.lwjgl3.opengl.Texture;
import com.shc.silenceengine.core.IResource;
import com.shc.silenceengine.io.FilePath;
import com.shc.silenceengine.utils.BufferUtils;
import com.shc.silenceengine.utils.FileUtils;

public class TileTexture implements IResource {
    
    public FilePath path;
    private ByteBuffer image;
    
    public int width;
    public int height;
    public int x;
    public int y;
    
    public float minU;
    public float minV;
    public float maxU;
    public float maxV;
    public int comp;
    
    public TileTexture(String path) {
        
        this(FilePath.getResourceFile(path));
    }
    
    public TileTexture(FilePath path) {
        
        this.path = path;
    }
    
    public void loadFile () {
        
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);
        try {
            ByteBuffer imageBuffer = FileUtils.readToByteBuffer(path.getInputStream());
            this.image = STBImage.stbi_load_from_memory(imageBuffer, w, h, comp, 0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        this.width = w.get(0);
        this.height = h.get(0);
        this.comp = comp.get(0);
    }
    
    public void setUV (float minU, float minV, float maxU, float maxV) {
        
        this.minU = minU;
        this.minV = minV;
        this.maxU = maxU;
        this.maxV = maxV;
    }
    
    public ByteBuffer getImage () {
        
        return image;
    }
    
    private Texture getTexture () {
        
        return TextureSystem.texture;
    }
    
    @Override
    public void dispose () {
    
    }
}
