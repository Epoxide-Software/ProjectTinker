package net.epoxide.tinker.util.lang;

public enum LanguageType {
    
    CANADIAN("en_CA"),
    CHINESE("zh_CN"),
    ENGLISH("en_US"),
    FRENCH("fr_FR"),
    GERMAN("de_DE"),
    JAPANESE("ja_JP"),
    PIRATE("en_PT"),
    POLISH("pl_PL"),
    PORTUGUESE("pt_PT"),
    SPANISH_ES("es_ES"),
    SPANISH_MX("es_MC"),
    SWEDISH("sv_SE");
    
    /**
     * The language identifier. This is used to determine which language is being represented,
     * as well and the .lang file name.
     */
    private final String language;
    
    /**
     * Constructs a new LanguageType.
     * 
     * @param language The language identifier for the language.
     */
    LanguageType(String language) {
        
        this.language = language;
    }
    
    /**
     * Gets the language identifier.
     * 
     * @return String the language identifier.
     */
    public String getLanguage () {
        
        return this.language;
    }
}