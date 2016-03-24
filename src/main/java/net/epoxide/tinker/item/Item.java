package net.epoxide.tinker.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.entity.StatModifier;
import net.epoxide.tinker.util.ItemObject;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.world.TileMap;

public class Item {
    
    public static final NamedRegistry<Item> REGISTRY = new NamedRegistry<Item>();
    
    /**
     * Gets a MultiMap of StatModifiers. All StatModifiers returned will be applied to the
     * entity that is using the item. This can be used to allow for things like additional
     * damage or decreased movement speed.
     * 
     * @param item The ItemObject being used.
     * @param map The TileMap where this is happening.
     * @param user The entity using the item.
     * @return Multimap<String, StatModifier> A Multimap of StatModifiers for the Item.
     */
    public Multimap<String, StatModifier> getStatModifiers (ItemObject item, TileMap map, Entity user) {
        
        return HashMultimap.<String, StatModifier> create();
    }
}