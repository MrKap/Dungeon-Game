package unsw.dungeon;

/**
 * Observer for Enemy and Player
 * Enemy needs to know where is the current position of player
 * @author z5177103
 *
 */
public interface Observer{

	/**
	 * update the enemy
	 * @param object
	 */
	public void update(Object object);
	
}
