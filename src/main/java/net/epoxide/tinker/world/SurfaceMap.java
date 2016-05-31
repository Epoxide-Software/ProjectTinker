package net.epoxide.tinker.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.darkhax.ess.DataCompound;

import net.epoxide.tinker.entity.Entity;
import net.epoxide.tinker.tile.Tile;
import net.epoxide.tinker.util.Displayable;
import net.epoxide.tinker.util.NamedRegistry;
import net.epoxide.tinker.util.Persistent;
import net.epoxide.tinker.util.RegistryName;
import net.epoxide.tinker.util.lang.I18n;

public class SurfaceMap extends TileMap implements Persistent, Displayable {
    
    /**
     * A registry for maps. TileMaps do not need to be registered here for them to work,
     * however static locations that can be warped to must be registered here.
     */
    public static final NamedRegistry<SurfaceMap> REGISTRY = new NamedRegistry<>();
    
    /**
     * The ID that the map is registered with.
     */
    public final RegistryName ID;
    
    public SurfaceMap(int width, int height, RegistryName id) {
        
        super(width, height, "");
        this.ID = id;
    }
    
    @Override
    public String getTranslatedName () {
        
        return I18n.translate("map." + this.ID.getDomain() + "." + this.ID.getName() + ".name");
    }
    
    @Override
    public void readData (DataCompound tag) {
        
        this.name = tag.getString("MapName");
        this.width = tag.getInt("MapWidth");
        this.height = tag.getInt("MapHeight");
        
        final String[] tileIDs = tag.getStringArray("TileIDs");
        @SuppressWarnings("unchecked")
        final List<DataCompound> tileData = (List<DataCompound>) tag.getList("TileData");
        
        for (int x = 0; x < this.width; x++)
            for (int y = 0; y < this.height; y++) {
                
                final int index = y % this.height + x * this.height;
                this.tileMap[x][y] = Tile.getTileByName(tileIDs[index]);
                this.tileData[x][y] = tileData.get(index);
            }
            
        final List<DataCompound> entities = (List<DataCompound>) tag.getList("Entities");
        
        for (final DataCompound entityTag : entities) {
            
            final DataCompound entityData = entityTag;
            final Entity entity = Entity.createInstance(Entity.REGISTRY.getValue(entityData.getString("EntityID")));
            
            if (entity != null) {
                
                entity.readData(entityData);
                entity.setEntityData(entityData);
                this.addEntity(entity);
            }
        }
    }
    
    @Override
    public void setName (String name) {
    
    }
    
    @Override
    public DataCompound writeData (DataCompound tag) {
        
        tag.setValue("MapName", this.name);
        tag.setValue("MapWidth", this.width);
        tag.setValue("MapHeight", this.height);
        
        final List<String> tileIDs = new ArrayList<>();
        final List<DataCompound> tileData = new ArrayList<>();
        
        for (int x = 0; x < this.width; x++)
            for (int y = 0; y < this.height; y++) {
                
                tileIDs.add(this.getTileUnsafely(x, y).ID.toString());
                tileData.add(this.getTileDataUnsafely(x, y));
            }
            
        tag.setValue("TileIDs", tileIDs.toArray(new String[this.width * this.height]));
        tag.setValue("TileData", (Serializable) tileData);
        
        final List<DataCompound> entityData = new ArrayList<>();
        this.entityList.forEach(entity -> entityData.add(entity.writeData(new DataCompound())));
        tag.setValue("Entities", (Serializable) entityData);
        
        return tag;
    }
}
