package unsw.dungeon;
/**
 * Door state
 * 	- Open State
 * 	- Locked State
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public interface DoorState {
	
	/**
	 * check if the door is still locked or not
	 * @return true if the door is unlocked
	 */
	public boolean canPassThrough();
	
	
}
