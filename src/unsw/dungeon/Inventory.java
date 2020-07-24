package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Inventory
 * 
 * Inventory stores all collectables
 * collectables includes: potions, keys, sword, treasure
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public class Inventory {

	private InventoryTreasure treasureInv;
	private InventorySword swordInv;
	private InventoryKeys keyInv;
	private InventoryPotions invincPotionInv;
	private StringProperty treasureString = new SimpleStringProperty();
	private BooleanProperty showTreasureCount = new SimpleBooleanProperty(false);

	/**
	 * Inventory Constructor
	 * create new inventory for each items
	 */
	public Inventory() {
		treasureInv = new InventoryTreasure();
		swordInv = new InventorySword();
		keyInv = new InventoryKeys();
		invincPotionInv = new InventoryPotions();
		
	}
	
	/**
	 * Pickup sword method
	 * @param sword
	 * @return true if sword is picked up
	 */
	public boolean pickupSword(Sword sword) {
		if(swordInv.getSwordList().isEmpty()) {
			swordInv.addSword(sword);
			return true;
		}
		
		return false;
		
	}

	/**
	 * pickup inventory potion and store into potion inventory 
	 * @param invincibility
	 */
	public void pickupInvPotion(Invincibility invincibility) {
		invincPotionInv.addPotion(invincibility);
		
	}
	
	/**
	 *  check if the player is carrying a sword
	 * @return true is player is carrying a sword
	 */
	public boolean isSwordWielded() {
		return swordInv.isSwordWielded();
	}
	
	/**
	 * pickup a treasure and store in treasure inventory
	 * @param treasure
	 */
	public void pickupTreasure(Treasure treasure) {
		treasureInv.addTreasure(treasure);
		treasureString.setValue(String.valueOf(treasureInv.treasureCount()));
		if(treasureInv.treasureCount() != 0 ) showTreasureCount.setValue(true);
	}
	
	/**
	 * pickup key and store into inventory treasure
	 * @param key
	 */
	public void pickupKey(Key key) {
		keyInv.addKey(key);
	}

	/**
	 * remove sword from inventory
	 */
	public void breakSword() {
		swordInv.destroySword();
	}

	/**
	 * Check if the player is carrying a key
	 * @return true if a key is in the key inventory
	 */
	public boolean isCarryingKey() {
		
		if(keyInv.isEmpty()) {return false;}
		return true;
	}
	
	/**
	 * check if the player is carrying key with the id given
	 * @param id key id
	 * @return true if the key in the key inventory is matching with the id given
	 */
	public boolean isCarryingCorrectKey(int id) {
		return keyInv.hasCorrectKey(id);
	}
	
	/**
	 * check if the player has invincibility potion in potion inventory
	 * @return
	 */
	public boolean isPlayerInvincible() {
		return invincPotionInv.isPlayerInvicible();
	}
	
	/**
	 * use sword, reduce the sword remaining hits
	 */
	public void useSword() {
		swordInv.getSwordList().get(0).hitAction();
		System.out.println("use sword");
		if(swordInv.hitsLeft()==0) {
			swordInv.destroySword();
		}
	}
	
	/**
	 * check how many the hits of sword left
	 * @return
	 */
	public int swordHits() {
		return swordInv.hitsLeft();
	}
	
	/**
	 * return how many sword count
	 * @return
	 */
	public int swordCount() {
		return swordInv.getSwordList().size();
		
	}

	/**
	 * use key , remove key in inventory
	 */
	public void useKey() {
		keyInv.removeKey();
	}
	
	/**
	 * check how many treasure left in the inventory
	 * @return
	 */
	public int getTreasureCount() {
		int n = treasureInv.treasureCount();
		
		return n;
	}
	
	/**
	 * reduce the invincibility potion timeleft
	 */
	public void decrementInvinc() {
		invincPotionInv.decrementInvincibility();
	}

	/**
	 * number of items that inventory can holds
	 * @return inventory type
	 */
	public int invCount() {
		
		return 4;
	}

	/**
	 * string property of current count of treasure in the inventory
	 * @return count of treasure as string property
	 */
	public StringProperty treasureLeft() {
		return treasureString;
	}

	/**
	 * BooleanProperty of treasure count to be use in frontend
	 * @return
	 */
	public BooleanProperty treasureCountVisibility() {
		// TODO Auto-generated method stub
		return showTreasureCount;
	}
	
	// GETTERS AND SETTERS
	public InventoryTreasure getTreasureInv() {
		return treasureInv;
	}
	
	public InventorySword getSwordInv() {
		return swordInv;
	}
	
	public InventoryKeys getKeyInv() {
		return keyInv;
	}
}
