package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
/**
 * Start screen goes here
 * @author z5177103
 *
 */


public class GameCompletedController {

    
    private PlayScreen playScreen;
    private PauseScreen pauseScreen;
    
    @FXML
    private Pane pane;

    @FXML
    private Button restartButton;

    @FXML
    void handleRestartButton(ActionEvent event) throws IOException {
        //playScreen.restart(gameCompletedScreen, pauseScreen, goalScreen);
    }

    public void setPlayScreen(PlayScreen playScreen) {
    	
    	//playScreen.cleanup();
        this.playScreen = playScreen;
    }
    
    public void setPauseScreen(PauseScreen pauseScreen) {
        this.pauseScreen = pauseScreen;
    }
    

}
