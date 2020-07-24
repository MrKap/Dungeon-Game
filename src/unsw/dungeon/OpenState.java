package unsw.dungeon;
/**
 * Door State : Opened
 * 
 * player can pass through the door in this state.
 * 
 * @author Emanuel Kap
 * @author Muhammad Ismail
 *
 */
public class OpenState implements DoorState {

	private Door door;
	
	/**
	 * opened door constructor
	 * @param door
	 */
	public OpenState(Door door) {
		super();
		this.door = door;
	}
	
	@Override
	public boolean canPassThrough() {
		// TODO Auto-generated method stub
		return true;
	}


}
