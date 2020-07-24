package unsw.dungeon;
/**
 * Door State : Locked
 * 
 * player cannot pass through the door in this state.
 * except if the player carries a key that is same as door ID , the door will change to open state
 * 
 * @author Emanuel Kap
 * @author Muhammad Ismail
 *
 */
public class LockedState implements DoorState {

	private Door door;
	
	/**
	 * lockedState constructor
	 * @param door
	 */
	public LockedState(Door door) {
		super();
		this.door = door;
	}

	@Override
	public boolean canPassThrough() {
		// TODO Auto-generated method stub
		return false;
	}


}
