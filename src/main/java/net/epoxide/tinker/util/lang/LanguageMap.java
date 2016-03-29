package net.epoxide.tinker.util.lang;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.shc.silenceengine.utils.Logger;

public class LanguageMap {

    /**
     * The language that is being translated for.
     */
    private final LanguageType type;;

    /**
     * A map of all the translations loaded.
     */
    private final Map<String, String> translations;

    /**
     * Constructs a new language map for a specific LanguageType. Preferably only one map
     * should exist per language. If you want to inject your own language file into the
     * language map you can use {@link #loadLangFile(String)} to add it.
     *
     * @param type The language that should be translated for.
     */
    public LanguageMap(LanguageType type) {

        this.type = type;
        this.translations = new HashMap<String, String>();
    }

    /**
     * Gets the language that the map is translating for.
     *
     * @return LanguageType The language that is being translated for.
     */
    public LanguageType getType () {

        return this.type;
    }

    /**
     * Attempts to translate a localization key into the current language type. If no
     * translation exists, the key will be sent back out. Will fall back to English if a
     * language other than english can not translate something.
     *
     * @param key The translation key to translate for.
     * @return String The translated text.
     */
    public String translate (String key) {

        final String translation = this.translations.get(key);
        return translation == null || translation.isEmpty() ? this.type != LanguageType.ENGLISH ? I18n.ENGLISH.translate(key) : key : translation;
    }

    /**
     * Checks if a translation key exists in the LanguageMap.
     *
     * @param key The translation key to look for.
     * @return boolean Whether or not the translation key exists.
     */
    public boolean canTranslation (String key) {

        return this.translations.containsKey(key);
    }

    /**
     * Loads a language file into the language map. The name of the file is determined by the
     * domain that the language file is in. This allows for things like a mod to load their own
     * language file and have it added to the language map.
     *
     * @param domain The domain for the language file. The domain is the directory after the
     *        assets one. For example 'assets/DOMAIN/lang/en_US.lang'.
     */
    public void loadLangFile (String domain) {

        final String path = "assets/" + domain + "/lang/" + this.type.getLanguage() + ".lang";
        try (Stream<String> lines = Files.lines(Paths.get(path))) {

            lines.forEach(line -> {

                final String[] translationPair = line.split("=");
                this.translations.put(translationPair[0], translationPair[1]);
            });
        }

        catch (final IOException exception) {

            Logger.error("There was a problem reading " + path);
            exception.printStackTrace();
        }
    }
}
