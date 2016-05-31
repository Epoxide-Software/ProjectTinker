package net.epoxide.tests.gen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Room {
    
    public int width;
    public int height;
    public int x;
    public int y;
    public List<Point> doors;
    
    public Room(int width, int height, int x, int y) {
        
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.doors = new ArrayList<>();
    }
    
}
