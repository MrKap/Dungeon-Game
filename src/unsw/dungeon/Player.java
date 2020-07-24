package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject {

    private Dungeon dungeon;
    private List<Observer> observers;
    private Inventory inventory;
    
    /**
     * Player Constructor
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.inventory = new Inventory();
        this.observers = new ArrayList<Observer>();
    }

 /**
  * move the player to given coordinate
  * @param x
  * @param y
  * @return
  */
    //Main method to move the player
    public boolean playerMove(int x, int y) {
    	if(dungeon.validCollide(x, y)) { // Moves player to square(x,y) if a valid move
    		this.x().set(x);
    		this.y().set(y);
    		return true;
    	}
    	return false; // Player stays where they are.
    }

    /**
     * teleports the player to other side of portals
     * @param x
     * @param y
     * @return
     */
    public void portalPlayerMove(int x, int y) {
    	this.x().set(x);
    	this.y().set(y);
    //	return true;
    }
    
    /**
     * move player up one square
     * @return
     */
    //Moves player up
	public boolean moveUp() {
    	boolean valid = false;
        if (getY() > 0) {
        	valid = playerMove(getX(), getY()-1);
        }
        
        return valid;
    }
    
	/**
	 * move player down one square
	 * @return
	 */
	//Moves player down
    public boolean moveDown() {
    	boolean valid = false;
        if (getY() < dungeon.getHeight() - 1) {
        	valid = playerMove(getX(), getY()+1);
        }
        
        return valid;
    }

    /**
     * move player left one square
     * @return
     */
    //Moves player let
    public boolean moveLeft() {
    	boolean valid = false;
        if (getX() > 0) {
        	valid = playerMove(getX()-1, getY());
        }
        return valid;
    }

    /**
     * move player right one square
     * @return
     */
    //Moves player right
    public boolean moveRight() {
    	boolean valid = false;
        if (getX() < dungeon.getWidth() - 1) {
        	valid = playerMove(getX()+1, getY());
        }
        return valid;
    }
    	
    /**
     * pick up sword
     * @param s 	sword
     */
    public void pickupSword(Sword s) {
    	inventory.pickupSword(s); //adds sword to inventory
    	dungeon.removeEntity(s); //removes sword from board

    }
    
    /**
     * count sword in inventory
     * @return
     */
    public int swordCount() {
    	return inventory.swordCount();
    }
    
    /**
     * hits remaining of the swords
     * @return
     */
    public int swordHitsRemaining() {
    	return inventory.swordHits();
    }
    
    /**
     * use key from inventory
     */
    public void useKey() {
    	inventory.useKey();
    }
    
    
    /**
     * pick up potion
     * @param invincibility
     */
    public void pickupInvPotion(Invincibility invincibility) {
    	inventory.pickupInvPotion(invincibility);
    	dungeon.removeEntity(invincibility);
    }
    
    
    /**
     * pickup key
     * @param kk
     */
    public void pickupKey(Key kk) {
    	inventory.pickupKey(kk); //adds key to inventory
    	dungeon.removeEntity(kk); //removes key from board.
    }
    
    /**
     * pickup treasure
     * @param t
     */
    public void pickupTreasure(Treasure t) {
    	inventory.pickupTreasure(t); //adds treasure to inventory.
    	dungeon.removeEntity(t); //removes treasure from board.
    }
    
    /**
     * checks if player is carrying matching key to door ID provided.
     * @param id
     * @return true if the key matches false otherwise
     */
    
    public boolean isMatchingKeyinInventory(int id) {
    	return inventory.isCarryingCorrectKey(id);
    }
    
    /**
     * Checks if we have a key already in inventory
     * @return true if we have the key false otherwise
     */

    public boolean isAKeyInInventory() {
    	return inventory.isCarryingKey();
    }
    
    /**
     * Uses the wielded sword -> meaning the amount of hits left decrements.
     */
 
    public void useSword() {
    	inventory.useSword();
    }
    
    /**
     * Checks a sword is wielded
     * @return true if the player carry a sword , false otherwise
     */
    public boolean isSwordWielded() {
    	return inventory.isSwordWielded();
    }
    
    /**
     * Checks in player invincible
     * @return
     */
    public boolean isPlayerInvincible() {
    	return inventory.isPlayerInvincible();
    }
    
    /**
     * check how many treasure there are in the inventory
     * @return treasure count
     */
    public int treasureCount() {
    	return inventory.getTreasureInv().treasureCount();
    }
    
    
    
    /**
     * dget the dungeon
     * @return
     */
    public Dungeon getDungeon() {
		return dungeon;
	}

    
    /**
     * add the observer
     */
	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);
		
	}

	/**
	 * 
	 */
	@Override
	public void notifyObservers() {
        for (Observer o : this.observers) {
            o.update(this);
        }
    }
		
	public void decrementInvincibility() {
		inventory.decrementInvinc();
	}
	
	
	/**
	 * Method to determine whether a player can enter the same square as a player.
	 * return FALSE -> as a player cannot move into a square occupied by another player
	 */
	@Override
	public boolean validPlayerCollision(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validEnemyCollision() {
		// TODO Auto-generated method stub
		return true;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public boolean validBoulderCollision() {
		// TODO Auto-generated method stub
		return false;
	}
}
