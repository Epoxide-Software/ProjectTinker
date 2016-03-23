package net.epoxide.tinker.util;

import org.apache.commons.lang.StringUtils;

public class RegistryName {
    
    /**
     * The domain used for the RegistryName.
     */
    private final String domain;
    
    /**
     * The name used for the registry.
     */
    private final String name;
    
    /**
     * Creates a new RegistryName using an array of String. The first String is the domain and
     * the second is the name. The domain will default to tinker if empty.
     * 
     * @param nameParts An array of String to pull domain and name from.
     */
    private RegistryName(String... nameParts) {
        
        this.domain = StringUtils.isEmpty(nameParts[0]) ? "tinker" : nameParts[0].toLowerCase();
        this.name = nameParts[1];
    }
    
    /**
     * Creates a new RegistryName using only one name value. The name will attempt to be split
     * using {@link #splitNameSafely(String)}.
     * 
     * @param name The name to be split.
     */
    public RegistryName(String name) {
        
        this(splitNameSafely(name));
    }
    
    /**
     * Creates a new RegistryName using a domain and name value.
     * 
     * @param domain The domain for the registry name to use.
     * @param name The name for the registry.
     */
    public RegistryName(String domain, String name) {
        
        this(new String[] { domain, name });
    }
    
    /**
     * Safely splits a name into two pieces. Used to pull a domain from the name. If no domain
     * is specified, it will default to tinker. A domain is considered to be the first
     * substring in front of a colon. The name is considered the second substring after that.
     * If there is not exactly one colon, the domain will default to tinker and the name will
     * not be split.
     * 
     * @param name The name to split.
     * @return String[] A new RegistryName pair where the first String is domain and second is
     *         name.
     */
    private static String[] splitNameSafely (String name) {
        
        String[] defaultNames = new String[] { "tinker", name };
        String[] splitNames = name.split(":");
        
        if (splitNames.length == 2)
            defaultNames[0] = splitNames[0];
            
        return defaultNames;
    }
    
    /**
     * Gets the domain for the registry name.
     * 
     * @return String The domain for this name.
     */
    public String getDomain () {
        
        return this.domain;
    }
    
    /**
     * Gets the name for the registry name.
     * 
     * @return String the registry name.
     */
    public String getName () {
        
        return this.name;
    }
    
    @Override
    public boolean equals (Object obj) {
        
        if (obj instanceof RegistryName) {
            
            RegistryName regName = (RegistryName) obj;
            return (this.domain.equals(regName.domain) && this.name.equals(regName.name));
        }
        
        return super.equals(obj);
    }
    
    @Override
    public String toString () {
        
        return this.domain + ":" + this.name;
    }
}