package net.epoxide.tinker.util;

import java.util.Random;

public enum Rarity {
    
    COMMON("common", 1, 49, 0.49d),
    UNCOMMON("uncommon", 2, 35, 0.35d),
    RARE("rare", 3, 10, 0.1d),
    EPIC("epic", 4, 5, 0.05d),
    MYTHIC("mythic", 5, 1, 0.01d),
    KEY("key", 0, 0, 0d),
    VANITY("vanity", 0, 5, 0.05d),
    EXCLUSIVE("exclusive", 0, 0, 0d);
    
    /**
     * The name of the Rarity.
     */
    public final String name;
    
    /**
     * The tier value for the rarity.
     */
    public final int tier;
    
    /**
     * The weight for the rarity.
     */
    public final int weight;
    
    /**
     * The percent chance for the rarity.
     */
    public final double percentage;
    
    /**
     * Constructs a Rarity. Rarity is used to determine how rare something is. It should be
     * treated as a standard for outcomes, especially when working with weighted systems or
     * percent based outcomes.
     * 
     * @param name The name of the rarity.
     * @param tier The tier of the rarity.
     * @param weight The weight for the rarity in a tier system.
     * @param percentage The chance of this rarity happening out of 100.
     */
    private Rarity(String name, int tier, int weight, double percentage) {
        
        this.name = name;
        this.tier = tier;
        this.weight = weight;
        this.percentage = percentage;
    }
    
    /**
     * Calculates an outcome based on the percentage of the Rarity. The odds of this being true
     * are roughly equal to the percentage value of the Rarity.
     * 
     * @return boolean Whether or not the attempt was successful.
     */
    public boolean tryPercentage () {
        
        return Math.random() < this.percentage;
    }
    
    /**
     * Generates a random rarity, using a provided Random instance as a Random Number
     * Generator. For a rarity to be provided, {@link Random#nextDouble()} must return a value
     * equal to or greater than it's percentage.
     * 
     * @param rng An instance of Random to serve as the Random Number Generator.
     * @return Rarity A Rarity outcome based on the percentage of the rarity.
     */
    public static Rarity getRandomRarity (Random rng) {
        
        final double percent = rng.nextDouble();
        return (percent >= COMMON.percentage) ? COMMON : (percent >= UNCOMMON.percentage) ? UNCOMMON : (percent >= RARE.percentage) ? RARE : (percent >= EPIC.percentage) ? EPIC : MYTHIC;
    }
}
