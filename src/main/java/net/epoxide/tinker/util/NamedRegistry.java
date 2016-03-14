package net.epoxide.tinker.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Maps;
import com.shc.silenceengine.utils.Logger;

public class NamedRegistry<V> implements Iterable<V> {
    
    /**
     * A Map which contains all of the names and values that have been registered.
     */
    protected final Map<String, V> registry = Maps.<String, V> newHashMap();
    
    /**
     * An array that holds a cache of all registered values. This cache should only be altered
     * internally.
     */
    private V[] valueCache;
    
    /**
     * Retrieves a value from the registry that is associated with the name passed.
     * 
     * @param name The name of the Object you want to retrieve.
     * @return V The value associated with the name. If none is found, expect null.
     */
    public V getValue (String name) {
        
        return this.registry.get(name);
    }
    
    // TODO Add domain support along with code to filter by domains.
    
    /**
     * Registers a new value with the registry. This will also reset the value cache. If the
     * name or value passed are null, the application will crash. Registering a value using a
     * name that already exists is discouraged.
     * 
     * @param name The name to register the value with.
     * @param value The value to register.
     */
    public void registerValue (String name, V value) {
        
        Validate.notNull(name);
        Validate.notNull(value);
        
        if (this.registry.containsKey(name))
            Logger.warn("A value with the name /'" + name + "/' has already been registered. Duplicate names are discouraged.");
            
        this.valueCache = null;
        this.registry.put(name, value);
    }
    
    /**
     * Retrieves a Set of the names that are currently in use.
     * 
     * @return Set<String> The Set of names.
     */
    public Set<String> getNames () {
        
        return Collections.<String> unmodifiableSet(this.registry.keySet());
    }
    
    /**
     * Retrieves the value cache for the registry. If the value cache is null, then it will be
     * automatically regenerated.
     * 
     * @return V[] An array of all the cached values.
     */
    @SuppressWarnings("unchecked")
    public V[] getValues () {
        
        if (this.valueCache == null)
            this.valueCache = (V[]) registry.values().toArray();
            
        return this.valueCache;
    }
    
    /**
     * Checks whether or not a name has already been used in the registry.
     * 
     * @param name The name to check for.
     * @return boolean True if the name is in use, false if it is not.
     */
    public boolean hasName (String name) {
        
        return this.registry.containsKey(name);
    }
    
    /**
     * Retrieves a value randomly from the cache of values. If the cache of values is empty,
     * then null will be returned.
     * 
     * @param random The Random instance to use for retrieving values.
     * @return V A random value from the value cache. null if the cache is empty.
     */
    public V getRandomValue (Random random) {
        
        return (V) this.getValues()[random.nextInt(this.valueCache.length)];
    }
    
    @Override
    public Iterator<V> iterator () {
        
        return this.registry.values().iterator();
    }
}