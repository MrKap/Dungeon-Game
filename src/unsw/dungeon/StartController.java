package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
/**
 * Start screen goes here
 * @author z5177103
 *
 */
public class StartController {
    
	PlayScreen playScreen;
	GoalScreen goalScreen;
	GameOverScreen gameOverScreen;
	PauseScreen pauseScreen;
	AboutScreen aboutScreen;
	
	@FXML
	private TextArea title;
	
	@FXML
	private Button playButton;
	
	@FXML
	private Button aboutButton;
	
	@FXML
	void handleAbout(ActionEvent event) {
		aboutScreen.show();
	}
	
	@FXML
	void handlePlay(ActionEvent event) throws IOException {
		 playScreen.restart(gameOverScreen, pauseScreen, goalScreen);
	}
	
	//SETTERS
    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public void setPauseScreen(PauseScreen pauseScreen) {
        this.pauseScreen = pauseScreen;
    }
    public void setGameOverScreen(GameOverScreen gameOverScreen) {
        this.gameOverScreen = gameOverScreen;
    }
    public void setGoalScreen(GoalScreen goalScreen) {
        this.goalScreen = goalScreen;
    }
    public void setAboutScreen(AboutScreen aboutScreen) {
    	this.aboutScreen = aboutScreen;
    }
}
