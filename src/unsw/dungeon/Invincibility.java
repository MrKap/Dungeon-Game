package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Invincibility 
 * 
 * A player can use invincibility potion and become invincible for a period of time.
 * during the perid, the player are immune to enemy and enemy will be destroyed if the player touches the enemy
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public class Invincibility extends Entity {
	
	private final int INV_OFFSETS = 1;
	private int timeRemaining = 11;
	private StringProperty timeLeft= new SimpleStringProperty();
	private BooleanProperty showTime = new SimpleBooleanProperty(false);
	
	/**
	 * invinvibility potion Constructor
	 * @param x
	 * @param y
	 */
	public Invincibility(int x, int y) {
		super(x, y);
		this.timeLeft.setValue(String.valueOf(timeRemaining));
		// TODO Auto-generated constructor stub
	}

	/**
	 * get the time remaining for the potion
	 * @return timeRemaining
	 */
	public int getTimeRemaining(){
		return timeRemaining;
	}
	
	/**
	 * reduce the invinvibility time
	 */
	public void decrementInvincibleTime() {
		timeRemaining = timeRemaining-1;
		timeLeft.setValue(String.valueOf(timeRemaining));
		if(timeRemaining == 0 ) showTime.setValue(false);
	}

	@Override
	public boolean validPlayerCollision(Player player) {

		this.x().set(INV_OFFSETS + 3);
		this.y().set(0);
		player.pickupInvPotion(this);
		showTime.set(true);
		return true;
	}

	@Override
	public boolean validEnemyCollision() {
		return true;
	}

	/**
	 * time left fot the potion but in string property to view in frontend
	 * @return timeLeft StringProperty
	 */
	public StringProperty timeLeft() {
		// TODO Auto-generated method stub
		return timeLeft;
	}

	/**
	 * return visibility if the remaining time of the potion for frontend
	 * @return showTime BooleanProperty
	 */
	public BooleanProperty timeVisibility() {

		return showTime;
	}

	@Override
	public boolean validBoulderCollision() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
