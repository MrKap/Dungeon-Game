package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    /**
     * Dungeon Loader constructor
     * @param filename
     * @throws FileNotFoundException
     */
    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        Goal overallGoal = loadGoal(dungeon, jsonGoals);
        dungeon.setGoal(overallGoal);
        
        return dungeon;
    }

    /**
     * Parse Entity from JSONObject file and load into dungeon
     * @param dungeon
     * @param json
     */
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id = 0;
        if (json.has("id")) {
        	id = json.getInt("id");
        	}


        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
     //       dungeon.addEntity(wall);
            onLoad(wall);
            entity = wall;
            break;
        case "boulder":
        	Boulder boulder = new Boulder(dungeon,x,y);
   //     	dungeon.addEntity(boulder);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x,y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "exit":
        	ExitSquare exit = new ExitSquare(x,y);
    //    	dungeon.addEntity(exit);Box
        	dungeon.setExitSquare(exit);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "door":
        	Door door = new Door(id,x,y);
  //      	dungeon.addEntity(door);
        	onLoad(door);
        	entity = door;
        	break;
        case "key":
        	Key key = new Key(id, x, y);
     //   	dungeon.addEntity(key);
        	onLoad(key);
        	entity = key;
        	break;
        case "switch":
        	FloorSwitch floorSwitch = new FloorSwitch(dungeon, x,y);
        	dungeon.addSwitch(floorSwitch);
      //  	dungeon.addEntity(floorSwitch);
        	onLoad(floorSwitch);
        	entity = floorSwitch;
        	break;
        case "portal":
        	Portal portal = new Portal(id,(-id),x,y);
      //  	dungeon.addEntity(portal);
        	dungeon.addPortal(portal);
        	onLoad(portal);
        	entity = portal;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(dungeon, x,y);
   //     	dungeon.addEntity(enemy);
        	dungeon.setEnemy(enemy);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "enemy2":
        	Enemy enemy2 = new Enemy(id, dungeon, x,y);
   //     	dungeon.addEntity(enemy);
        	dungeon.setEnemy(enemy2);
        	onLoad(enemy2);
        	entity = enemy2;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y);
  //      	dungeon.addEntity(sword);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "invincibility":
        	Invincibility invincibility = new Invincibility(x,y);
    //    	dungeon.addEntity(invincibility);
        	onLoad(invincibility);
        	entity = invincibility;
        	break;
        }
        dungeon.addEntity(entity);
    }

    
    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(ExitSquare exit);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(FloorSwitch floorSwitch);
    
    public abstract void onLoad(Portal portal);
    
    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Invincibility invincibility);
   
    /**
     * Parse Goals from given JSONObject and load into dungeon
     * @param dungeon
     * @param json
     * @return
     */
    private Goal loadGoal(Dungeon dungeon, JSONObject json) {
        String goalType = json.getString("goal");
        if (goalType.equals("AND") || goalType.equals("OR")) {
            Goal goal =  loadSubgoals(dungeon, json.getString("goal"), json.getJSONArray("subgoals"));
            return goal;
        }
        Goal goal = null;
        switch (goalType) {
        case "enemies":
            goal = new KillAllEnemiesGoal(dungeon);
            break;
        case "treasure":
            goal = new CollectTreasureGoal(dungeon);
            break;
        case "exit":
            goal = new ExitSquareGoal(dungeon);
            break;
        case "boulders":
            goal = new AllBouldersGoal(dungeon);
            break;
        }
        return goal;
    }
    
    
    /**
     * Parse Subgoals from given JSONObject and load into dungeon
     * @param dungeon
     * @param json
     * @return
     */
    private Goal loadSubgoals(Dungeon dungeon, String method, JSONArray subgoals) {
        // assumes 2 goals
        Goal goal1 = loadGoal(dungeon, subgoals.getJSONObject(0));
        Goal goal2 = loadGoal(dungeon, subgoals.getJSONObject(1));
        if (method.equals("AND")) {
            return new SolveBothGoals(goal1, goal2);
        } else if (method.equals("OR")) {
            return new SolveEitherGoal(goal1, goal2);
        }
        return null;
    }
    
}
