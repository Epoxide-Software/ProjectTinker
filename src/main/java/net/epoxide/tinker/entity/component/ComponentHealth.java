package net.epoxide.tinker.entity.component;

import net.epoxide.tinker.entity.Entity;

public class ComponentHealth extends EntityComponent {
    
    /**
     * The most health that the entity should ever have. It will be possible for entities to
     * have more health than the maximum in unnatural circumstances.
     */
    private int maxHealth;
    
    /**
     * The amount of health that the entity is currently at.
     */
    private int health;
    
    /**
     * A flag used to determine whether or not the entity can lose health.
     */
    private boolean isInvulnerable;
    
    /**
     * Provides the entity with health properties. This will allow the entity to be hurt, and
     * killed through damage. This constructor sets the mob to full health.
     * 
     * @param maxHealth The maximum amount of health that the entity should have.
     */
    public ComponentHealth(int maxHealth) {
        
        this(maxHealth, maxHealth);
    }
    
    /**
     * Provides the entity with health properties. This will allow the entity to be hurt, and
     * killed through damage.
     * 
     * @param maxHealth The maximum amount of health that the entity should have.
     * @param startingHealth The amount of health to start the entity with.
     */
    public ComponentHealth(int maxHealth, int startingHealth) {
        
        this.maxHealth = maxHealth;
        this.health = startingHealth;
        this.isInvulnerable = false;
    }
    
    /**
     * Gets the maximum amount of health that the entity should have under normal
     * circumstances.
     * 
     * @return int The maximum amount of health the entity should have.
     */
    public int getMaxHealth () {
        
        return this.maxHealth;
    }
    
    /**
     * Sets the maximum health of the entity. The max health must be at least one.
     * 
     * @param maxHealth The new maximum health for the entity.
     */
    public void setMaxHealth (int maxHealth) {
        
        this.maxHealth = Math.max(maxHealth, 1);
    }
    
    /**
     * Gets the current health of the entity.
     * 
     * @return int The current health of the entity.
     */
    public int getCurrentHealth () {
        
        return this.health;
    }
    
    /**
     * Heals the entity by the specified amount. Will never heal more than the max health.
     * 
     * @param amount The amount to heal the entity by.
     */
    public void heal (int amount) {
        
        this.health = Math.min(this.health + amount, this.maxHealth);
    }
    
    /**
     * Harms the entity by the specified amount. Will never harm the entity below 0. If the
     * entity is invulnerable they will receive no damage.
     * 
     * @param amount The amount to damage the entity by.
     */
    public void harm (int amount) {
        
        if (!this.isInvulnerable)
            this.health = Math.max(this.health - amount, 0);
    }
    
    /**
     * Calculates the percentage of remaining health that the mob has.
     * 
     * @return double The percentage as a double.
     */
    // TODO Round this down to show up to 1 decimal place, if that decimal is not 0.
    public double getHealthPercentage () {
        
        return (double) this.health / (double) this.maxHealth;
    }
    
    /**
     * Checks if an entity should be immune to damage.
     * 
     * @return boolean Whether or not the entity can not take damage.
     */
    public boolean isInvulnerable () {
        
        return this.isInvulnerable;
    }
    
    /**
     * Updates the vulnerability of the entity.
     * 
     * @param vulnerability If true, the entity will be invulnerable.
     */
    public void setVulnerability (boolean vulnerability) {
        
        this.isInvulnerable = vulnerability;
    }
    
    @Override
    public void onUpdate (Entity entity) {
        
        if (this.health <= 0)
            entity.setRemoveStatus(true);
    }
}