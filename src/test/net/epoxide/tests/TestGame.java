package net.epoxide.tests;

import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.utils.Logger;

import net.epoxide.tinker.util.ColorData;
import net.epoxide.tinker.util.lang.I18n;
import net.epoxide.tinker.util.lang.LanguageType;

public class TestGame extends Game {
    
    public static String[] args;
    
    public long finish;
    public long start;
    
    /**
     * Test for confirming that color bit shifting is actually working.
     */
    private void testColors () {
        
        final ColorData color = ColorData.DARK_SEA_GREEN;
        final int rgba = color.getRGBA();
        final int red = ColorData.getRed(rgba);
        final int green = ColorData.getGreen(rgba);
        final int blue = ColorData.getBlue(rgba);
        final int alpha = ColorData.getAlpha(rgba);
        
        Logger.info("The color test", "Passing: " + (red == color.getRed() && green == color.getGreen() && blue == color.getBlue() && alpha == color.getAlpha()), "Red: " + color.getRed() + " " + red, "Green: " + color.getGreen() + " " + green, "Blue: " + color.getBlue() + " " + blue, "Alpha: " + color.getAlpha() + " " + alpha);
    }
    
    /**
     * Tests that the internationalization of text actually works by loading a string from the
     * en_US.lang file for a test translation key.
     */
    private void testI18n () {
        
        this.start = System.nanoTime();
        
        final String translation = I18n.translate("test.language.translation");
        
        this.finish = System.nanoTime();
        
        Logger.info("The I18n test", "Passing: " + translation.equals("Tango Echo Sierra Tango"), "Time: " + (this.finish - this.start), "Translation: " + translation);
    }
    
    /**
     * Tests that the internationalization of text actually works by loading a string from the
     * de_DE.lang file for a test translation.
     */
    private void testI18nGerman () {
        
        this.start = System.nanoTime();
        
        I18n.setCurrentLanguage(LanguageType.GERMAN);
        final String translation = I18n.translate("test.language.translation");
        
        this.finish = System.nanoTime();
        I18n.setCurrentLanguage(LanguageType.ENGLISH);
        
        Logger.info("The I18n test", "Passing: " + translation.equals("Theodor Emil Sigfried Theodor"), "Time: " + (this.finish - this.start), "Translation: " + translation);
    }
    
    @Override
    public void init () {
        
        for (final String arg : args) {
            
            final boolean all = arg.equalsIgnoreCase("all");
            
            if (arg.equalsIgnoreCase("testI18n") || all)
                this.testI18n();
                
            if (arg.equalsIgnoreCase("testI18nGerman") || all)
                this.testI18nGerman();
                
            if (arg.equalsIgnoreCase("testColors") || all)
                this.testColors();
            ;
        }
    }
    
    public static void main (String[] programArgs) {
        
        args = programArgs;
        final TestGame game = new TestGame();
        game.start();
    }
}
