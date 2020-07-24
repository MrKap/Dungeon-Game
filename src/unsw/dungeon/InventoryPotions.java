package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory for potions
 * 
 * Store list of invincibility potions
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public class InventoryPotions {

	private List<Invincibility> invincPotion;
	
	/**
	 * InventoryPotions Constructor
	 */
	public InventoryPotions() {
		this.invincPotion = new ArrayList<Invincibility>();
	}
	
	/**
	 * check if there is a invincibility potion in the inventory
	 * @return true is inventory is not empty
	 */
	public boolean isPlayerInvicible() {
		if (invincPotion.size()==0) {
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * check the potion time left
	 * @return the time remaining of the potion
	 */
	public int getMovesLeft() {
		return invincPotion.get(0).getTimeRemaining();
	}
	
	/**
	 * add potion to the inventory
	 * @param invincibility potion
	 */
	public void addPotion(Invincibility invincibility) {
		invincPotion.add(invincibility);
	}
	
	/**
	 * remove the potion and set the visibility to be false on map
	 */
	public void removeInvincibility() {
		invincPotion.get(0).setOnMap(false);
		invincPotion.remove(0);
	}
	
	/**
	 * reduce the invincibility potion time
	 */
	public void decrementInvincibility() {
		invincPotion.get(0).decrementInvincibleTime();
		
		if(invincPotion.get(0).getTimeRemaining()==0) {
			this.removeInvincibility();
		}
	}
	
	
}
