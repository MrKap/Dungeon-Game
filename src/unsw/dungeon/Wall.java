package unsw.dungeon;

/**
 * Wall
 * 
 * Player cannot move inside a wall
 * 
 * @author Emanuel Kap
 * @author Muhammad Ismail
 *
 */
public class Wall extends Entity {

	/**
	 * wall constructor
	 * @param x
	 * @param y
	 */
    public Wall(int x, int y) {
        super(x, y);
    }

	@Override
	public boolean validPlayerCollision(Player player) {
		return false;
	}

	@Override
	public boolean validEnemyCollision() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validBoulderCollision() {
		// TODO Auto-generated method stub
		return false;
	}

}
