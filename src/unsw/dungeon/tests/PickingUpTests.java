package unsw.dungeon.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import unsw.dungeon.Invincibility;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Treasure;
import unsw.dungeon.Sword;


public class PickingUpTests {
	Dungeon dungeon;
	Player player;
	
	@Before
	public void setUp() throws Exception {
		
		// Create dungeon and player at (1,1)
		dungeon = new Dungeon(3, 3);
		player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
	}

	private int getPlayerNumSwords() {
		return player.swordCount();
	}
	
	private int getPlayerSwordHits() {
		return player.swordHitsRemaining();
	}
	
	private int getPlayerNumKeys() {
		if (player.isAKeyInInventory()) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	private int getPlayerNumTreasures() {
		return player.treasureCount();
	}
	
	private int getPlayerNumPotions() {
		if (player.isPlayerInvincible()) {
			return 1;
		}
		return 0;
	}



	
	/**
	 * Tests player can pick up treasure, and number treasure stack.
	 */
	@Test
	public void TreasurePickup() {
		// Add a treasure
		Treasure treasure = new Treasure(2, 1);
		dungeon.addEntity(treasure);
		assertEquals(true, dungeon.getEntities(2, 1).contains(treasure));
		// Check pickup 
		assertEquals(0, getPlayerNumTreasures());	//before
		assertEquals(true, player.moveRight());		//pickup
		assertEquals(1, getPlayerNumTreasures());	//after
		assertEquals(false, dungeon.getEntities(2, 1).contains(treasure));	//check removed off dungeon
		assertEquals(true, dungeon.getEntities(2, 1).contains(player));
	}

	/**
	 * Tests player can pick up invincibility.
	 */
	@Test
	public void InvinciblePickup() {
		// Add 
		Invincibility potion = new Invincibility(2, 1);
		dungeon.addEntity(potion);
		assertEquals(true, dungeon.getEntities(2, 1).contains(potion));
		// Check pickup
		assertEquals(0, getPlayerNumPotions()); //before
		assertEquals(true, player.moveRight()); //pickup
		assertEquals(1, getPlayerNumPotions()); //after
		assertEquals(false, dungeon.getEntities(2, 1).contains(potion)); //check removed off dungeon
		assertEquals(true, dungeon.getEntities(2, 1).contains(player));
	}
	
	
	
	/**
	 * Checks player can pick up a sword.
	 */
	@Test
	public void SwordPickup() {
		// Add a sword
		Sword sword = new Sword(2, 1);
		dungeon.addEntity(sword);
		assertEquals(true, dungeon.getEntities(2, 1).contains(sword));
		// Check pickup 
		assertEquals(0, getPlayerNumSwords());		// before
		assertEquals(true, player.moveRight());		// pickup
		assertEquals(1, getPlayerNumSwords());		// after
		assertEquals(sword.getRemainingHits(), getPlayerSwordHits());
		assertEquals(false, dungeon.getEntities(2, 1).contains(sword));	//check removed off dungeon
		assertEquals(true, dungeon.getEntities(2, 1).contains(player));
	}

	/**
	 * Tests player can pick up key.
	 */
	@Test
	public void KeyPickup() {
		// Add a key
		Key key = new Key(0, 2, 1);
		dungeon.addEntity(key);
		assertEquals(true, dungeon.getEntities(2, 1).contains(key));
		// Check pickup
		assertEquals(0, getPlayerNumKeys());		// before
		assertEquals(true, player.moveRight());		// pickup
		assertEquals(1, getPlayerNumKeys());		// after
		assertEquals(false, dungeon.getEntities(2, 1).contains(key));	//check removed off dungeon
		assertEquals(true, dungeon.getEntities(2, 1).contains(player));
	}
	
	
	/**
	 * Checks pick up 1 sword only.
	 */
	@Test
	public void TryPickupManySwords() {
		dungeon.addEntity(new Sword(2, 1));
		dungeon.addEntity(new Sword(2, 2));
		
		assertEquals(0, getPlayerNumSwords());
		assertEquals(true, player.moveRight());
		assertEquals(1, getPlayerNumSwords());
		assertEquals(true, player.moveDown());
		assertEquals(1, getPlayerNumSwords());
	}
	
	
	/**
	 * Checks pick up if many itrms on same square
	 */
	@Test
	public void ManyItemsOnSquarePickup() {	
		// Place an boulder and sword on the same square
		assertEquals(true, player.moveLeft());
		dungeon.addEntity(new Boulder(dungeon, 1, 1));
		dungeon.addEntity(new Sword(1, 1));
		
		Sword sword = new Sword(0, 0);
		
		// Check if player picked up sword
		assertEquals(0, getPlayerNumSwords());
		assertEquals(true, player.moveRight());
		assertEquals(1, getPlayerNumSwords());
		assertEquals(1, dungeon.getEntities(1, 1).size());
		assertEquals(true, dungeon.getEntities(1, 1).contains(player));
	}
	
	
	/**
	 * Checks pick up 1 key only
	 */
	@Test
	public void TryPickupManyKey() {
		
		dungeon.addEntity(new Key(2, 1, 0));
		dungeon.addEntity(new Key(2, 2, 1));
		
		assertEquals(true, player.moveRight());
		assertEquals(1, getPlayerNumKeys());
		assertEquals(true, player.moveDown());
		assertEquals(1, getPlayerNumKeys());
	}
	
	/**
	 * Checks that can pick up many treasures
	 */
	@Test
	public void ManyTreasurePickup() {
		dungeon.addEntity(new Treasure(2, 1));
		dungeon.addEntity(new Treasure(2, 2));
		
		assertEquals(true, player.moveRight());
		assertEquals(1, getPlayerNumTreasures());
		assertEquals(true, player.moveDown());
		assertEquals(2, getPlayerNumTreasures());
	}
	

}