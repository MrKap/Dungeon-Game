package unsw.dungeon;
/**
 * Implementation of Goal interface
 * Goal: Kill all enemies
 * 
 * @author Emanuel Kap
 * @author Muhammad Ismail
 *
 */
public class KillAllEnemiesGoal implements Goal {

	private Dungeon dungeon;
	private String desc;

	/**
	 * Kill enemy constructor
	 * @param dungeon
	 */
	public KillAllEnemiesGoal (Dungeon dungeon) {
		this.desc = "Give red cards to all defenders";
		this.dungeon = dungeon;
	}
	
	@Override
	public String getDesc() {
		return desc;
	}
	
	@Override
	public boolean isSolved() {
		return dungeon.isEnemyDead();
	}
}