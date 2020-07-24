package unsw.dungeon;

/**
 * Treasure
 * 
 * Treasure can be pick up 
 * 
 * @author Emanuel Kap
 * @author Muhammad Ismail
 *
 */
public class Treasure extends Entity {
	
	private final int INV_OFFSETS = 1;
	
	/**
	 * treasure Constructor
	 * @param x
	 * @param y
	 */
	public Treasure(int x, int y) {
		super(x, y);

	}

	@Override
	public boolean validPlayerCollision(Player player) {
		//this.setOnMap(false);
		this.y().set(0);
		this.x().set(INV_OFFSETS + 1);
		player.pickupTreasure(this);
		return true;
	}

	@Override
	public boolean validEnemyCollision() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validBoulderCollision() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
