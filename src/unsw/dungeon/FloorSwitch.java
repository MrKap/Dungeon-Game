package unsw.dungeon;

import java.util.List;

/**
 * Floor Switch
 * 
 * A floor switch can be switch on by moving boulder on it
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public class FloorSwitch extends Entity{
	
	private Dungeon dungeon;
	
	/**
	 * Floor Switch Constructor 
	 * @param dungeon 
	 * @param x 	current X location
	 * @param y 	current Y location
	 */
	public FloorSwitch(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}
	
	/**
	 * check if a boulder is on top of the switch
	 * @return true if a boulder is on top of the switch.
	 */
	public boolean hasBoulderOnTop(){
		
		List<Entity> entities = dungeon.getEntities(getX(), getY());
        for (Entity entity : entities) {
            if (entity instanceof Boulder) {
                return true;
            }
        }
        return false;
	}

	@Override
	public boolean validPlayerCollision(Player player) {
        // Does nothing
        return true;
	}

	@Override
	public boolean validEnemyCollision() {
		return true;
	}

	@Override
	public boolean validBoulderCollision() {
		return true;
	}

}
