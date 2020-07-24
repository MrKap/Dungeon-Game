package unsw.dungeon;

/**
 * subject for observer
 * @author
 *
 */
public interface Subject{

	/**
	 * add the observer
	 * @param observer
	 */
	public void addObserver(Observer observer);
		
	/**
	 * notify the observers
	 */
	public void notifyObservers();
		
		
		
	
	
	
	
}
