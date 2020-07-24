package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Start Screen goes here.
 * Loads everything in the controller
 * @author z5177103
 *
 */
public class StartScreen {

    private Stage stage;
    private String title;
    private StartController controller;
    private Scene scene;

    public StartScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Start Screen";

        controller = new StartController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startPage.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }

    /**
     * starts the screen
     */
    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        System.out.printf("start page: %b%n" , stage.isShowing());
    }

    /**
     * get the contoller
     * @return controller - startcontroller
     */
    public StartController getController() {
        return controller;
    }
}
