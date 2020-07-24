package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Door
 * 
 * player can pass through a door if it is in open state only
 * player needs key to open a door (key and door must have same ID)
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public class Door extends Entity{
	
	private int id;
	private DoorState state;
	private DoorState openState;
	private DoorState lockedState;
	private BooleanProperty unlocked;
	
	/**
	 * Door constructor
	 * 
	 * @param id	unique ID for the door, each door will have a corresponding key with same ID
	 * @param x		current X location
	 * @param y		current Y location
	 */
	public Door(int id, int x, int y) {
		super(x, y);
		this.id = id;
		this.openState = new OpenState(this);
		this.lockedState = new LockedState(this);
		this.state = new LockedState(this);
		this.unlocked = new SimpleBooleanProperty(false);
	}
	
	/**
	 * Getter for door ID
	 * @return 	door ID
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Current state of the Door 
	 *
	 * @return True if the door is Opened, otherwise false
	 */
	public boolean canPassThrough() {
		this.unlocked.set(state.canPassThrough());
		return state.canPassThrough();
		
	}	
	
	
	/**
	 * Function so dungeonController knows if door unlocked or not to load correct image.
	 * @return unlocked - true if door unlocked, false if not.
	 */
	public BooleanProperty frontendDoorThing() {
		return unlocked;
	}
	
	
	
	/**
	 * setter for door state
	 * @param newDoorState
	 */
	public void setState(DoorState newDoorState) {
		
		this.state = newDoorState;
		
	}
	
	@Override
	/**
	 * check if the player can pass through the door.
	 * can pass through if door is already open.
	 * if the door is locked, the player must carry the key with door ID to pass through
	 * otherwise the door state will remain locked.
	 */
	public boolean validPlayerCollision(Player player) {
		
		//if door open
		if(state instanceof OpenState) {
			return state.canPassThrough();
			
		}
		else if( state instanceof LockedState) {
			
			// check if the player carries key matching door id
			if(player.isAKeyInInventory() && player.isMatchingKeyinInventory(this.id)) {
				setState(openState);
				player.useKey();
				this.unlocked.set(state.canPassThrough());

				return state.canPassThrough();
			}
			
			else {
				setState(lockedState);
				this.unlocked.set(state.canPassThrough());
				return state.canPassThrough();
			}
		}
		
		else {
			
			// no state given
			// should not reach here
			// make sure it closed
			setState(lockedState);
			return state.canPassThrough();
		}
		
	}
	
	/**
	 * Function that determines whether an enemy can collide with door.
	 * @return state -> If door unlocked then TRUE and enemy can move there, if locked FALSE and can't
	 */
	@Override
	public boolean validEnemyCollision() {
		return state.canPassThrough();
	}
	
	/**
	 * Function that determines whether a boulder can collide with door.
	 * @return state -> If door unlocked then TRUE and enemy can move there, if locked FALSE and can't
	 */
	@Override
	public boolean validBoulderCollision() {
		return state.canPassThrough();
	}

}
