package unsw.dungeon;
/**
 * Goal interface.
 * Goal implements composite design pattern
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public interface Goal {
	
	
	/**
	 * check whether the current goal already solved or not
	 * @return true if the goal solved, false otherwise
	 */
	public boolean isSolved();
	
	/**
	 * get the description of the current goal is
	 * @return desciption of current goal
	 */
	public String getDesc();
	
}
