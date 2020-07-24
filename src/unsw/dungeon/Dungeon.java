/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private List<FloorSwitch> list2;
    private Player player;
    private List<FloorSwitch> floorSwitches;
    private List<Portal> portals;
    private List<Enemy> enemies;
    private ExitSquare exitSquare;
    private Goal goal;
    private int enemyNumber;
    private int newEnemyNumber;
    private boolean gameOver = false;
    private StringProperty gameOverText =  new SimpleStringProperty("Player Dead!");

    /**
     * Constructor for Dungeon
     * @param width - Width of the dungeon 
     * @param height - Height of the dungeon
     */
	public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.floorSwitches = new ArrayList<>();
        this.player = null;
        this.goal = null;
        this.enemies = new ArrayList<Enemy>();
        this.exitSquare = null;
        this.portals = new ArrayList<>();
        this.enemyNumber=0;
        this.newEnemyNumber = 0;
        this.list2 = new ArrayList<>();
        
       
    }
    
	/**
	 * Finds the exit portal given an Id of a start portal
	 * @param id - ID of the start portal
	 * @return - returns the exit portal
	 */
	public Portal getExitPortal(int id) {
		for (Portal p : portals) {
			if( p.getID()==(-id)){
				return p;
			}
		}
		
		return null;
	}
	
	/**
	 * add instance FloorSwitch to the floorSwitches list 
	 * @param floorSwitch
	 */
	public void addSwitch(FloorSwitch floorSwitch) {
	    floorSwitches.add(floorSwitch);
	    list2.add(floorSwitch);
	}

	/**
	 * add instance of portal to the portals list and the dungeon entities list
	 * @param portal
	 */
	public void addPortal(Portal portal) {
		for(Portal portalls : portals) {
			if(portal.getID()==portalls.getID()) {
				portal.setId(-portalls.getID());
			}
		}
		portals.add(portal);
		entities.add(portal);
	}
	
	/**
	 * add given entity to dungeon entities list
	 * except portals
	 * @param entity
	 */
    public void addEntity(Entity entity) {
    	if (entity instanceof Portal) {
    		
    		
    	}
    	
    	else {
        entities.add(entity);
    	}
    }
  

    /**
     * given a square, it returns a list of entities on that square.
     * 
     * @param x		X location of the square
     * @param y		Y location of the square
     * @return		List of entities at the given location 
     */

    public List<Entity> getEntities(int x, int y) {
    	
    	List<Entity> entitiesOnSquare = new ArrayList<Entity>();
    	//Checks for a player
    	if((player != null) && (player.getX() == x) && (player.getY() == y)) {
    		entitiesOnSquare.add(player);
    	}   	
    	
    	// Check for other entities
    	for(Entity entity : entities) {
    		if((entity != null) && (entity.getX() == x) && (entity.getY() == y)) {
    			entitiesOnSquare.add(entity);
    		}
    	}
  
    	return entitiesOnSquare;
    }  
    
    /**
     * check if the enemy number is changed
     * @return true if changed, false otherwise
     */
    public boolean didEnemyNumChange() {
    	if (enemyNumber!=newEnemyNumber) {
    		return false;
    		
    	}
    	
    	return true;
    }
    
    
    /**
     * check if all goals are solved
     * @return true is all goals solved
     */
	public boolean areGoalsSolved() {
	    return goal.isSolved();
	}
    
	/**
	 * set the gameOverText to given string
	 * @param s new check
	 */
	public void setGameOverText(String s) {
		gameOverText.setValue(s);
	}
	
	/**
	 * game over text
	 * win : goals solved
	 * lose : player dies
	 * @return
	 */
	public StringProperty gameOverText() {
		return this.gameOverText;
	}
	
	/**
	 * check if enemy is dead
	 * @return	true if the enemy is null false otherwise
	 */
	public boolean isEnemyDead() {
		return enemies.isEmpty() || enemies == null;
	} 
	
	/**
	 * check if the player is dead
	 * @return	true if player is dead, false otherwise
	 */
	public boolean isPlayerDead() {
		if (player == null) {
			gameOver = true;
			return true;
		} 
		else {
			return false;
		}
	}
	
	/**
	 * Check if the player can move to the destination.
	 * The destination might have some other entities.
	 * 
	 * @param x		X destination
	 * @param y		Y destination
	 * @return		return true of each of the entities in the square given is collidable with player
	 */
	public boolean validCollide(int x, int y) {
		
		List<Entity> entitiesOnSquare = getEntities(x, y);
			for(Entity e : entitiesOnSquare) {
				if(!e.validPlayerCollision(player)) {
					return false;
				}
			}
			
			return true;
	}
	
	/**
	 * Check if the enemy can move to the destination.
	 * The destination might have some other entities.
	 * 
	 * @param x		X destination
	 * @param y		Y destination
	 * @return		return true of each of the entities in the square given is collidable with enemy
	 */
	public boolean validEnemyCollide(int x, int y) {
		
		List<Entity> entitiesOnSquare = getEntities(x, y);
		for(Entity e : entitiesOnSquare) {
			if(!e.validEnemyCollision()) 
				return false;
		}
		return true;
		
	}
	
	/**
	 * remove the given entity from dungeon
	 * @param entity
	 */
	public void removeEntity(Entity entity) {
		if (player != null && player.equals(entity)) {
			player = null;
		} else if (enemies.contains(entity)) {
				enemies.remove(entity);
				newEnemyNumber--;
		} else if (floorSwitches.contains(entity)) {
			floorSwitches.remove(entity); 
		} else if (entities.contains(entity)){
			entities.remove(entity); 
		}
	}
	
	/**
	 * Check every switch on the dungeon whether it is on or off
	 * Switch is on if there is a boulder on it, off otherwise.
	 * 
	 * @return true if switch is on false otherwise.
	 */
	public boolean checkAllSwitches() {
		// check every switch on the dungeon whether it is on or not
		for (FloorSwitch s : floorSwitches) {
			if(!s.hasBoulderOnTop()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if all there is still instance of treasures left in the dungeon
	 * 
	 * @return	true if no instance of treasures , false otherwise
	 */
	public boolean treasuresAllCollected() {
		
		boolean treasureFlag = true;
		// check the if list of treasure is empty
		for (Entity e : entities) {
			if (e instanceof Treasure) {
				treasureFlag = false;
			}
		}
		
		return treasureFlag;
	}

	/**
	 * check if the player is on the Exit Square
	 * 
	 * @return true if the player on the exit square
	 */
	public boolean isPlayerOnExit() {
		// check if the player is on the exit square
		for(Entity entity : entities ) {
			if(entity instanceof ExitSquare) {
				if((entity.getX() == player.getX()) && (entity.getY() == player.getY()))
					return true;
			}
		}
		return false;
	}
    
	/**
	 * Checks if any boulders are on a floor switch
	 * @return TRUE if a boulder is on a switch, and FALSE otherwise.
	 */
	public boolean isAnyBoulderOnSwitch() {
		for (FloorSwitch s : list2) {
			if(s.hasBoulderOnTop()) {
				list2.remove(s);
				return true;
			}
		}
			
			return false;
	}
		
		
		
	/**
	 * next enemy movement
	 */
	public void nextCycle() {
		for(Enemy enemy : enemies) {
			enemy.nextCycle();
		}
		

		if(player.isPlayerInvincible()) {
			player.decrementInvincibility();
		}
	}

	/**
	 * set the game to game over, set enemy to null
	 * @return gamOver - true
	 */
	public boolean gameOver() {
		// TODO Game Over, player dies
		this.player = null;
		return gameOver;
	}
	
	//GETTERS AND SETTERS
    public ExitSquare getExitSquare() {
		return exitSquare;
	}

	public void setExitSquare(ExitSquare exitSquare) {
		this.exitSquare = exitSquare;
	}
	
	public void setGoal(Goal goal) {
		this.goal = goal;
	}
	public Goal getGoal() {
		return this.goal;
	}
	
	public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }
    
    /**
     * Sets the player
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
        if(player != null) {
	        for(Enemy enemy : enemies) {
	        	enemy.setPlayer(player);
	            player.addObserver(enemy);
	        }
        }
    }

    /**
     * Sets the enemy
     * @param enemy The enemy to set
     */
    public void setEnemy(Enemy enemy) {
    	enemies.add(enemy);
    	enemyNumber++;
    	newEnemyNumber=enemyNumber;
		if (player != null) {
		    player.addObserver(enemy);
		    enemy.setPlayer(player);
		}
	}
}
