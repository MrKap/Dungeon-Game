package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory for treasure
 * 
 * Store list of treasures
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public class InventoryTreasure {
	
	private List<Treasure> treasures;
	
	/**
	 * Constructor of treasure inventory
	 */
	public InventoryTreasure() {
		this.treasures = new ArrayList<Treasure>();
	}
	
	/**
	 * add treasure to inventory
	 * @param treasure
	 */
	public void addTreasure(Treasure treasure) {
		treasures.add(treasure);
	}
	
	/**
	 * check how many treasure in the inventory
	 * @return number of treasure in the inventory
	 */ 
	public int treasureCount() {
		return treasures.size();
	}
	
}
