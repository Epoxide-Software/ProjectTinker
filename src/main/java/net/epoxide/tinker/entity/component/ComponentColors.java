package net.epoxide.tinker.entity.component;

@Deprecated
public class ComponentColors extends EntityComponent {
    
    /**
     * An array of the various colors used by the entity. Each is stored as a hexadecimal
     * integer.
     */
    private int[] colors;
    
    /**
     * The expected length of the color array. Starts at 0, just the array.
     */
    private int expectedLength;
    
    /**
     * Provides the entity with an array of color codes. These can be used in the rendering of
     * the entity, or for other things. This constructor assumes that the length of the array
     * provided is the expected length that should be used.
     * 
     * @param colors The array of colors to store.
     */
    public ComponentColors(int[] colors) {
        
        this(colors, colors.length);
    }
    
    /**
     * Provides the entity with an array of color codes. These can be used in the rendering of
     * the entity, or for other things.
     * 
     * @param colors The array of colors to store.
     * @param expectedLength The expected length of the color array.
     */
    public ComponentColors(int[] colors, int expectedLength) {
        
        this.colors = colors;
        this.expectedLength = expectedLength;
    }
    
    /**
     * Gets the entire array of colors used by the entity.
     * 
     * @return int[] The array of colors used.
     */
    public int[] getColors () {
        
        return this.colors;
    }
    
    /**
     * Sets the entire array of colors used by the entity. If the length of the new array does
     * not match the expected length of the array, the colors will not be set.
     * 
     * @param colors An array of color codes.
     */
    public void setColors (int[] colors) {
        
        if (this.colors.length == this.expectedLength)
            this.colors = colors;
    }
    
    /**
     * Gets the color code for a specific layer. If the layer is not within range, black will
     * be returned.
     * 
     * @param layer The layer to grab the color for.
     * @return int The color code for the specified layer. Black if that layer is not valid.
     */
    public int getColorForLayer (int layer) {
        
        if (this.colors.length <= layer)
            return this.colors[layer];
            
        return 0x000000;
    }
    
    /**
     * Sets the color code for a specific layer. If the layer is not within range, nothing will
     * happen.
     * 
     * @param color The new color code to set.
     * @param layer The layer to apply the color to.
     */
    public void setColorForLayer (int color, int layer) {
        
        if (this.colors.length <= layer)
            this.colors[layer] = color;
    }
    
    /**
     * Retrieves the expected length of the color code array. Array lengths start at 0!
     * 
     * @return int The expected length of the color code array.
     */
    public int getExpectedLength () {
        
        return this.expectedLength;
    }
    
    /**
     * Changes the expected length of the color code array. Array lengths start at 0! You
     * should never set the length to below 0. When updating the expected length of the array,
     * you should also update the array itself to match the new length.
     * 
     * @param length The new expected length for the color code array.
     */
    public void setExpectedLength (int length) {
        
        this.expectedLength = length;
    }
}
