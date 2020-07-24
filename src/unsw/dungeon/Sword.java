package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Sword
 * 
 * Player can use a sword
 * each sword will have 5 hits
 * each hits will be used if hits an enemy
 * once it reach 0 hits, the sword will be remove
 * 
 * @author Emanuel Kap
 * @author Muhammad Ismail
 *
 */
public class Sword extends Entity {
	
	private final int INV_OFFSETS = 1;
	private int remainingHits;
	private boolean pickedUp = false;
	
	private StringProperty hitText;
	private BooleanProperty showHits;
	
	/**
	 * Sword Constructor
	 * @param x
	 * @param y
	 */
	public Sword(int x,int y) {
		super(x, y);
		hitText = new SimpleStringProperty();
		showHits = new SimpleBooleanProperty();
		this.remainingHits = 5;
	}
	
	/**
	 * get the remaining hits
	 * @return number of hits left
	 */
	public int getRemainingHits(){
		return remainingHits;
	}
	
	/**
	 * reduce the hit counter
	 */
	public void hitAction(){
		int n = remainingHits -1;
		hitText.setValue(String.valueOf(n));
		if(n == 0) showHits.setValue(false);
		--remainingHits;
		
	}

	@Override
	public boolean validPlayerCollision(Player player) {

		//this.setOnMap(false);
		showHits.setValue(true);
		player.pickupSword(this);
		this.x().set(INV_OFFSETS);
		this.y().set(0);
		//System.out.println("meet sword");
		//dungeon.removeEntity(this);
		
		return true;
	}

	@Override
	public boolean validEnemyCollision() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * check if the sword is picked up or not
	 * @return true if it is already picked up, false otherwise
	 */
	public boolean isPickedUp() {
		return pickedUp;
	}

	/**
	 * pickup the sword
	 * @param pickedUp
	 */
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	/**
	 * the remaining sword hits in String Property
	 * @return hitText - remainig hit left
	 */
	public StringProperty hitsLeft() {
		
		hitText.set(String.valueOf(getRemainingHits()));
		return hitText;
	}

	/**
	 * check the hit count visibility
	 * @return
	 */
	public BooleanProperty hitsVisibility() {
		// TODO Auto-generated method stub
		return showHits;
	
	}

	@Override
	public boolean validBoulderCollision() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
