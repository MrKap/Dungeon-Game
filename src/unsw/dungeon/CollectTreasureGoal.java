package unsw.dungeon;
/**
 * Implementation of Goal interface
 * Goal: Collecting all treasure.
 * 
 * @author Emanuel Kap
 * @author Muhammad Ismail
 *
 */
public class CollectTreasureGoal implements Goal {

	private Dungeon dungeon;
	
	/**
	 * description of current goal
	 */
	private String desc;
	
	/**
	 * Goal constructor
	 * @param dungeon
	 */
	public CollectTreasureGoal (Dungeon dungeon) {
		this.desc = "Collect all trophies";
		this.dungeon = dungeon;
	}
	
	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	/**
	 * Collect all treasure in the dungeon goal
	 * See if the is still treasure left in the dungeon
	 */
	public boolean isSolved() {
		// collect all treasure in the dungeon goal
		// see if the is still treasure left in the dungeon
		if(dungeon.treasuresAllCollected()) {
			return true;
		}
		return false;
	}
}