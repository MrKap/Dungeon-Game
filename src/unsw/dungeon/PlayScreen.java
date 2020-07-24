package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Play Screen goes here.
 * Loads everything in the controller
 * @author z5177103
 *
 */
public class PlayScreen {

    private Stage stage;
    private String title;
    private DungeonController controller;
    private DungeonControllerLoader dungeonLoader;
    private Scene scene;
    //private GameOverScreen gameOverScreen;
    public PlayScreen(Stage stage) throws IOException {
    	
        this.stage = stage;
        title = "Dungeon";
        //setup();
        dungeonLoader = new DungeonControllerLoader("everything.json");

        controller = dungeonLoader.loadController();
           
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    	stage.setScene(scene);
    }
    
    /**
     * set up the controller
     * @throws IOException
     */
	public void setup() throws IOException {
		
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("everything.json");
    	controller = dungeonLoader.loadController();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
    	loader.setController(controller);
    	Parent root = loader.load();
    	scene = new Scene(root);
    	root.requestFocus();
	}
	/**
	 * restart the playScreen so that everything is new 
	 * @param gameOverScreen
	 * @param pauseScreen
	 * @param goalScreen
	 * @throws IOException
	 */
	public void restart(GameOverScreen gameOverScreen, PauseScreen pauseScreen, GoalScreen goalScreen) throws IOException {
		
		setup();
    	controller.setGameOverScreen(gameOverScreen);
    	controller.setPauseScreen(pauseScreen);
    	controller.setGoalScreen(goalScreen);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        System.out.printf("game page: %b%n" , stage.isShowing());
	}
	
	
	/**
	 * start the screen
	 * @throws IOException
	 */
    public void start() throws IOException {

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        System.out.printf("game page: %b%n" , stage.isShowing());
    }
    
    /**
     * get the controller
     * @return
     */
	public DungeonController getController() {
		return controller;
	}

	// GETTERS SETTERS
	
    public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}


}
