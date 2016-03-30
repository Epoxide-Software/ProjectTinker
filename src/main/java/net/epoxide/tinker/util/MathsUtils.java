package net.epoxide.tinker.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.shc.silenceengine.math.Vector3;

import net.epoxide.tinker.TinkerGame;

public final class MathsUtils {
    
    /**
     * Gets the middle integer between two other integers. The order is not important.
     * 
     * @param first The first integer.
     * @param second The second integer.
     * @return int The integer that is between the two provided integers.
     */
    public static int getAverage (int first, int second) {
        
        return Math.round((first + second) / 2.0F);
    }
    
    /**
     * Calculates the distance between two Vec3 positions.
     * 
     * @param firstPos The first position to work with.
     * @param secondPos The second position to work with.
     * @return double The distance between the two provided locations.
     */
    public static double getDistanceBetweenPoints (Vector3 firstPos, Vector3 secondPos) {
        
        final double distanceX = firstPos.x - secondPos.x;
        final double distanceY = firstPos.y - secondPos.y;
        final double distanceZ = firstPos.z - secondPos.z;
        
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
    }
    
    /**
     * Checks if a double is within range of two other doubles.
     * 
     * @param min The smallest valid value.
     * @param max The largest valid value.
     * @param value The value to check.
     * @return boolean Whether or not the value is within the provided scope.
     */
    public static boolean isInRange (double min, double max, double value) {
        
        return value <= max && value >= min;
    }
    
    /**
     * Used to retrieve a random integer between the two provided integers. The integers
     * provided are also possible outcomes.
     * 
     * @param min The minimum value which can be returned by this method.
     * @param max The maximum value which can be returned by this method.
     */
    public static int nextIntInclusive (int min, int max) {
        
        return TinkerGame.RANDOM.nextInt(max - min + 1) + min;
    }
    
    /**
     * This method can be used to round a double to a certain amount of places.
     * 
     * @param value The double being round.
     * @param places The amount of places to round the double to.
     * @return double The double entered however being rounded to the amount of places
     *         specified.
     */
    public static double round (double value, int places) {
        
        return value >= 0 && places > 0 ? new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue() : value;
    }
    
    /**
     * Converts time in ticks to a human readable string.
     * 
     * @param ticks The amount of ticks to convert.
     * @return String A human readable version of the time.
     */
    public static String ticksToTime (int ticks) {
        
        final int seconds = ticks / 20;
        final int minutes = seconds / 60;
        return minutes + "" + seconds;
    }
    
    /**
     * A method which handles the calculating of percentages. While this isn't a particularly
     * difficult piece of code, it has been added for the sake of simplicity.
     * 
     * @param percent The percent chance that this method should return true. 1.00 = 100%
     * @return boolean Returns are randomly true or false, based on the suplied percentage.
     */
    public static boolean tryPercentage (double percent) {
        
        return Math.random() < percent;
    }
}