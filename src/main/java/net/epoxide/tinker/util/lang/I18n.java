package net.epoxide.tinker.util.lang;

import java.util.HashMap;
import java.util.Map;

public class I18n {
    
    /**
     * The current language used by the client.
     */
    private static LanguageType currentLanguage = LanguageType.ENGLISH;
    
    /**
     * A cache of all the loaded languages.
     */
    private static Map<LanguageType, LanguageMap> languageCache = new HashMap<LanguageType, LanguageMap>();
    
    /**
     * A map of translations loaded for the current language.
     */
    private static LanguageMap translations;
    
    /**
     * The English translation. This is used as a fallback for when another language does not
     * have a translation.
     */
    public static final LanguageMap ENGLISH;
    
    static {
        
        ENGLISH = new LanguageMap(LanguageType.ENGLISH);
        ENGLISH.loadLangFile("tinker");
        translations = ENGLISH;
        languageCache.put(LanguageType.ENGLISH, ENGLISH);
    }
    
    /**
     * Checks if a translation key exists in the current language.
     * 
     * @param key The translation key to look for.
     * @return boolean Whether or not the translation key exists.
     */
    public static boolean canTranslate (String key) {
        
        return translations.canTranslation(key);
    }
    
    /**
     * Gets the current language that is being translated for.
     * 
     * @return LanguageType The language that is being translated for.
     */
    public static LanguageType getCurrentLanguage () {
        
        return currentLanguage;
    }
    
    /**
     * Gets the LanguageMap for the current language. This contains a map of all translations
     * loaded for that language.
     * 
     * @return LanguageMap A map of all loaded translations for the current language
     *         localization.
     */
    public static LanguageMap getTranslations () {
        
        return translations;
    }
    
    /**
     * Loads a language file into the current language map. The name of the file is determined
     * by the domain that the language file is in. This allows for things like a mod to load
     * their own language file and have it added to the language map.
     * 
     * @param domain The domain for the language file. The domain is the directory after the
     *        assets one. For example 'assets/DOMAIN/lang/en_US.lang'.
     */
    public static void loadLangFile (String domain) {
        
        translations.loadLangFile(domain);
    }
    
    /**
     * Sets the current language to a new one. This will try to load the language from the
     * {@link #languageCache}. If the language does not exist in the language cache, a new
     * language map will be created and added to the cache. The new language map will also be
     * populated with the default language file for it. This will update
     * {@link #currentLanguage} and {@link #translations}
     * 
     * @param type The language to translate the game to.
     */
    public static void setCurrentLanguage (LanguageType type) {
        
        LanguageMap language = languageCache.get(type);
        
        if (language == null) {
            
            language = new LanguageMap(type);
            languageCache.put(type, language);
            language.loadLangFile("tinker");
        }
        
        currentLanguage = type;
        translations = language;
    }
    
    /**
     * Attempts to translate a localization key into the current language type. If no
     * translation exists, the key will be sent back out.
     * 
     * @param key The translation key to translate for.
     * @return String The translated text.
     */
    public static String translate (String key) {
        
        return translations.translate(key);
    }
}