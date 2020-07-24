package unsw.dungeon;

/**
 * Implementation of Goal interface
 * Goal: Having all boulders on floor switches 
 * 
 * @author Emanuel
 * @author Muhammad
 *
 */
public class AllBouldersGoal implements Goal {
	
	private Dungeon dungeon;
	private String desc;
	
	/**
	 * Goal constructor
	 * @param dungeon
	 */
	public AllBouldersGoal (Dungeon dungeon) {
		this.desc = "Score in all goalposts (place soccer balls on all goalpost squares)";
		this.dungeon = dungeon;
	}
	
	@Override
	/**
	 * check if all switches are on
	 * condition : all boulders should be on the floor switch
	 */
	public boolean isSolved() {
	
		if (dungeon.checkAllSwitches()) {
			return true;
		}

		return false;
	}

	@Override
	/**
	 * return the description of the current goal
	 */
	public String getDesc() {
		// TODO Auto-generated method stub
		return this.desc;
	}


}