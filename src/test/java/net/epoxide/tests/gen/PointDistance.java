package net.epoxide.tests.gen;

public class PointDistance {
    public int x;
    public int y;
    public int distance;
    public PointDistance parent;
    
    public PointDistance(int x, int y, int distance, PointDistance parent) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.parent = parent;
    }
}
