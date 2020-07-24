package unsw.dungeon;
/**
 * Key
 * 
 * Key has unique ID which will be use to open a door with the same ID
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public class Key extends Entity{
	private final int INV_OFFSETS = 1;
	private int id;
	
	/**
	 * Key Constructor
	 * @param id key id
	 * @param x x location
	 * @param y y location
	 */
	public Key(int id, int x, int y) {
		super(x, y);
		this.id = id;
	}

	/**
	 * get the key id
	 * @return id - key id
	 */
	public int getID() {
		return id;
	}


	@Override
	public boolean validPlayerCollision(Player player) {

		if(!player.isAKeyInInventory()) {
			//this.setOnMap(false);
			this.x().set(INV_OFFSETS + 2);
			this.y().set(0);
			player.pickupKey(this);
		}
		
		return true;
	}


	@Override
	public boolean validEnemyCollision() {
		return true;
	}


	@Override
	public boolean validBoulderCollision() {
		// TODO Auto-generated method stub
		return true;
	}
}
