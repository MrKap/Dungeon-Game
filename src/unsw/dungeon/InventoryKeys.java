package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
/**
 * Inventory for key
 * 
 * Store list of keys
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public class InventoryKeys {
	
	
	private List<Key> key;
	/**
	 * InvetoryKeys Constructor
	 */
	public InventoryKeys() {
		this.key = new ArrayList<Key>();
	}
	
	/**
	 * check if the key match with the id given
	 * @param id
	 * @return true is same, false otherwise
	 */
	public boolean hasCorrectKey(int id){
	//	if(isEmpty()) {return false;}
		if (key.get(0).getID()==id) {
			return true;
		}
		return false;
	}
	
	/**
	 * check if there is a key in the inventory
	 * @return true is there is no key, false otherwise
	 */
	public boolean isEmpty() {
		if (key.size()==0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * add key to the inventory
	 * @param k key
	 */
	public void addKey(Key k) {
		key.add(k);
	}
	
	/**
	 * remove key from the inventory. set the key to dissapear on map
	 */
	public void removeKey() {
		key.get(0).setOnMap(false);
		key.remove(0);
	}
	
	// GETTERS AND SETTERS
	public int getKeyID() {
		return key.get(0).getID();
	}
	
	public List<Key>getKeyList(){
		return key;
	}
	
}
