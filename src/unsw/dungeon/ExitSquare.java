package unsw.dungeon;

import java.util.List;
/**
 * Exit Square
 * 
 * player will win the game if it steps on the exit square provided all goal are solved
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public class ExitSquare extends Entity{
	
	private Dungeon dungeon;
	
	public ExitSquare(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}
	
	public ExitSquare(int x, int y) {
		super(x, y);
	}
	
	
	/**
	 * check if the player is on top of the exit square
	 * @return true if player is at exit square, false otherwise
	 */
	public boolean hasPlayerOnTop(){
		
		if(dungeon.getPlayer().getX() == this.getX() && dungeon.getPlayer().getY() == this.getY()) {
	        return true;
		}
		return false;
	}
	
	@Override
	public boolean validPlayerCollision(Player player) {
		return true;
	}

	@Override
	public boolean validEnemyCollision() {
		return true;
	}

	@Override
	public boolean validBoulderCollision() {
		// TODO Auto-generated method stub
		return false;
	}

}
