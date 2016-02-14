package net.epoxide.tinker.entity.component;

import net.epoxide.tinker.entity.Entity;

public class EntityComponent {
    
    /**
     * Called whenever the entity enters a world. This is typically limited to when the mob
     * initially spawns.
     */
    public void onJoinWorld (Entity entity) {
    
    }
    
    /**
     * Called when the entity is killed or removed from the world.
     */
    public void onKilled (Entity entity) {
    
    }
    
    /**
     * Called on every update tick.
     */
    public void onUpdate (Entity entity) {
    
    }
    
    /**
     * Called when the entity is being loaded from disk.
     */
    public void readData (Entity entity) {
    
    }
    
    /**
     * Called when the entity is being written to data. Things saved here can be loaded during
     * readData.
     */
    public void writeData (Entity entity) {
    
    }
}
