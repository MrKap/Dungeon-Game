package unsw.dungeon;
/**
 * Solving either goals
 *
 */
public class SolveEitherGoal implements Goal {

	private Goal firstGoal;
	private Goal secondGoal;

	/**
	 * Solve either goal constructor
	 * @param firstGoal
	 * @param secondGoal
	 */
	public SolveEitherGoal(Goal firstGoal, Goal secondGoal) {
		this.firstGoal = firstGoal;
		this.secondGoal = secondGoal;
	}
	
	@Override
	public String getDesc() {
		return this.firstGoal.getDesc() + "\nOR\n" + this.secondGoal.getDesc();
	}
	@Override
	public boolean isSolved() {
		// TODO Auto-generated method stub
		return firstGoal.isSolved() || secondGoal.isSolved();
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
