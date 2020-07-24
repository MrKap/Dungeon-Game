package unsw.dungeon;

/**
 * Implementation of Goal interface
 * Goal: Getting to an exit
 * 
 * @author Emanuel Kap
 * @author Muhammad Ismail
 *
 */
public class ExitSquareGoal implements Goal {

	private Dungeon dungeon;
	private String desc;
	
	/**
	 * Exit Square constructor
	 * @param dungeon
	 */
	public ExitSquareGoal (Dungeon dungeon) {
		this.desc = "Get to Full Time Square";
		this.dungeon = dungeon;
	}
	
	@Override
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	/**
	 * check if the player has reached the exit square
	 */
	public boolean isSolved() {

		if(dungeon.isPlayerOnExit()) { return true;}
		else{return false;}
		
	}



}
