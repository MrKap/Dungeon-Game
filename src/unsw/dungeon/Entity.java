package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {
	
	/**
	 * IntegerProperty is used so that changes to the entities position can be externally observed.
	 */
    private IntegerProperty x, y;
    
    /**
     * the BoolenProperty for the Entity to shown or not on the map
     * onMap value is true if the current Entity is still on the map, false otherwise
     */
    private BooleanProperty onMap = new SimpleBooleanProperty(true);
    
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    /**
     * return the integer property of x coordinate of current Entity
     * @return integer property of x coordinate
     */
    public IntegerProperty x() {
        return x;
    }
    
    /**
     * return the integer property of x coordinate of current Entity
     * @return integer property of x coordinate
     */
    public IntegerProperty y() {
        return y;
    }

    /**
     * get the y coodinate of current Entity
     * @return y coodinate
     */
    public int getY() {
        return y().get();
    }

    /**
     * get the x coodinate of current Entity
     * @return x coodinate
     */
    public int getX() {
        return x().get();
    }
    
    /**
     * return the boolean property of onMap
     * @return onMap boolean Property
     */
    public BooleanProperty getOnMap() {
    	return onMap;
    }
    
    /**
     * set the onMap value
     * @param b true if the Entity is still on map, false otherwise
     * @see onMap
     */
    public void setOnMap(boolean b) {
    	this.onMap.set(b);
    }

    /**
     * check if the player can collides to the current Entity
     * @param player
     * @return true if the player can collide with the Entity
     */
    public abstract boolean validPlayerCollision(Player player);
    
    /**
     * check if the enemy can collides with the current Entity
     * @return true if the enemy can collide with the Entity, false otherwise
     */
    public abstract boolean validEnemyCollision();
    
    /**
     * check if the boulder can collide to the current Entity
     * @return true if the boulder can collide with the Entity, false otherwise
     */
    public abstract boolean validBoulderCollision();

	
	
    
    
}
