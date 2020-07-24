package unsw.dungeon;

import java.util.List;
/**
 * Boulder
 * 
 * boulder can be pushed by player for some condition
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public class Boulder extends Entity{

	private Dungeon dungeon;
	/**
	 * Boulder Constructor 
	 * @param dungeon dungeon class
	 * @param x 	current X location
	 * @param y 	current Y location
	 */
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

	/**
	 * check if the boulder can move to the destination (x,y).
	 * cannot move if:	another boulder, wall, enemy, outside map
	 * can move if: 	empty tiles, switch, lockedDoor, potion, treasure, sword etc
	 * 
	 * @param x 	x destination
	 * @param y 	y destination
	 * @return		true if the boulder can be move to the given square , otherwise false
	 */
	public boolean isMovable(int x, int y){
		// return false if the destination outside map
		if(x > dungeon.getWidth() || x < 0 || y < 0 || y > dungeon.getHeight())	return false;
		
		List<Entity> entities = dungeon.getEntities(x, y);
		for(Entity e: entities) {
			if(!e.validBoulderCollision()) {
				return false;
			}
		}
		return true;
		
	}

	@Override
	/**
	 * set the location of boulder after pushed by player
	 * if the boulder can be pushed.
	 * return true to the player	
	 */
	public boolean validPlayerCollision(Player player) {
		
		// Get the player's location and check where boulder is pushed
		int playerX = player.getX();
		int playerY = player.getY();
		int targetX = getX() + (getX() - playerX);
		int targetY = getY() + (getY() - playerY);
		
		// If not blocked, move to new location and return true to player
		if (isMovable(targetX, targetY)) {
			x().set(targetX);
			y().set(targetY);
			return true;
		}
		return false;
		
	}

	@Override
	public boolean validEnemyCollision() {
		return false;
	}

	@Override
	public boolean validBoulderCollision() {
		return false;
	}
	
	
	
}




