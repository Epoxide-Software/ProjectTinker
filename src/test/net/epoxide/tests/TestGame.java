package net.epoxide.tests;

import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.utils.Logger;

import net.epoxide.tinker.util.lang.I18n;
import net.epoxide.tinker.util.lang.LanguageType;

public class TestGame extends Game {
    
    public long start;
    public long finish;
    
    public static void main (String[] args) {
        
        final TestGame game = new TestGame();
        game.start();
    }
    
    @Override
    public void init () {
        
        this.testI18n();
        this.testI18nGerman();
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
}
