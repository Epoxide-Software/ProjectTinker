package net.epoxide.tinker.util;

public interface Displayable {
    
    /**
     * Gets the name of the object, translated into the language that is currently selected.
     * This can be done by passing a translation key to I18n.
     * 
     * @return String The translated name.
     */
    public String getTranslatedName ();
}
