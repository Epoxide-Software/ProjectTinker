package net.epoxide.tinker.client.input.keys;

import net.epoxide.tinker.TinkerGame;
import net.epoxide.tinker.client.input.KeyBind;
import net.epoxide.tinker.entity.living.EntityPlayer;

public class MovementKey extends KeyBind {
    
    /**
     * The type that the button represents. 0 is backward, 1 is forward, 2 is left, 3 is right.
     */
    public final int TYPE;
    
    /**
     * Constructs a new MovementKey. This is used to handle player movement.
     * 
     * @param key The numeric value for the key.
     * @param type The key type. 0 is backward, 1 is forward, 2 is left, 3 is right.
     */
    public MovementKey(String id, int key, int type) {
        
        super(id, key, true);
        this.TYPE = type;
        this.setRepeatable(true);
    }
    
    @Override
    public void onKeyPressed (float delta) {
        
        final EntityPlayer player = TinkerGame.entityPlayer;
        switch (this.TYPE) {
            
            case 0:
                player.setYPos(player.getYPos() + delta);
                break;
                
            case 1:
                player.setYPos(player.getYPos() - delta);
                break;
                
            case 2:
                player.setXPos(player.getXPos() + delta);
                break;
                
            case 3:
                player.setXPos(player.getXPos() - delta);
                break;
        }
    }
}