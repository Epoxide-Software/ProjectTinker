package net.epoxide.tests;

import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.utils.Logger;

import net.epoxide.tinker.util.lang.I18n;

public class TestGame extends Game {

    public long start;
    public long finish;

    public static void main (String[] args) {

        final TestGame game = new TestGame();
        game.start();
    }

    @Override
    public void preInit () {

        this.testI18n();
    }

    @Override
    public void init () {

        this.start = System.nanoTime();

        final String translation = I18n.translate("test.language.translation");

        this.finish = System.nanoTime();

        Logger.info("The I18n test", "Passing: " + translation.equals("Tango Echo Sierra Tango"), "Time: " + (this.finish - this.start), "Translation: " + translation);
    }

    /**
     * Successful
     */
    private void testI18n() {

    }
}
