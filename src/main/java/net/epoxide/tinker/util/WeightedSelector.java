package net.epoxide.tinker.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightedSelector<T> {
    
    /**
     * A random object used only for weighted selectors.
     */
    private static final Random RANDOM = new Random();
    
    /**
     * The list of entries held by the selector.
     */
    private final List<WeightedEntry<T>> entries = new ArrayList<WeightedEntry<T>>();
    
    /**
     * The total weight of all entries.
     */
    private int total = 0;
    
    /**
     * Whether or not entries should be removed when they are selected.
     */
    public boolean autoRemove = false;
    
    /**
     * Adds an entry to the entry list. If the entry is added successfully, the total will
     * automatically update.
     * 
     * @param entry The entry to add to the entry pool.
     * @return Whether or not the entry was added successfully.
     */
    public boolean addEntry (WeightedEntry<T> entry) {
        
        final boolean added = this.entries.add(entry);
        
        if (added)
            this.total += entry.getWeight();
            
        return added;
    }
    
    /**
     * Removes an entry from the entry list. If the entry is removed successfully, the total
     * will automatically update.
     * 
     * @param entry The entry to remove from the entry pool.
     * @return Whether or not the entry was removed successfully.
     */
    public boolean removeEntry (WeightedEntry<T> entry) {
        
        final boolean removed = this.entries.remove(entry);
        
        if (removed)
            this.total -= entry.getWeight();
            
        return removed;
    }
    
    /**
     * Provides access to the list of entries. If you add or remove anything, make sure to call
     * {@link #updateTotal()} when you are done. It is critical that the total does not get
     * messed up!
     * 
     * @return A list of weighted entries.
     */
    public List<WeightedEntry<T>> getEntries () {
        
        return this.entries;
    }
    
    /**
     * Checks if the selector automatically removes an entry when it is selected.
     * 
     * @return Whether or not the selector automatically removes entries when they are
     *         selected.
     */
    public boolean willAutoRemove () {
        
        return this.autoRemove;
    }
    
    /**
     * Allows the auto remove setting to be changed.
     * 
     * @param autoRemove Whether or not the selector should automatically remove entries when
     *        they are selected.
     * @return The WeightedSelector being changed. Allows for builder convenience.
     */
    public WeightedSelector<T> setAutoDelete (boolean autoRemove) {
        
        this.autoRemove = autoRemove;
        return this;
    }
    
    /**
     * Randomly selects an entry from the list. Makes use of the weighted values to give values
     * with higher weight a better likelihood.
     * 
     * @return The weighted entry that was selected.
     */
    public WeightedEntry<T> getRandomEntry () {
        
        final int selected = RANDOM.nextInt(this.total);
        int current = 0;
        
        for (final WeightedEntry<T> entry : this.entries) {
            
            current += entry.weight;
            
            if (selected < current) {
                
                if (this.autoRemove)
                    this.entries.remove(entry);
                    
                return entry;
            }
        }
        
        return null;
    }
    
    /**
     * Updates the total weight value for the selector. This should be caused any time the list
     * is altered.
     * 
     * @return The new total weight.
     */
    public int updateTotal () {
        
        int total = 0;
        
        for (final WeightedEntry<T> entry : this.entries)
            total += entry.getWeight();
            
        return total;
    }
    
    public static class WeightedEntry<T> {
        
        /**
         * The outcome being represented by the entry.
         */
        private final T entry;
        
        /**
         * The weight of the entry.
         */
        private final int weight;
        
        /**
         * Constructs a new WeightedEntry using a basic entry and weight parameter.
         * 
         * @param entry The outcome to represent with the entry.
         * @param weight The weight of the entry. Entries with a higher weight have a higher
         *        likelihood of being selected.
         */
        public WeightedEntry(T entry, int weight) {
            
            this.entry = entry;
            this.weight = weight;
        }
        
        /**
         * Gets the outcome represented by the entry.
         * 
         * @return The outcome represented by the entry.
         */
        public T getEntry () {
            
            return this.entry;
        }
        
        /**
         * Gets the weight of the entry.
         * 
         * @return The weight of the entry.
         */
        public int getWeight () {
            
            return this.weight;
        }
    }
}