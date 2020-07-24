package unsw.dungeon;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameCompletedScreen {

	private Stage stage;
    private String title;
    private GameCompletedController controller;
    private Scene scene;

    public GameCompletedScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "GAME Completed :')";

		controller = new GameCompletedController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("gameCompleted.fxml"));
		loader.setController(controller);
		
		// load into a Parent node called root
		    Parent root = loader.load();
		    scene = new Scene(root);
	}
	
	public void start() throws IOException {
		
	    stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();
	    System.out.printf("game completed page: %b%n" , stage.isShowing());
	}
	
	public GameCompletedController getController() {
	    return controller;
	}

}
