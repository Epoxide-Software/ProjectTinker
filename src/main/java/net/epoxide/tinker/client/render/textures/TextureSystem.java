package net.epoxide.tinker.client.render.textures;

import com.shc.silenceengine.backend.lwjgl3.opengl.Texture;
import net.epoxide.tinker.client.render.textures.packer.TexturePacker;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.util.RegistryName;

import java.util.Arrays;
import java.util.List;

public class TextureSystem {

    public static Texture texture;
    public static NamedRegistry<TileTexture> REGISTRY = new NamedRegistry<>();

    /**
     *
     */
    public static void init () {

        List<TileTexture> textureList = Arrays.asList(REGISTRY.getValues());
        ResourceLoading resourceLoading = new ResourceLoading();

        resourceLoading.load(REGISTRY);
        texture = new TexturePacker().packImages(textureList);
    }

    public static void registerTileTextures (String texture) {

        RegistryName name = new RegistryName(texture);
        REGISTRY.registerValue(name, new TileTexture("assets/" + name.getDomain() + "/textures/tiles/" + name.getName() + (name.getName().contains("png") ? "" : ".png")));
    }
}