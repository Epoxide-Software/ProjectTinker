package net.epoxide.tests.utils;

public class MathUtil {

    public static boolean rectColliding(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2, int widthBuffer, int heightBuffer) {
        return ((x1 - widthBuffer < x2 + w2) && (x2 < x1 + w1 + widthBuffer) && (y1 - heightBuffer < y2 + h2) && (y2 < y1 + h1 + heightBuffer));
    }
}
