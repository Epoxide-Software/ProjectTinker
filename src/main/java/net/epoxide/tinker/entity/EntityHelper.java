package net.epoxide.tinker.entity;

import java.awt.Color;

import net.epoxide.tinker.entity.component.ComponentColors;
import net.epoxide.tinker.entity.component.ComponentHealth;
import net.epoxide.tinker.entity.component.ComponentPosition;

public class EntityHelper {
    
    /**
     * Creates a standard green slime. The slime will have 20 health, and will have the color
     * green stored in its data.
     * 
     * @param x: The X position to put the slime at.
     * @param y: The Y position to put the slime at.
     * @return Entity: The newly created green slime.
     */
    public static Entity createGreenSlime (int x, int y) {
        
        return new Entity("slime").addComponent(new ComponentPosition(x, y)).addComponent(new ComponentHealth(20)).addComponent(new ComponentColors(new int[] { Color.GREEN.getRGB() }));
    }
}