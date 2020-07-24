package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * About Screen goes here.
 * Loads everything in the controller
 * @author z5177103
 *
 */
public class AboutScreen {
	
	private Stage stage;
    private String title;
    private AboutController controller;
    private Scene scene;
    
    
    /**
     * AboutScreen constructor
     * initialize new AboutController , screen title
     * @param stage
     * @throws IOException
     */
    public AboutScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "About Screen";

        controller = new AboutController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aboutPage.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }
    
    /**
     * main method to call About screen page
     */
	public void show() {
		// TODO Auto-generated method stub
		stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        //System.out.printf("start page: %b%n" , stage.isShowing());
	}
	
	public AboutController getController() {
        return controller;
    }
	

}
