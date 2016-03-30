package net.epoxide.tinker.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import com.shc.silenceengine.utils.Logger;

import org.apache.commons.lang3.Validate;

public class NamedRegistry<V> implements Iterable<V> {
    
    /**
     * An array that holds a cache of all registered values. This cache should only be altered
     * internally.
     */
    private Object[] valueCache;
    
    /**
     * A Map which contains all of the names and values that have been registered.
     */
    protected final BiMap<RegistryName, V> REGISTRY = HashBiMap.create();
    
    /**
     * Retrieves the name that was used to register the passed value.
     * 
     * @param value The value to get the name of.
     * @return RegistryName The name that the value was registered under. Can be null if no
     *         results.
     */
    public RegistryName getNameForValue (V value) {
        
        return this.REGISTRY.inverse().get(value);
    }
    
    /**
     * Retrieves a Set of the names that are currently in use.
     * 
     * @return Set<RegistryName> The Set of names.
     */
    public Set<RegistryName> getNames () {
        
        return this.REGISTRY.keySet();
    }
    
    /**
     * Retrieves a List of all registered names that use the passed domain.
     * 
     * @param domain The domain to get the names for.
     * @return List<RegistryName> A List of all registered names using the passed domain.
     */
    public List<RegistryName> getNames (String domain) {
        
        return this.getNames().stream().filter(name -> name.getDomain().equals(domain)).collect(Collectors.toList());
    }
    
    /**
     * Retrieves a value randomly from the cache of values. If the cache of values is empty,
     * then null will be returned.
     * 
     * @param random The Random instance to use for retrieving values.
     * @return V A random value from the value cache. null if the cache is empty.
     */
    @SuppressWarnings("unchecked")
    public V getRandomValue (Random random) {
        
        final Object[] values = this.getValueCache();
        return values.length == 0 ? null : (V) values[random.nextInt(values.length)];
    }
    
    /**
     * Retrieves a value randomly from the registered values that are registered using the
     * provided domain.
     * 
     * @param random The Random instance to use for retrieving values.
     * @param domain The domain to limit outcomes to.
     * @return V A random value from the value cache. null if the cache is empty.
     */
    public V getRandomValue (Random random, String domain) {
        
        final List<V> values = this.getValues(domain);
        return values.isEmpty() ? null : values.get(random.nextInt(values.size()));
    }
    
    /**
     * Retrieves a value from the registry that is associated with the name passed.
     * 
     * @param name The name of the Object you want to retrieve.
     * @return V The value associated with the name. If none are found, expect null.
     */
    public V getValue (RegistryName name) {
        
        return this.REGISTRY.get(name);
    }
    
    /**
     * Retrieves a value from the registry that is associated with a name.
     * 
     * @param name The name of the value you are looking for.
     * @return V The value associated with the name. If none are found, expect null.
     */
    public V getValue (String name) {
        
        return this.getValue(new RegistryName(name));
    }
    
    /**
     * Retrieves a value from the registry that is associated with the domain and name passed.
     * 
     * @param domain The domain of the value you are looking for.
     * @param name The name of the value you are looking for.
     * @return V The value associated with the name. If none are found, expect null.
     */
    public V getValue (String domain, String name) {
        
        return this.getValue(new RegistryName(domain, name));
    }
    
    /**
     * Retrieves the value cache for the registry. If the value cache is null, then it will be
     * automatically regenerated.
     * 
     * @return V[] An array of all the cached values.
     */
    public Object[] getValueCache () {
        
        if (this.valueCache == null)
            this.valueCache = this.REGISTRY.values().toArray(new Object[this.REGISTRY.size()]);
            
        return this.valueCache;
    }
    
    /**
     * Gets the values of the registry as a List.
     * 
     * @return List<V> An ArrayList containing all registered values.
     */
    public List<V> getValues () {
        
        return new ArrayList<>(this.REGISTRY.values());
    }
    
    /**
     * Retrieves the values that have been registered under the specified domain.
     * 
     * @param domain The domain to get values for.
     * @return List<V> A List of all values registered with the specified domain.
     */
    public List<V> getValues (String domain) {
        
        final List<V> values = new ArrayList<>();
        this.REGISTRY.entrySet().stream().filter(pair -> pair.getKey().getDomain().equals(domain)).forEach(pair -> values.add(pair.getValue()));
        return values;
    }
    
    /**
     * Checks whether or not a domain has been used to register something.
     * 
     * @param domain The domain to check for.
     * @return boolean True if the domain has been used.
     */
    public boolean hasDomain (String domain) {
        
        return this.REGISTRY.keySet().stream().filter(name -> name.getDomain().equals(domain)).findFirst() != null;
    }
    
    /**
     * Checks whether or not a name has already been used in the registry.
     * 
     * @param name The name to check for.
     * @return boolean True if the name is in use, false if it is not.
     */
    public boolean hasName (RegistryName name) {
        
        return this.REGISTRY.containsKey(name);
    }
    
    @Override
    public Iterator<V> iterator () {
        
        return this.REGISTRY.values().iterator();
    }
    
    /**
     *
     * Registers a new value with the registry. If the name or value passed are null, the
     * application will crash. This will also cause the {@link #valueCache} to be reset when it
     * is next accessed. Registering with duplicate names is discouraged, and may have bad
     * effects.
     * 
     * @param name The name to register the value with.
     * @param value The value to register.
     * @return V The value being registered.
     */
    public V registerValue (RegistryName name, V value) {
        
        Validate.notNull(name);
        Validate.notNull(value);
        
        if (this.REGISTRY.containsKey(name))
            Logger.warn("A value with the name /'" + name + "/' has already been registered. Duplicate names are discouraged.");
            
        this.valueCache = null;
        this.REGISTRY.put(name, value);
        return value;
    }
    
    /**
     * Registers a new value with the registry with a domain specified. If the name or value
     * passed are null, the application will crash. This will also cause the
     * {@link #valueCache} to be reset when it is next accessed. Registering with duplicate
     * names is discouraged, and may have bad effects.
     * 
     * @param domain The domain for the entry. This is like a category to register the value
     *        under.
     * @param name The name to register the value with.
     * @param value The value to register.
     * @return V The value being registered.
     */
    public V registerValue (String domain, String name, V value) {
        
        this.registerValue(new RegistryName(domain, name), value);
        return value;
    }
    
    /**
     * Registers a new value with the registry with the name specified. If the name or value
     * passed are null, the application will crash. This will also cause the
     * {@link #valueCache} to be reset when it is next accessed. Registering with duplicate
     * names is discouraged, and may have bad effects.
     * 
     * @param name The name to register the value with.
     * @param value The value to register.
     * @return V The value being registered.
     */
    public V registerValue (String name, V value) {
        
        this.registerValue(new RegistryName(name), value);
        return value;
    }
}