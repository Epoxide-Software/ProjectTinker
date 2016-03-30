package net.epoxide.tinker.client.render.textures;

import java.util.List;

import com.shc.silenceengine.backend.lwjgl3.opengl.Texture;

import net.epoxide.tinker.client.render.textures.packer.TexturePacker;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.util.RegistryName;

public class TextureManager {
    
    /**
     * The registry of textures to be loaded.
     */
    public static NamedRegistry<AtlasTexture> REGISTRY = new NamedRegistry<>();
    
    /**
     * The texture atlas generated for the tiles.
     */
    public static Texture texture;
    
    /**
     * Initializes the loading of textures.
     */
    public static void init () {
        
        final List<AtlasTexture> textureList = REGISTRY.getValues();
        final ResourceLoading resourceLoading = new ResourceLoading();
        
        resourceLoading.load(REGISTRY);
        texture = new TexturePacker().packImages(textureList);
    }
    
    /**
     * Registers a tile texture to be loaded at the end of the preInit stage.
     * 
     * @param texture The texture to render, follows standard RegistryName convention.
     */
    public static void registerTileTextures (String texture) {
        
        final RegistryName name = new RegistryName(texture);
        REGISTRY.registerValue(name, new AtlasTexture("assets/" + name.getDomain() + "/textures/tiles/" + name.getName() + (name.getName().contains("png") ? "" : ".png")));
    }
}