package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Pause Screen goes here.
 * Loads everything in the controller
 * @author z5177103
 *
 */
public class PauseScreen {

    private Stage stage;
    private String title;
    private PauseController controller;
    private Scene scene;

    public PauseScreen(Stage stage)  throws IOException {
        this.stage = stage;
        title = "Start Screen";

        controller = new PauseController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pausedPage.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }

    /**
     * show to frontend
     */
	public void show() {
		stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        System.out.printf("start page: %b%n" , stage.isShowing());
	}

	/**
	 * get the PauseController
	 * @return cotroller
	 */
    public PauseController getController() {
        return controller;
    }

}

