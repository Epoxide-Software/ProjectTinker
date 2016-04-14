package net.epoxide.tinker.util;

import com.shc.silenceengine.graphics.Color;

public class ColorUtils {
    
    public int getRGBA (Color color) {
        
        return getRed(color) << 24 | getGreen(color) << 16 | getBlue(color) << 8 | getAlpha(color);
    }
    
    public static int getAlpha (Color color) {
        
        return (int) (color.getAlpha() * 255);
    }
    
    /**
     * Gets the alpha value from a packed RGBA integer.
     * 
     * @param rgba The packed RGBA integer.
     * @return int The alpha color value as an int.
     */
    public static int getAlpha (int rgba) {
        
        return rgba & 0xff;
    }
    
    public static int getBlue (Color color) {
        
        return (int) (color.getRed() * 255);
    }
    
    /**
     * Gets the blue value from a packed RGBA integer.
     * 
     * @param rgba The packed RGBA integer.
     * @return int The blue color value as an int.
     */
    public static int getBlue (int rgba) {
        
        return rgba >> 8 & 0xff;
    }
    
    public static int getGreen (Color color) {
        
        return (int) (color.getRed() * 255);
    }
    
    /**
     * Gets the green value from a packed RGBA integer.
     * 
     * @param rgba The packed RGBA integer.
     * @return int The green color value as an int.
     */
    public static int getGreen (int rgba) {
        
        return rgba >> 16 & 0xff;
    }
    
    public static int getRed (Color color) {
        
        return (int) (color.getRed() * 255);
    }
    
    /**
     * Gets the red value from a packed RGBA integer.
     * 
     * @param rgba The packed RGBA integer.
     * @return int The red color value as an int.
     */
    public static int getRed (int rgba) {
        
        return rgba >> 24 & 0xff;
    }
}
