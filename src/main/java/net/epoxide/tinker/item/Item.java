package net.epoxide.tinker.item;

import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.entity.StatModifier;
import net.epoxide.tinker.util.ItemObject;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.world.TileMap;

public class Item {
    
    public static final NamedRegistry<Item> REGISTRY = new NamedRegistry<Item>();
    
    /**
     * Gets a StatModifier for the Item. This can allow the Item to manipulate entity stats,
     * such as increasing damage, or lowering speed.
     * 
     * @param item The ItemObject being used.
     * @param map The TileMap where this is happening.
     * @param user The entity using the item.
     * @param type The name of the stat to provide a modifier for.
     * @return StatModifier A StatModifier object which represents the modifier effects of the
     *         Item. Return null to have no effect.
     */
    public StatModifier getStatModifier (ItemObject item, TileMap map, Entity user, String type) {
        
        return null;
    }
}