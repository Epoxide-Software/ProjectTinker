package net.epoxide.tinker.util.lang;

public enum LanguageType {
    
    ENGLISH("en_US"),;
    
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