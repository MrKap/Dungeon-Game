package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * goal screen
 * this will view all the goals that need to be completed to win the game
 * @author z5177103
 *
 */
public class GoalScreen {
	
	private Stage stage;
	private String title;
	private GoalController controller;
	private Scene scene;
	private Dungeon dungeon;
	public GoalScreen(Stage stage, Dungeon dungeon) throws IOException {
		this.stage = stage;
	    title = "Goal Screen";
	    this.dungeon = dungeon;
	
	    controller = new GoalController(dungeon);
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("goalPage.fxml"));
	    loader.setController(controller);
	
	    // load into a Parent node called root
	    Parent root = loader.load();
	    scene = new Scene(root);
	}
	/**
	 * show the screen
	 */
	public void show() {
		// TODO show goals
		stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();
	    System.out.printf("goal page: %b%n" , stage.isShowing());
	}
	
	/**
	 * get the goal controller
	 * @return controller - GoalController
	 */
	public GoalController getController() {
		return controller;
		
	}
}
