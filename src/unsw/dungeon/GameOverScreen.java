package unsw.dungeon;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Game over screen goes here.
 * load everything is the controller
 * @author z5177103
 *
 */
public class GameOverScreen {

	private Stage stage;
    private String title;
    private GameOverController controller;
    private Scene scene;

    /**
     * GameOverScreen constructor
     * @param stage 
     * @param dungeon
     * @throws IOException
     */
    public GameOverScreen(Stage stage, Dungeon dungeon) throws IOException {
        this.stage = stage;
        title = "GAME OVER :'(";

		controller = new GameOverController(dungeon);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("gameOverPage.fxml"));
		loader.setController(controller);
		
		// load into a Parent node called root
		    Parent root = loader.load();
		    scene = new Scene(root);
	}
	
    /**
     * show the current screen
     * @throws IOException
     */
	public void start() throws IOException {
		
	    stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();
	    System.out.printf("game over page: %b%n" , stage.isShowing());
	}
	
	/**
	 * get the GameOverController
	 * @return controller
	 */
	public GameOverController getController() {
	    return controller;
	}

}
