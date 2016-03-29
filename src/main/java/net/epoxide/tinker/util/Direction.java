package net.epoxide.tinker.util;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(1, 0),
    RIGHT(-1, 0),
    
    UP_LEFT(1, -1),
    UP_RIGHT(-1, -1),
    DOWN_LEFT(1, 1),
    DOWN_RIGHT(-1, 1),
    UNKNOWN(0, 0);
    
    int x;
    int y;
    
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public static Direction getValue (float motionX, float motionY) {
        
        for (final Direction direction : values())
            if (direction.x == (motionX == 0 ? 0 : motionX < 0 ? 1 : -1) && direction.y == (motionY == 0 ? 0 : motionY < 0 ? 1 : -1))
                return direction;
                
        return UNKNOWN;
    }
}
