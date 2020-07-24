package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {
	
	
	private final String COUNTER_STYLE = "-fx-pref-width: 2em; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size:1.35em";
	private final int INV_OFFSETS = 1;
	
	private GameOverScreen gameOverScreen;
	private PauseScreen pauseScreen;
	private GameCompletedScreen gameCompletedScreen;
	private GoalScreen goalScreen;
    @FXML
    private GridPane squares;
    
    
    private List<ImageView> initialEntities;
    private List<Node> others;
    private Player player;
    private Dungeon dungeon;
    private Inventory inventory;
    
    /**
     * Dungeon Controller constructor
     * @param dungeon 
     * @param initialEntities all entities that needed to load into the dungeon
     * @param others other items that is neccessary for the game
     */
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, List<Node> others) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.inventory = player.getInventory();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.others = new ArrayList<>(others);
        
    }

    @FXML
    public void initialize() {
    	
        Image ground = new Image("/dirt_1_new.png");
        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        
        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        for (Node n : others) {
        	if(n.getId() == "sword") {
        		squares.add(n, INV_OFFSETS, 0);
        		
        	}else if (n.getId() == "potion") {
        		squares.add(n, INV_OFFSETS+3, 0);
        	}
        }
        
        TextField inventoryCount = new TextField("0");
        inventoryCount.setStyle(COUNTER_STYLE);
        inventoryCount.textProperty().bindBidirectional(inventory.treasureLeft());
        inventoryCount.visibleProperty().bindBidirectional(inventory.treasureCountVisibility());
        inventoryCount.setBackground(Background.EMPTY);
        squares.add(inventoryCount, INV_OFFSETS + 1, 0);


    }

    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException {
    	if(dungeon.isPlayerDead()) {
    		
    		dungeon.setGameOverText("YOUR PLAYER DIED!!");
    		//System.out.println("player dies");
    		gameOverScreen.start();
    		return;
    	}
    	
    	if(dungeon.isAnyBoulderOnSwitch()) {
    		this.start();
    		
    	}
    	
    	if(dungeon.areGoalsSolved()) {
    		System.out.println("goals are solved");
    		//dungeon.setGameOverText("CONGRATS , YOU WIN!!");

    		pauseScreen.show();
    		showScreen();
    	}

        switch (event.getCode()) {
        case UP:
        	 player.moveUp();
            dungeon.nextCycle();
           
            break;
        case DOWN:
        	 player.moveDown();
            dungeon.nextCycle();
           
            break;
        case LEFT:
        	 player.moveLeft();
        	 dungeon.nextCycle();
           
           
            break;
        case RIGHT:
        	 player.moveRight();
            dungeon.nextCycle();
            break;
        case R:
        	gameOverScreen.start();
        	break;
        case P:
        	//pause button
        	// player can choose to continue or restart game
        	pauseScreen.show();
        	break;
        case G:
        	// TODO show goal 
        	
        	goalScreen.show();
        	break;
        default:
            break;
        }
    }

    /**
     * show game completed screen 
     * @throws IOException
     */
	public void showScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gameCompleted.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
	}
	
	// GETTERS AND SETTERS
	
	public GridPane getSquares() {
		return squares;
	}

	public void setSquares(GridPane squares) {
		this.squares = squares;
	}

	public void setGameOverScreen(GameOverScreen gameOverScreen) {
		// set up game over screen
		this.gameOverScreen = gameOverScreen; 
		
	}
	
	public void setGameCompletedScreen(GameCompletedScreen gameCompletedScreen) {
		// set up game completed screen
		this.gameCompletedScreen = gameCompletedScreen; 
		
	}
	
	public void setPauseScreen(PauseScreen pauseScreen) {
		this.pauseScreen = pauseScreen;
		
	}
	public void setGoalScreen(GoalScreen goalScreen) {
		this.goalScreen = goalScreen;
		
	}

	public Dungeon getDungeon() {
		return this.dungeon;
	}

	 
	/**
	 * POPUP saying GOAL! when a player scores a goal in the goalposts.
	 * @throws IOException
	 */
	
	    public void start() throws IOException {	
	    	
	    	FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("goal.fxml"));
	        /* 
	         * if "fx:controller" is not set in fxml
	         * fxmlLoader.setController(NewWindowController);
	         */
	        Scene scene = new Scene(fxmlLoader.load(), 60, 60);
	        Stage stage = new Stage();
	        stage.setTitle("GOAL!!!!!!");
	        stage.setScene(scene);
	        stage.show();
	    	
	        PauseTransition wait = new PauseTransition(Duration.seconds(0.37));
            wait.setOnFinished((e) -> {
                /*YOUR METHOD*/
                stage.close();
                wait.playFromStart();
            });
            wait.play();
	    	
	    }
	
}

