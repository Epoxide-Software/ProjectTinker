package net.epoxide.tinker.item;

import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.entity.EntityStat;
import net.epoxide.tinker.entity.StatModifier;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.util.RegistryName;
import net.epoxide.tinker.util.StatProvider;
import net.epoxide.tinker.util.lang.I18n;

public class Item implements StatProvider {
    
    public static final Item ERROR = registerItem(new Item("tinker:error"));
    
    public static final NamedRegistry<Item> REGISTRY = new NamedRegistry<>();
    
    /**
     * The ID that the item is registered under.
     */
    public final RegistryName ID;
    
    /**
     * Constructs an Item with the ID that the Item was registered under.
     *
     * @param id The ID that the Item is registered under.
     */
    public Item(String id) {
        
        this.ID = new RegistryName(id);
    }
    
    /**
     * Gets the name for the Item translated with the current language.
     * 
     * @return String the name for the Item.
     */
    public String getTranslatedName () {
        
        return I18n.translate("item." + this.ID.getDomain() + "." + this.ID.getName() + ".name");
    }
    
    /**
     * Gets an Item from the {@link #REGISTRY} which is associated with the name. If no item
     * exists, {@link #ERROR} will be returned.
     *
     * @param name The name of the Item you are looking for.
     * @return Item The Item associated with the specified name, or {@link #ERROR} if no item
     *         is found.
     */
    public static Item getItemByName (String name) {
        
        final Item item = REGISTRY.getValue(name);
        return item == null ? ERROR : item;
    }
    
    /**
     * Registers an Item with the {@link #REGISTRY} using the ID stored in the Item. This
     * should be used over directly accessing the REGISTRY.
     *
     * @param item The Item to register.
     * @return Item The same Item being registered. Provided to make life easier.
     */
    public static Item registerItem (Item item) {
        
        return REGISTRY.registerValue(item.ID, item);
    }

    @Override
    public StatModifier getStatModifier (Entity entity, EntityStat type) {
        
        return null;
    }
}