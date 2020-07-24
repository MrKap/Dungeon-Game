package unsw.dungeon;

/**
 * Solving both goals
 *
 */
public class SolveBothGoals implements Goal {

	private Goal firstGoal;
	private Goal secondGoal;
	
	/**
	 * Solve both goals constructor
	 * @param firstGoal
	 * @param secondGoal
	 */
	public SolveBothGoals(Goal firstGoal, Goal secondGoal) {
		this.firstGoal = firstGoal;
		this.secondGoal = secondGoal;
	}
	

	@Override
	public boolean isSolved() {
		// TODO Auto-generated method stub
		return firstGoal.isSolved() && secondGoal.isSolved();
	}

	@Override
	public String getDesc() {
		
		return this.firstGoal.getDesc() + " AND " + this.secondGoal.getDesc(); 
	}

	//GETTERS AND SETTERS
	public Goal getFirstGoal() {
		return firstGoal;
	}

	public void setFirstGoal(Goal firstGoal) {
		this.firstGoal = firstGoal;
	}

	public Goal getSecondGoal() {
		return secondGoal;
	}

	public void setSecondGoal(Goal secondGoal) {
		this.secondGoal = secondGoal;
	}
	
}
