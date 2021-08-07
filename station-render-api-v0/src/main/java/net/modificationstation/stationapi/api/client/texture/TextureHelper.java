package net.modificationstation.stationapi.api.client.texture;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.mixin.render.client.TextureManagerAccessor;

import java.awt.image.*;
import java.io.*;

public class TextureHelper {

    public static BufferedImage getTexture(String path) {
        //noinspection deprecation
        return ((TextureManagerAccessor) ((Minecraft) FabricLoader.getInstance().getGameInstance()).textureManager).invokeMethod_1091(getTextureStream(path));
    }

    public static InputStream getTextureStream(String path) {
        //noinspection deprecation
        return ((Minecraft) FabricLoader.getInstance().getGameInstance()).texturePackManager.texturePack.getResourceAsStream(path);
    }
}