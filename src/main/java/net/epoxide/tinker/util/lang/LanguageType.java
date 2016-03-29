package net.epoxide.tinker.util.lang;

public enum LanguageType {
    
    ENGLISH("en_US"),
    CANADIAN("en_CA"),
    GERMAN("de_DE"),
    PIRATE("en_PT"),
    SPANISH_ES("es_ES"),
    SPANISH_MX("es_MC"),
    FRENCH("fr_FR"),
    JAPANESE("ja_JP"),
    POLISH("pl_PL"),
    PORTUGUESE("pt_PT"),
    SWEDISH("sv_SE"),
    CHINESE("zh_CN");
    
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