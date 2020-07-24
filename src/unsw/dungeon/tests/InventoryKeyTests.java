
package unsw.dungeon.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;



public class InventoryKeyTests {
    Dungeon dungeon;
    Player player;


    @Before
    public void setUp() throws Exception {
        dungeon = new Dungeon(3, 3);
        player = new Player(dungeon, 0, 0);
    }

    
    /**
     * Tests if no key exists in inventory will make door not open.
     */
    @Test
    public void DoorNoKey() {
    	Door door = new Door(1,0,0);
        assertEquals(false, door.validPlayerCollision(player));
    }
    
    
    /**
     * Checks if key and door match.
     * Also checks if removal was successful.
     */
    @Test
    public void KeyDoorMatch() {
    	Door door = new Door(3, 0, 0);
    	player.pickupKey(new Key(3,0,0));
        assertEquals(true, door.validPlayerCollision(player));
        assertEquals(true, door.canPassThrough());
        // After the first match the door should still be open
        assertEquals(true, door.validPlayerCollision(player));
        
        //new door, no key, so shouldnt be ablt to move
        assertEquals(false, (new Door(1, 0, 0)).validPlayerCollision(player));
    }
    

    /**
     * Checks key mismatch.
     */
    @Test
    public void KeyDoorMismatch() {
    	Door door = new Door(0,0,0);
    	player.pickupKey(new Key(1,0,1));
        assertEquals(false, door.validPlayerCollision(player));
    }
    

 

}