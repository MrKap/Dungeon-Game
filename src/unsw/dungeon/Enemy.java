package unsw.dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Enemy
 * 
 * enemies will moving towards player except when the player are invinvible
 * 
 * @author Muhammad Ismail
 * @author Emanuel Kap
 *
 */
public class Enemy extends Entity implements Observer{
	
	private Dungeon dungeon;
	private Player player;
	private boolean isPlayerInvincible;
	private int Enemy2 = 0;
	
	/**
	 * Enemy constructor
	 * 
	 * @param dungeon 
	 * @param x 	current X location
	 * @param y 	current Y location
	 */
	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.player = null;
		this.dungeon = dungeon;

	}

	public Enemy(int x, int y) {
		super(x, y);
		this.player = null;

	}
	public Enemy(int id, Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.player = null;
		this.Enemy2 = id;

	}
	
	/**
	 * check if the enemy should run away from the player
	 * @return true if player is invincible
	 */
	public boolean runningAway() {
		return player.isPlayerInvincible();
	}
	
	/**
	 * Enemy movement algorithm when player is invincible
	 */
	public void runAway() {
		int enemyX = this.getX();
		int enemyY = this.getY();
		if ((player.getX() < enemyX)) {
			// If higher x position away is available, move that way
			if (this.isValidEnemyMove(enemyX + 1, enemyY)) {
				this.x().set(enemyX + 1);
				return;
			}
		} else if (player.getX() > enemyX) {
			// If lower x position away is available, move that way
			if (this.isValidEnemyMove(enemyX - 1, enemyY)) {
				this.x().set(enemyX - 1);
				return;
			}		
		} else if (player.getY() < enemyY) {
			// If higher y position away is available, move that way
			if (this.isValidEnemyMove(enemyX, enemyY + 1)) {
				this.y().set(enemyY + 1);
				return;
			}
		} else if (player.getY() > enemyY) {
			// if lower y position
			if (this.isValidEnemyMove(enemyX, enemyY - 1)) {
				this.y().set(enemyY - 1);
				return;
			}
		}

	}
	
	/**
	 * enemy algorithm to move towards player
	 */
	public void chasePlayer() {
		
		
		int currX = this.getX();
		int currY = this.getY();
		int playerX = player.getX();
		int playerY = player.getY();
		
		//current distance
	//	double distance = calcDistance(currX, playerX, currY, playerY);
		double distanceUp = calcDistance(currX, playerX, currY-1, playerY);
		double distanceDown = calcDistance(currX, playerX, currY+1, playerY);
		double distanceLeft = calcDistance(currX-1, playerX, currY, playerY);
		double distanceRight = calcDistance(currX+1, playerX, currY, playerY);

		List<Double> distanceList = new ArrayList<>();
		distanceList.add(distanceUp);
		distanceList.add(distanceDown);
		distanceList.add(distanceLeft);
		distanceList.add(distanceRight);
		Collections.sort(distanceList);
		
		boolean valid = false;
		
		
		while(valid == false) {
			if(distanceList.size()==0) {valid=true;}
			
			else if (distanceList.get(0)==distanceUp) {
				if (this.isValidEnemyMove(currX, currY-1)) {
					this.y().set(currY-1);
					valid = true;
				}
				
				else {
					distanceList.remove(0);
				}
			}
			
			else if (distanceList.get(0)==distanceDown) {
				if (this.isValidEnemyMove(currX, currY+1)) {
					this.y().set(currY+1);
					valid = true;
				}
				
				else {
					distanceList.remove(0);
				}
			}
			
			else if (distanceList.get(0)==distanceLeft) {
				if (this.isValidEnemyMove(currX-1, currY)) {
					this.x().set(currX-1);
					valid = true;
				}
				
				else {
					distanceList.remove(0);
				}
			}
			
			
			else if (distanceList.get(0)==distanceRight) {
				if (this.isValidEnemyMove(currX+1, currY)) {
					this.x().set(currX+1);
					valid = true;
				}
				
				else {
					distanceList.remove(0);
				}
			}
			
			else {
				valid = true;
			}
		}
		
		// player and enemy should be in the same square
		if (this.getX() == player.getX() && this.getY() == player.getY()) {
			System.out.println("player and enemy is on same square");
			this.validPlayerCollision(player);
		}
	}
	

	/**
	 * calculate distance of enemy and player
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 * @return
	 */
	public double calcDistance(int x1, int x2, int y1, int y2) {
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
	
	/**
	 * check if the enemy can move to the target location
	 * @param x X location
	 * @param y Y location
	 * @return true if enemy can move, false otherwise
	 */
	public boolean isValidEnemyMove(int x, int y) {

		// return false if the destination outside map
		if(x > dungeon.getWidth() || x < 0 || y < 0 || y > dungeon.getHeight())	return false;
		
		return dungeon.validEnemyCollide(x, y);

	}
	
	/**
	 * destroy the enemy
	 */
	public void destroyEnemy() {
		this.x().set(0);
		this.y().set(0);
		this.setOnMap(false);
		dungeon.removeEntity(this);
	}

	
	//Updates enemy (where player is the publisher)
	@Override
	public void update(Object object) {
		if (object instanceof Player) {
			Player player = (Player) object;
			if (player.isPlayerInvincible()) {
				runAway();
			}
			else {
				chasePlayer();
			}
		}	
		
	}

	@Override
	/**
	 * enemy will be destroyed if it touches player that has sword or are invincible.
	 * otherwise the player dies
	 */
	public boolean validPlayerCollision(Player player) {

		if (player.isPlayerInvincible()) { //destroy enemy if player is invincible
			destroyEnemy();
		} 
		
		else if (player.isSwordWielded()) { //use sword and destroy enemy if player has sword wielded
			
			if(this.Enemy2 != 0) {
				if(player.swordHitsRemaining()>=2) {
				System.out.println("i have sword bruhh");
				player.useSword();
				player.useSword();
				destroyEnemy();
				return true;
				}
				
				else {
					System.out.println("player die??");
					player.x().set(1);
					player.y().set(0);
					dungeon.gameOver();
				}
				
			}	
			
			else {
				System.out.println("i have sword bruhh");
				player.useSword();
				destroyEnemy();
				return true;
			}

		}
		
		else {
			System.out.println("player die??");
			// TODO: go to game over page
			//dungeon.removeEntity(player); //Player dies.
			player.x().set(1);
			player.y().set(0);
			dungeon.gameOver();
		}
		
		return true;
	}
	
	/**
	 * next movement of the enemy
	 */
	public void nextCycle() {
		
		if(this.Enemy2 != 0) {
			if (this.runningAway()) {
				runAway();
				runAway();
			} else {
				chasePlayer();
				chasePlayer();
			}	
		}
		
		else {
			if (this.runningAway()) {
				runAway();
			} else {
				chasePlayer();
			}	
		}
	}

	@Override
	public boolean validEnemyCollision() {
		return false;
	}

	public int getEnemy2() {
		// TODO Auto-generated method stub
		return this.Enemy2;
	}

	@Override
	public boolean validBoulderCollision() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * set the player
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}


