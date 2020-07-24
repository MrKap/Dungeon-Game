package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * controller to view About screen.
 * set FXML controller
 * @author z5177103
 *
 */
public class AboutController {
	

	StartScreen startScreen;
	
    @FXML
    private Pane pane;
    
    @FXML
    private Button backButton;

    /**
     * handle back button to return startScreen
     * @param event 
     */
    @FXML
    void handleBackButton(ActionEvent event) {
    	startScreen.start();
    }

    /**
     * set the startScreen so that we can go back to Start Screen
     * @param startScreen
     */
    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }
}
