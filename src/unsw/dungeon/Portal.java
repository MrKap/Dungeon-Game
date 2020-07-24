 package unsw.dungeon;
 /**
  * Portal
  * 
  * player can pass through a portal and teleports to the other side of the portal
  * the portal is two ways
  * 
  * @author Emanuel Kap
  * @author Muhammad Ismail
  *
  */
public class Portal extends Entity{

	private int ID;
	private int exitID;
	
	/**
	 * Portal Constructor
	 * @param ID
	 * @param exitID
	 * @param x
	 * @param y
	 */
	public Portal(int ID, int exitID, int x, int y) {
		super(x, y);
		this.ID = ID;
		this.exitID = exitID;
	}

	@Override
	public boolean validPlayerCollision(Player player) {
		Portal p = player.getDungeon().getExitPortal(ID);
		player.portalPlayerMove(p.getX(), p.getY());
		return false;
	}

	@Override
	public boolean validEnemyCollision() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validBoulderCollision() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//GETTER AND SETTERS
	public int getID() {
		return ID;
	}

	public void setId(int x) {
		this.ID = x;
	}
	
	public int getExitID() {
		return exitID;
	}

	
}
