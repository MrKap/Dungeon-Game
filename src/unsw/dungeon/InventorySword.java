package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory for sword
 * 
 * Store list of swords
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public class InventorySword {

	private List<Sword> sword;
	
	/**
	 * constructor for InventorySword
	 */
	public InventorySword() {
		this.sword = new ArrayList<Sword>();
	}
	
	/**
	 * remove sword from inventory and set the sword visibility to false
	 */
	public void destroySword() {
		sword.get(0).setOnMap(false);
		sword.remove(0);
	}
	
	/**
	 *  reduce the remaining sword hit
	 */
	public void decrementHit() {
		sword.get(0).hitAction();
		
	}
	
	/**
	 * check if there is a sword in the inventory
	 * @return true if there is a sword, false otherwise
	 */
	public boolean isSwordWielded() {
		if(sword.size()==0) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * check how many hits left for the sword
	 * @return number of hits left
	 */
	public int hitsLeft() {
		return sword.get(0).getRemainingHits();
	}
	
	/**
	 * add sword to the inventory 
	 * @param s sword
	 */
	public void addSword(Sword s) {
		sword.add(s);
	}
	
	/**
	 * get the sword
	 * @return
	 */
	public List<Sword>getSwordList(){
		return sword;
	}
	
}
